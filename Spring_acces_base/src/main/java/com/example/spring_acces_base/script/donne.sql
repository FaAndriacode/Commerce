-- Exemples de données pour la table "Departement"
INSERT INTO Departement (nom) VALUES
('Département 1'),
('Département 2'),
('Département 3');

-- Exemples de données pour la table "Services"
INSERT INTO Services (nom, idDept) VALUES
('Service A', 1),
('Service B', 1),
('Service C', 2);

-- Exemples de données pour la table "ChefServices"
INSERT INTO ChefServices (email, passwords, idServices) VALUES
('chefA@email.com', 'passwordA', 1),
('chefB@email.com', 'passwordB', 2),
('chefC@email.com', 'passwordC', 3);

-- Exemples de données pour la table "ChefDept"
INSERT INTO ChefDept (email, passwords, idDept) VALUES
('chefDept1@email.com', 'deptPassword1', 1),
('chefDept2@email.com', 'deptPassword2', 2),
('chefDept3@email.com', 'deptPassword3', 3);

-- Exemples de données pour la table "Fournisseur"
INSERT INTO Fournisseur (nom, adresse, Email) VALUES
('Fournisseur A', 'Adresse A', 'fournisseurA@email.com'),
('Fournisseur B', 'Adresse B', 'fournisseurB@email.com'),
('Fournisseur C', 'Adresse C', 'fournisseurC@email.com');

-- Exemples de données pour la table "Article"
INSERT INTO Article (unite) VALUES
('Unité A'),
('Unité B'),
('Unité C');

-- Exemples de données pour la table "Besoin"
INSERT INTO Besoin (idArticle, Quantite, Date_creation, etat, idServices) VALUES
(1, 10, '2023-11-18', 1, 1),
(2, 15, '2023-11-18', 2, 2),
(3, 20, '2023-11-18', 1, 3);

-- Exemples de données pour la table "FournArticle"
INSERT INTO FournArticle (idFournisseur, idArticle, prixunitaire, Quantite) VALUES
(1, 1, 50.0, 100),
(2, 2, 30.0, 150),
(3, 3, 25.0, 200);
