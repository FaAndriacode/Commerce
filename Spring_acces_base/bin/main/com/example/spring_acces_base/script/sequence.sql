CREATE SEQUENCE n_idDept;
CREATE SEQUENCE n_idServices;
CREATE SEQUENCE n_idUserServices;
CREATE SEQUENCE n_idUserDept;
CREATE SEQUENCE n_idFournisseur;
CREATE SEQUENCE n_idBesoin;
CREATE SEQUENCE n_idArticle;
CREATE SEQUENCE n_idFournarticle;
CREATE SEQUENCE n_idFournarticletemp;
CREATE SEQUENCE n_idBoncommande;

CREATE SEQUENCE n_idFournarticletemp;
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