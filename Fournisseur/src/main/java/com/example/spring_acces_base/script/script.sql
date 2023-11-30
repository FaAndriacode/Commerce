
CREATE sequence n_magasin ;
CREATE SEQUENCE n_idsortie ;
CREATE SEQUENCE n_idEntre ;
CREATE SEQUENCE n_idMouvement;
CREATE SEQUENCE n_idetat;
CREATE SEQUENCE n_idunitaire;

--creation de la table fifo-lifo
CREATE TABLE etat (
    idEtat INTEGER DEFAULT nextval('n_idetat') PRIMARY KEY,
    nom VARCHAR(255)
);

-- Création de la table "Magasin"
CREATE TABLE Magasin (
    idmagasin INTEGER DEFAULT nextval('n_magasin') PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

--creation de la table unitaire
CREATE TABLE unite (
    idUnite INTEGER DEFAULT nextval('n_idunitaire') PRIMARY KEY,
    nom VARCHAR(255),
    quantite DOUBLE PRECISION
);

-- Création de la table "Article"
CREATE TABLE Article (
    idArticle VARCHAR(255) PRIMARY KEY,
    nom VARCHAR(255),
    etat INTEGER,
    idequivalance INTEGER,
    FOREIGN KEY (etat) REFERENCES etat(idEtat),
    FOREIGN KEY (idequivalance) REFERENCES unite(idUnite)
);

-- Création de la table "sortie_stocke"
CREATE TABLE sortie_stocke (
    idsortie INTEGER DEFAULT nextval('n_idsortie') PRIMARY KEY,
    Quantite DOUBLE PRECISION,
    idMagasin INTEGER,
    idArticle VARCHAR(255),
    Date_sortie Date,
    Quantite_o DOUBLE PRECISION,
    idunite INTEGER,
    FOREIGN KEY (idMagasin) REFERENCES Magasin(idmagasin),
    FOREIGN KEY (idArticle) REFERENCES Article(idArticle)
);

-- Création de la table "entre_Stocke"
CREATE TABLE entre_stocke (
    idEntre INTEGER DEFAULT nextval('n_idEntre') PRIMARY KEY,
    idArticle VARCHAR(255),
    Quantite DOUBLE PRECISION,
    prixUnitaire DOUBLE PRECISION,
    idMagasin INTEGER,
    Date_Entre Date,
    Quantite_o DOUBLE PRECISION,
    idunite INTEGER,
    FOREIGN KEY (idArticle) REFERENCES Article(idArticle),
    FOREIGN KEY (idMagasin) REFERENCES Magasin(idmagasin)
);

-- Creation de la table movement
CREATE TABLE mouvement (
    idMouvement INTEGER DEFAULT nextval('n_idMouvement') PRIMARY KEY,
    Quantite_F DOUBLE PRECISION,
    idEntre INTEGER,
    idSorti INTEGER,
    FOREIGN KEY (idEntre) REFERENCES entre_Stocke(idEntre)
);

-- Création de la table "sortie_stocke"
CREATE TABLE sortie_valide (
    idSortie INTEGER,
    Date_sortie_valide Date,
    FOREIGN KEY (idSortie) REFERENCES sortie_stocke(idSortie)
);

-- Insertion de données de test dans la table "Etat"
 INSERT INTO etat(nom)
 VALUES
    ('FIFO'),
    ('LIFO');

-- Insertion de données de test dans la table "unite"
 INSERT INTO unite(nom, quantite)
 VALUES
    ('Unite', 1),
    ('Gazo', 8),
    ('Carton', 10),
    ('Sase', 50);

-- Insertion de données de test dans la table "Magasin"
INSERT INTO Magasin (nom,email,password)
VALUES
    ('Magasin A','escobarpabloo2802@gmail.com','fandresena'),
    ('Magasin B','escobarpabloo2802@gmail.com','huhu'),
    ('Magasin C','escobarpabloo2802@gmail.com','allo');

-- Insertion de données de test dans la table "Article"
INSERT INTO Article (idarticle, nom, etat, idequivalance)
VALUES
    ('B1', 'biski', 1, 4),
    ('J1', 'thb', 2, 2),
    ('U1', 'fromage', 2, 3);

----creation de view
CREATE OR REPLACE VIEW stock_reste AS
    WITH ranked_mouvements AS (
        SELECT
            es.identre,
            es.idarticle,
            es.prixunitaire,
            es.idmagasin,
            es.date_entre,
            es.quantite,
            m.idmouvement,
            COALESCE(m.quantite_f, es.quantite) AS quantite_f,
            ROW_NUMBER() OVER (PARTITION BY es.identre, es.idarticle ORDER BY m.idmouvement DESC) AS row_num
        FROM 
            entre_stocke AS es
        LEFT JOIN 
            mouvement AS m ON m.identre = es.identre
    )SELECT
        identre,
        idarticle,
        prixunitaire,
        idmagasin,
        date_entre,
        quantite,
        idmouvement,
        quantite_f as reste
    FROM
        ranked_mouvements
    WHERE
        row_num = 1 AND quantite_f <> 0;

CREATE OR REPLACE VIEW sortie AS 
SELECT 
    sv.idsortie,
    sv.date_sortie_valide,
    ss.quantite,
    ss.idmagasin,
    ss.idarticle,
    ss.date_sortie
    FROM
        sortie_valide as sv
    JOIN sortie_stocke as ss ON sv.idsortie = ss.idsortie ;

CREATE OR REPLACE VIEW listes AS
SELECT 
    ss.idsortie,
    ss.quantite as quantite_sortie,
    ss.quantite_o as quantite_o_sortie,
    u.nom as unite_nom,
    ss.idmagasin,
    ss.idarticle,
    ss.date_sortie as date
FROM
    sortie_stocke as ss
JOIN unite as u ON ss.idunite = u.idunite;

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

CREATE OR REPLACE VIEW v_sortie_liste AS
SELECT
    *
    FROM sortie_stocke ss
    WHERE NOT EXISTS (
        SELECT 1
        FROM sortie s
        WHERE s.idsortie = ss.idsortie
    );