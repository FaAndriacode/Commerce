--drop view
DROP VIEW stock_reste CASCADE;
DROP VIEW sortie CASCADE;
DROP VIEW listes CASCADE;

-- Suppression des tables
DROP TABLE mouvement CASCADE;
DROP TABLE sortie_stocke CASCADE;
DROP TABLE entre_Stocke CASCADE;
DROP TABLE Article CASCADE;
DROP TABLE Magasin CASCADE;
DROP TABLE Etat CASCADE;
DROP TABLE sortie_valide CASCADE;
DROP TABLE unite CASCADE;

-- Suppression des séquences
DROP SEQUENCE n_idMouvement;
DROP SEQUENCE n_idEntre;
DROP SEQUENCE n_idsortie;
DROP SEQUENCE n_magasin;
DROP SEQUENCE n_idetat;
DROP SEQUENCE n_idunitaire;

-- drop fonction
DROP FUNCTION stock_final;
DROP FUNCTION stock_init;
DROP FUNCTION quantite_total;
DROP FUNCTION etat_stock;
