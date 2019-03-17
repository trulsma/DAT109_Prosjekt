DROP SCHEMA IF EXISTS prosjekt1 CASCADE;
CREATE SCHEMA prosjekt1;
SET search_path TO prosjekt1;

CREATE TABLE Bedrift (
  bedriftid          SERIAL,
  bedriftnavn        VARCHAR(100) UNIQUE,
  bedriftbeskrivelse VARCHAR(1000),
  CONSTRAINT bedriftPK PRIMARY KEY (bedriftid)
);

CREATE TABLE StemmeMetode (
  metodeid        SERIAL,
  metodenavn      VARCHAR(25),
  metodeparameter INTEGER,
  CONSTRAINT metodePK PRIMARY KEY (metodeid),
  CONSTRAINT metodenavnUnique UNIQUE (metodenavn)
);

CREATE TABLE Kategori (
  kategoriid   SERIAL,
  kategorinavn VARCHAR(255),
  CONSTRAINT kategoriPK PRIMARY KEY (kategoriid),
  CONSTRAINT uniqueKategorinavn UNIQUE (kategorinavn)
);

CREATE TABLE Prosjekt (
  prosjektid          SERIAL,
  prosjektnavn        VARCHAR(100) UNIQUE,
  prosjektbeskrivelse VARCHAR(1000),
  prosjektkategori    INTEGER,
  sammarbeidsbedrift  INTEGER,
  stemmemetode        INTEGER,
  shortenedurl        VARCHAR(255),
  qrimagepath         VARCHAR(255),
  pictureurl          VARCHAR(255),
  CONSTRAINT bedriftFK FOREIGN KEY (sammarbeidsbedrift) REFERENCES Bedrift (bedriftid),
  CONSTRAINT kategoriFK FOREIGN KEY (prosjektkategori) REFERENCES Kategori (kategoriid),
  CONSTRAINT metodeFK FOREIGN KEY (stemmemetode) REFERENCES StemmeMetode (metodeid),
  CONSTRAINT prosjektPK PRIMARY KEY (prosjektid)
);

CREATE TABLE Stemme (
  stemmeid        SERIAL,
  prosjektid      INTEGER,
  epost           VARCHAR(255),
  stemmeverdi     INTEGER,
  stemmetidspunkt TIMESTAMP,
  CONSTRAINT prosjektidFK FOREIGN KEY (prosjektid) REFERENCES Prosjekt (prosjektid),
  CONSTRAINT stemmePK PRIMARY KEY (stemmeid)
);

CREATE TABLE Arrangement (
  arrangementid          SERIAL,
  arrangementnavn        VARCHAR(255),
  arrangementbeskrivelse VARCHAR(255),
  arragementetutgaar     TIMESTAMP,
  CONSTRAINT arrangementPK PRIMARY KEY (arrangementid)
);

CREATE TABLE ArrangementDeltagelse (
  deltagelseid SERIAL,
  arrangement  INTEGER,
  prosjekt     INTEGER,
  CONSTRAINT deltagelsePK PRIMARY KEY (deltagelseid),
  CONSTRAINT prosjektFK FOREIGN KEY (prosjekt) REFERENCES Prosjekt (prosjektid),
  CONSTRAINT arrangementFK FOREIGN KEY (arrangement) REFERENCES Arrangement (arrangementid)
);

INSERT INTO Bedrift (bedriftnavn, bedriftbeskrivelse)
VALUES ('HVL', 'Høgskolen på Vestlandet');
INSERT INTO StemmeMetode (metodenavn, metodeparameter)
VALUES ('Like', 1);
INSERT INTO Kategori (kategorinavn)
VALUES ('Data og Realfag');
INSERT INTO Arrangement (arrangementnavn, arrangementbeskrivelse, arragementetutgaar)
VALUES ('Første Arrangement', 'Dette er en beskrivelse', '2019-03-17 12:07:21.827000');
INSERT INTO Prosjekt (prosjektnavn,
                      prosjektbeskrivelse,
                      prosjektkategori,
                      sammarbeidsbedrift,
                      stemmemetode,
                      shortenedurl,
                      qrimagepath,
                      pictureurl)
VALUES ('Prosjekt navn', 'Beskrivelse', 1, 1, 1, 'short url', 'qr path', 'picture url');
INSERT INTO ArrangementDeltagelse (arrangement, prosjekt)
VALUES (1, 1);
