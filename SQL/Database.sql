DROP SCHEMA IF EXISTS prosjekt1 CASCADE;
CREATE SCHEMA prosjekt1;
SET search_path TO prosjekt1;

CREATE TABLE Usergroup (
  groupid    SERIAL,
  groupname  VARCHAR(255),
  grouplevel INTEGER,
  CONSTRAINT groupPK PRIMARY KEY (groupid)
);

CREATE TABLE Users (
  userid    SERIAL,
  username  VARCHAR(255),
  password  VARCHAR(50),
  usergroup INTEGER,
  CONSTRAINT userPK PRIMARY KEY (userid),
  CONSTRAINT usernameUnique UNIQUE (username),
  CONSTRAINT userGroupFK FOREIGN KEY (usergroup) REFERENCES Usergroup (groupid)
);

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

CREATE TABLE Studie (
  studieid          SERIAL,
  studienavn        VARCHAR(255),
  studiebeskrivelse VARCHAR(255),
  studiekategori    INTEGER,
  CONSTRAINT studiePK PRIMARY KEY (studieid),
  CONSTRAINT kategoriFK FOREIGN KEY (studiekategori) REFERENCES Kategori (kategoriid)
);

CREATE TABLE Prosjekt (
  prosjektid          SERIAL,
  prosjektnavn        VARCHAR(100) UNIQUE,
  prosjektbeskrivelse VARCHAR(1000),
  studie              INTEGER,
  sammarbeidsbedrift  INTEGER,
  shortenedurl        VARCHAR(255),
  qrimagepath         VARCHAR(255),
  pictureurl          VARCHAR(255),
  CONSTRAINT bedriftFK FOREIGN KEY (sammarbeidsbedrift) REFERENCES Bedrift (bedriftid),
  CONSTRAINT kategoriFK FOREIGN KEY (studie) REFERENCES Studie (studieid),
  CONSTRAINT prosjektPK PRIMARY KEY (prosjektid)
);

CREATE TABLE Arrangement (
  arrangementid           SERIAL,
  arrangementnavn         VARCHAR(255),
  arrangementbeskrivelse  VARCHAR(255),
  arrangementStemmemetode INTEGER,
  arragementetutgaar      TIMESTAMP,
  CONSTRAINT arrangementPK PRIMARY KEY (arrangementid),
  CONSTRAINT stemmemetodeFK FOREIGN KEY (arrangementStemmemetode) REFERENCES StemmeMetode (metodeid)
);

CREATE TABLE ArrangementDeltagelse (
  deltagelseid SERIAL,
  arrangement  INTEGER,
  prosjekt     INTEGER,
  CONSTRAINT deltagelsePK PRIMARY KEY (deltagelseid),
  CONSTRAINT prosjektFK FOREIGN KEY (prosjekt) REFERENCES Prosjekt (prosjektid),
  CONSTRAINT arrangementFK FOREIGN KEY (arrangement) REFERENCES Arrangement (arrangementid)
);

CREATE TABLE Stemme (
  stemmeid                SERIAL,
  arrangementdeltagelseid INTEGER,
  epost                   VARCHAR(255),
  stemmeverdi             INTEGER,
  stemmetidspunkt         TIMESTAMP,
  CONSTRAINT prosjektidFK FOREIGN KEY (arrangementdeltagelseid) REFERENCES ArrangementDeltagelse (deltagelseid),
  CONSTRAINT stemmePK PRIMARY KEY (stemmeid)
);


INSERT INTO Bedrift (bedriftnavn, bedriftbeskrivelse)
VALUES ('HVL', 'Høgskolen på Vestlandet');
INSERT INTO StemmeMetode (metodenavn, metodeparameter)
VALUES ('Like', 1);
INSERT INTO Kategori (kategorinavn)
VALUES ('Data og Realfag');
INSERT INTO Studie (studienavn, studiebeskrivelse, studiekategori)
VALUES ('Informasjonsteknologi', 'Data', 1);
INSERT INTO Arrangement (arrangementnavn, arrangementbeskrivelse, arragementetutgaar)
VALUES ('Første Arrangement', 'Dette er en beskrivelse', '2019-03-17 12:07:21.827000');
INSERT INTO Prosjekt (prosjektnavn,
                      prosjektbeskrivelse,
                      studie,
                      sammarbeidsbedrift,
                      shortenedurl,
                      qrimagepath,
                      pictureurl)
VALUES ('Prosjekt navn', 'Beskrivelse', 1, 1, 'short url', 'qr path', 'picture url');
INSERT INTO ArrangementDeltagelse (arrangement, prosjekt)
VALUES (1, 1);
