CREATE SCHEMA IF NOT EXISTS gestione_ledwall ;

USE gestione_ledwall;

CREATE TABLE IF NOT EXISTS palinsesti (
  id_palinsesto INT NOT NULL,
  path_palinsesto VARCHAR(75) NULL,
  PRIMARY KEY (id_palinsesto)
  );

CREATE TABLE IF NOT EXISTS impianti (
  id_impianto INT NOT NULL,
  descrizione VARCHAR(100) NULL,
  latitudine DECIMAL(17,14) NULL DEFAULT NULL,
  longitudine DECIMAL(17,14) NULL DEFAULT NULL,
  path_palinsesto VARCHAR(75) NOT NULL,
  stato BOOLEAN DEFAULT FALSE, -- 0 = disattivo; 1= Attivo
  PRIMARY KEY (id_impianto)
  );
  
CREATE TABLE IF NOT EXISTS cartelloni (
  id_cartellone INT NOT NULL,
  path_cartellone VARCHAR(95) NOT NULL,
  durata INT NOT NULL,
  PRIMARY KEY (id_cartellone));

CREATE TABLE IF NOT EXISTS segnalazioni (
   id_segnalazione int NOT NULL AUTO_INCREMENT,
   ref_idimpianto int NOT NULL,
   ref_idpalinsesto int NOT NULL,
   ref_idcartellone int DEFAULT '-1',
   durata int DEFAULT NULL,
   timestamps timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (id_segnalazione),
   FOREIGN KEY (ref_idimpianto) REFERENCES gestione_ledwall.impianti (id_impianto),
   FOREIGN KEY (ref_idpalinsesto) REFERENCES gestione_ledwall.palinsesti (id_palinsesto),
   FOREIGN KEY (ref_idcartellone) REFERENCES gestione_ledwall.cartelloni (id_cartellone)
   );
   
CREATE TABLE IF NOT EXISTS utenti (
  id_utente INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(45) NOT NULL,
  pass VARCHAR(255) NOT NULL, 
  enabled TINYINT(1) DEFAULT 1,
  PRIMARY KEY (id_utente),
  UNIQUE KEY email_UNIQUE (email),
  UNIQUE KEY username_UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

 
CREATE TABLE IF NOT EXISTS ruoli (
  id_ruolo INT NOT NULL AUTO_INCREMENT,
  nome varchar(45) NOT NULL,
  PRIMARY KEY (id_ruolo)
);
 
CREATE TABLE IF NOT EXISTS ruoli_utente (
  id_utente INT NOT NULL,
  id_ruolo INT NOT NULL,
  KEY utente_fk_idx (id_utente),
  KEY ruolo_fk_idx (id_ruolo),
  CONSTRAINT ruolo_fk FOREIGN KEY (id_ruolo) REFERENCES ruoli (id_ruolo),
  CONSTRAINT utente_fk FOREIGN KEY (id_utente) REFERENCES utenti (id_utente)
);
 

INSERT INTO gestione_ledwall.palinsesti (id_palinsesto, path_palinsesto) VALUES ('1', '/palinsesto1.xml');
INSERT INTO gestione_ledwall.palinsesti (id_palinsesto, path_palinsesto) VALUES ('2', '/palinsesto2.xml');
INSERT INTO gestione_ledwall.palinsesti (id_palinsesto, path_palinsesto) VALUES ('3', '/palinsesto3.xml');
INSERT INTO gestione_ledwall.palinsesti (id_palinsesto, path_palinsesto) VALUES ('4', '/palinsesto4.xml');
INSERT INTO gestione_ledwall.palinsesti (id_palinsesto, path_palinsesto) VALUES ('5', '/palinsesto5.xml');

INSERT INTO gestione_ledwall.impianti (id_impianto, descrizione, latitudine, longitudine, path_palinsesto, stato) VALUES ('1', 'LedWall di Via della Libertà', '38.132597', '13.350420', '/palinsesto1.xml', '1');
INSERT INTO gestione_ledwall.impianti (id_impianto, descrizione, latitudine, longitudine, path_palinsesto, stato) VALUES ('2', 'LedWall di Piazza Castelnuovo', '38.125009', '13.355615','/palinsesto2.xml', '1');
INSERT INTO gestione_ledwall.impianti (id_impianto, descrizione, latitudine, longitudine, path_palinsesto, stato) VALUES ('3', 'LedWall di Via Roma', '38.110887', '13.366613', '/palinsesto3.xml', '1');
INSERT INTO gestione_ledwall.impianti (id_impianto, descrizione, latitudine, longitudine, path_palinsesto, stato) VALUES ('4', 'LedWall di via E. Tricomi', '38.101618', '13.356619', '/palinsesto4.xml', '1');
INSERT INTO gestione_ledwall.impianti (id_impianto, descrizione, latitudine, longitudine, path_palinsesto, stato) VALUES ('5', 'LedWall di Piazza Indipendenza', '38.111464', '13.351100', '/palinsesto5.xml', '1');

INSERT INTO gestione_ledwall.ruoli (nome) VALUES ('ADMIN');
INSERT INTO gestione_ledwall.utenti (username, email, pass, enabled) VALUES ('admin', 'admin@admin.com', '$2a$12$3FD7Q1L7fQHk5gvD010Ls.yZY7WkTrjvG.nIHANcyT7ckKfEXX1LG', '1');
INSERT INTO gestione_ledwall.ruoli_utente(id_utente, id_ruolo) VALUES (1, 1);


INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('101', '/casa-di-carta.html', '10');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('102', '/dolce&amp;gabbana.html', '8');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('103', '/juve_abbonamenti.html', '9');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('104', '/McDonalds.html', '35');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('105', '/radio105.html', '12');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('106', '/michelin.html', '35');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('107', '/sector.html', '11');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('108', '/relaxdasogno.html', '37');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('109', '/onePlus.html', '9');
INSERT INTO gestione_ledwall.cartelloni (id_cartellone, path_cartellone, durata) VALUES ('110', '/sky_wifi.html', '7');
