--requete de teste
SELECT * FROM mouv;
SELECT * FROM entre_stocke as es JOIN mouv as m ON m.identre = es.identre;

-- Exemple d'appel de la fonction avec idArticle et idMagasin
SELECT * from stock_init('A001', 1, '2023-11-15') AS stock_disponible;
SELECT * from stock_final('A001', 1, '2023-11-15', '2023-11-18') AS difference_stock;
select * from quantite_total(1,'2023-11-15','2023-11-25');

-- stock_final(idarticle,idmagasin,debut,fin)
-- stock_inti(idarticle,idmagasin,debut)

SELECT * FROM stock_reste WHERE idArticle = 'A001' AND idMagasin = 1 AND (reste IS NULL OR reste <> 0) ORDER BY Date_Entre ASC ,idEntre ASC;

SELECT 
    es.identre AS identre,
    m.idmouvement AS mouvement_id,
    es.idarticle AS idarticle,
    es.idmagasin AS idmagasin,
    m.quantite_f AS reste,
    es.prixunitaire AS prixunitaire,
    es.date_entre AS date_entre
FROM 
    mouvement AS m
WHERE es.idarticle = 'A001' AND idMagasin = 1 


select 
    *
    from mouvement as m 
    JOIN sortie_stocke as ss on m.idsorti = ss.idsortie 
    where 
        m.identre = 2 AND
        date_sortie <= '2023-11-20'
    ORDER BY idmouvement DESC LIMIT 1;

select 
    *
    from mouvement as m 
    JOIN sortie_stocke as ss on m.idsorti = ss.idsortie 
    where 
        m.identre = 1 AND
        date_sortie <= '2023-11-29'
    ORDER BY idmouvement DESC LIMIT 1;


select 
    identre,stock_init(idArticle,idmagasin,'2023-11-15') as stockinitial ,stock_final(idArticle,idmagasin,'2023-11-15','2023-11-18') as stockfinal,quantite_total(identre,prixunitaire,'2023-11-15','2023-11-18') as quantite_fin
from entre_stocke 
group by identre;


--etape 1 ß
select 
    idarticle,
    idmagasin,
    entre_stocke.*,
    stock_init(idArticle,idmagasin,'2023-11-15') as stockinitial,
    stock_final(idArticle, idmagasin,'2023-11-15','2023-11-18') AS quantitefinal,
    quantite_total(identre,'2023-11-15','2023-11-18') as montant_s_calcule,
    (prixunitaire *(quantite_total(identre,'2023-11-15','2023-11-18'))) as montant_f_prix,
    (quantite - quantite_total(identre,'2023-11-15','2023-11-18')) as montantT,
    (prixunitaire *(quantite -quantite_total(identre,'2023-11-15','2023-11-18'))) as montant
from entre_stocke;

select 
    identre,
    idarticle,
    idmagasin,
    quantite,
    prixunitaire,
    stock_init(idArticle,idmagasin,'2023-11-14') as stockinitial,
    stock_final(idArticle, idmagasin,'2023-11-14','2023-11-19') AS quantitefinal,
    (quantite_total(identre,'2023-11-14','2023-11-19')) as montant_s,
    (prixunitaire *(quantite_total(identre,'2023-11-14','2023-11-19'))) as montant
from entre_stocke;

-- parameter possible | prixunitaire | date entrant
--etape 2 ß  idarticle | idmagasin | stockinitial | quantitefinal | montant 

--etape 3 ß creation de fontion


SELECT
    idarticle,
    idmagasin,
    stock_initial,
    quantite_finale,
    (SUM(montant)/quantite_finale )as prixmoyen,
    SUM(montant) AS montant
FROM etat_stock('2023-11-20', '2023-11-24')
GROUP BY idarticle, idmagasin, stock_initial, quantite_finale;


SELECT 
    e.idarticle,
    e.idmagasin,
    e.stock_initial,
    e.quantite_finale,
    SUM(e.montant) AS montant,
    CASE WHEN e.quantite_finale <> 0 THEN SUM(e.montant) / e.quantite_finale ELSE 0 END as prixmoyen,
    m.nom AS magasin,
    a.nom AS article 
FROM etat_stock('2023-11-16', '2023-11-22') e 
    JOIN magasin m ON e.idmagasin = m.idmagasin 
    JOIN article a ON e.idarticle = a.idarticle 
GROUP BY e.idarticle, e.idmagasin, e.stock_initial, e.quantite_finale, m.nom, a.nom;


SELECT
    e.idarticle,
    e.idmagasin,
    e.stock_initial,
    e.quantite_finale,
    CASE WHEN e.quantite_finale <> 0 THEN SUM(e.montant) / e.quantite_finale ELSE 0 END as prixmoyen,
    SUM(e.montant) AS montant,
    m.nom AS magasin,
    a.nom AS article
FROM
    etat_stock('2023-11-03', '2023-11-13') e
JOIN
    magasin m ON e.idmagasin = m.idmagasin
JOIN
    article a ON e.idarticle = a.idarticle
WHERE
    e.idarticle LIKE '%'
GROUP BY
    e.idarticle, e.idmagasin, e.stock_initial, e.quantite_finale, m.nom, a.nom;



CREATE OR REPLACE VIEW entre AS
SELECT 
    es.idEntre,
    es.quantite as quantite_sortie,
    es.quantite_o as quantite_o_sortie,
    u.nom as unite_nom,
    es.idmagasin,
    es.idarticle,
    es.Date_Entre as date
FROM
    entre_stocke as es
JOIN unite as u ON es.idunite = u.idunite;