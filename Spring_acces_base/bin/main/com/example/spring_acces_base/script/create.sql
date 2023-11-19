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
    id_fourn_article INTEGER DEFAULT nextval('n_id_fourn_article') PRIMARY KEY,
    id_fournisseur INTEGER,
    id_article INTEGER,
    prixunitaire DOUBLE PRECISION,
    Quantite DOUBLE PRECISION,
    FOREIGN KEY (id_fournisseur) REFERENCES Fournisseur(idFournisseur),
    FOREIGN KEY (id_article) REFERENCES Article(idArticle)
);