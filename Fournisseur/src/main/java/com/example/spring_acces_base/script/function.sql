-- calcule de quantiter final et creation de la fonction
-- Création de la fonction
CREATE OR REPLACE FUNCTION stock_final(
    id_article VARCHAR(250),
    id_magasin INT,
    date_debut DATE,
    date_fin DATE
)
RETURNS DOUBLE PRECISION AS $$
DECLARE
    stock_sortie DOUBLE PRECISION;
    stock_entree DOUBLE PRECISION;
    difference_stock DOUBLE PRECISION;
BEGIN
    -- Calcul du stock d'entrée
    SELECT COALESCE(SUM(e.quantite), 0)
    INTO stock_entree
    FROM Entre_Stocke e
    WHERE e.date_entre <= date_fin 
    AND e.idArticle = id_article AND e.idMagasin = id_magasin;

     -- Calcul du stock de sortie
    SELECT COALESCE(SUM(s.quantite), 0)
    INTO stock_sortie
    FROM sortie s
    WHERE s.date_sortie_valide <= date_fin 
    AND s.idArticle = id_article AND s.idMagasin = id_magasin;

    -- Calcul du stock disponible
    difference_stock := stock_entree - stock_sortie;

    -- Retourner le stock disponible
    RETURN ABS(difference_stock);
END;
$$ LANGUAGE plpgsql;

--initial
-- Création de la fonction
CREATE OR REPLACE FUNCTION stock_init(
    id_article VARCHAR(250),
    id_magasin INT,
    date_limite DATE
)
RETURNS DOUBLE PRECISION AS $$
DECLARE
    stock_sortie DOUBLE PRECISION;
    stock_entree DOUBLE PRECISION;
    stock_disponible DOUBLE PRECISION;
BEGIN
    -- Calcul du stock d'entrée
    SELECT COALESCE(SUM(e.quantite), 0)
    INTO stock_entree
    FROM Entre_Stocke e
    WHERE e.date_entre <= date_limite 
    AND e.idArticle = id_article AND e.idMagasin = id_magasin;

     -- Calcul du stock de sortie
    SELECT COALESCE(SUM(s.quantite), 0)
    INTO stock_sortie
    FROM sortie s
    WHERE s.date_sortie_valide <= date_limite 
    AND s.idArticle = id_article AND s.idMagasin = id_magasin;

    -- Calcul du stock disponible
    stock_disponible := stock_entree - stock_sortie;

    -- Retourner le stock disponible
    RETURN ABS(stock_disponible);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION quantite_total(
    id_entre_param INT,
    date_debut_param DATE,
    date_fin_param DATE
)
RETURNS DOUBLE PRECISION AS $$
DECLARE
    stock_sortie DOUBLE PRECISION;
BEGIN
    -- Sélectionner la quantité de sortie
    SELECT 
        quantite_f
    INTO stock_sortie
    FROM mouvement as m 
    JOIN sortie as ss ON m.idsorti = ss.idsortie 
    WHERE 
        m.identre = id_entre_param AND
        date_sortie <= date_fin_param
    ORDER BY idmouvement DESC LIMIT 1;

    -- Vérifier si la sortie a été trouvée
    IF stock_sortie IS NULL THEN
        -- Si aucune sortie n'est trouvée, retourner la quantité d'entrée
        SELECT 
            es.quantite
        INTO stock_sortie
        FROM entre_stocke es
        WHERE 
            es.identre = id_entre_param AND
            date_entre <= date_fin_param;
    END IF;

    -- Retourner le stock disponible
    RETURN stock_sortie;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION etat_stock(
    date_debut_param DATE,
    date_fin_param DATE
)
RETURNS TABLE (
    idarticle VARCHAR(255),
    idmagasin INTEGER,
    stock_initial DOUBLE PRECISION,
    quantite_finale DOUBLE PRECISION,
    montant DOUBLE PRECISION
)
AS $$
BEGIN
    RETURN QUERY
    SELECT 
        es.idarticle,
        es.idmagasin,
        stock_init(es.idArticle, es.idmagasin, date_debut_param) AS stockinitial,
        stock_final(es.idArticle, es.idmagasin, date_debut_param, date_fin_param) AS quantitefinal,
        (es.prixunitaire * (quantite_total(es.identre, date_debut_param, date_fin_param))) AS montant
    FROM entre_stocke es;

    RETURN;
END;
$$ LANGUAGE plpgsql;