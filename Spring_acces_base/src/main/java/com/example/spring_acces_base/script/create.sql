-- Création de la table "Departement"
CREATE TABLE Departement (
    idDept INTEGER DEFAULT nextval('n_idDept') PRIMARY KEY,
    nom VARCHAR(100)
);

-- Création de la table "Service"
CREATE TABLE Services (
    idServices INTEGER DEFAULT nextval('n_idServices') PRIMARY KEY,
    nom VARCHAR(100),
    idDept INTEGER,
    FOREIGN KEY (idDept) REFERENCES Departement(idDept)
);

-- Création de la table "chef service"
CREATE TABLE ChefServices (
    idUserServices INTEGER DEFAULT nextval('n_idUserServices') PRIMARY KEY,
    email VARCHAR(100),
    passwords VARCHAR(100),
    idServices INTEGER,
    FOREIGN KEY (idServices) REFERENCES Services(idServices)
);

-- Création de la table "chef departement"
CREATE TABLE ChefDept (
    idUserDept INTEGER DEFAULT nextval('n_idUserDept') PRIMARY KEY,
    email VARCHAR(100),
    passwords VARCHAR(100),
    idDept INTEGER,
    FOREIGN KEY (idDept) REFERENCES Departement(idDept)
);

-- Création de la table "Fournisseur"
CREATE TABLE Fournisseur (
    idFournisseur INTEGER DEFAULT nextval('n_idFournisseur') PRIMARY KEY,
    nom VARCHAR(100),
    adresse VARCHAR(100),
    Email VARCHAR(100)
);

-- Création de la table "Besoin"
CREATE TABLE Besoin (
    idBesoin INTEGER DEFAULT nextval('n_idBesoin') PRIMARY KEY,
    idArticle INTEGER,
    Quantite DOUBLE PRECISION,
    Date_creation DATE,
    etat INTEGER,
    idServices INTEGER,
    FOREIGN KEY (idServices) REFERENCES Services(idServices)
);

-- Creation de la table Article
CREATE TABLE Article (
    idArticle INTEGER DEFAULT nextval('n_idArticle') PRIMARY KEY,
    nom VARCHAR(100),
    unite VARCHAR(100)
);

-- Création de la table "FournArticle"
CREATE TABLE FournArticle (
    idfournarticle INTEGER DEFAULT nextval('n_idFournarticle') PRIMARY KEY,
    idFournisseur INTEGER,
    idArticle INTEGER,
    prixunitaire DOUBLE PRECISION,
    Quantite DOUBLE PRECISION,
    date date,
    FOREIGN KEY (idFournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY (idArticle) REFERENCES Article(idArticle)
);

CREATE TABLE FournArticleTemp (
    idfournarticle INTEGER DEFAULT nextval('n_idFournarticletemp') PRIMARY KEY,
    idFournisseur INTEGER,
    idArticle INTEGER,
    prixunitaire DOUBLE PRECISION,
    Quantite DOUBLE PRECISION,
    date date,
    FOREIGN KEY (idFournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY (idArticle) REFERENCES Article(idArticle)
);

CREATE TABLE BonCommande (
    idboncommande INTEGER DEFAULT nextval('n_idBoncommande') PRIMARY KEY,
    idArticle INTEGER,
    idFournisseur INTEGER,
    prixunitaire DOUBLE PRECISION,
    Quantite DOUBLE PRECISION,
    date date,
    FOREIGN KEY (idFournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY (idArticle) REFERENCES Article(idArticle) 
);

CREATE VIEW v_boncommande AS 
select
boncommande.idboncommande,
boncommande.idArticle,
boncommande.idFournisseur,
boncommande.prixunitaire,
boncommande.Quantite,
boncommande.date,
fournisseur.nom as nomfournisseur,
fournisseur.adresse,
article.nom,
article.unite,
((prixunitaire*20)/100) as pht,
(prixunitaire*quantite) as total,
(select SUM(prixunitaire*quantite) from boncommande where idfournisseur=fournisseur.idFournisseur) as total_final
from boncommande
join fournisseur on fournisseur.idfournisseur=boncommande.idfournisseur
join article on article.idarticle=boncommande.idarticle;
