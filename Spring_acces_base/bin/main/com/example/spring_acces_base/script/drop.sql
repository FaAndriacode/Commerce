--drop table
DROP TABLE IF EXISTS FournArticle;
DROP TABLE IF EXISTS Besoin;
DROP TABLE IF EXISTS Article;
DROP TABLE IF EXISTS Fournisseur;
DROP TABLE IF EXISTS ChefDept;
DROP TABLE IF EXISTS ChefServices;
DROP TABLE IF EXISTS Services;
DROP TABLE IF EXISTS Departement;

--drop sequences
DROP SEQUENCE IF EXISTS n_idDept;
DROP SEQUENCE IF EXISTS n_idServices;
DROP SEQUENCE IF EXISTS n_idUserServices;
DROP SEQUENCE IF EXISTS n_idUserDept;
DROP SEQUENCE IF EXISTS n_idFournisseur;
DROP SEQUENCE IF EXISTS n_idBesoin;
DROP SEQUENCE IF EXISTS n_idArticle;
drop sequence if exists n_id_fourn_article;