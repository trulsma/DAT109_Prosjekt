DROP SCHEMA IF EXISTS prosjekt1 CASCADE;
CREATE SCHEMA prosjekt1;
SET search_path TO prosjekt1;

CREATE TABLE Usergroup
(
  groupid    SERIAL,
  groupname  VARCHAR(255),
  grouplevel INTEGER,
  CONSTRAINT groupPK PRIMARY KEY (groupid)
);

CREATE TABLE Users
(
  userid    SERIAL,
  username  VARCHAR(255),
  password  VARCHAR(255),
  usergroup INTEGER NOT NULL DEFAULT 3,
  expired   BOOLEAN,
  ipadress  VARCHAR(50),
  CONSTRAINT userPK PRIMARY KEY (userid),
  CONSTRAINT userGroupFK FOREIGN KEY (usergroup) REFERENCES Usergroup (groupid)
);

CREATE TABLE Bedrift
(
  bedriftid          SERIAL,
  bedriftnavn        VARCHAR(100) UNIQUE,
  bedriftbeskrivelse VARCHAR(1000),
  CONSTRAINT bedriftPK PRIMARY KEY (bedriftid)
);

CREATE TABLE StemmeMetode
(
  metodeid        SERIAL,
  metodenavn      VARCHAR(25),
  metodeparameter INTEGER,
  CONSTRAINT metodePK PRIMARY KEY (metodeid),
  CONSTRAINT metodenavnUnique UNIQUE (metodenavn)
);

CREATE TABLE Kategori
(
  kategoriid   SERIAL,
  kategorinavn VARCHAR(255),
  CONSTRAINT kategoriPK PRIMARY KEY (kategoriid),
  CONSTRAINT uniqueKategorinavn UNIQUE (kategorinavn)
);

CREATE TABLE Studie
(
  studieid          SERIAL,
  studienavn        VARCHAR(255),
  studiebeskrivelse VARCHAR(255),
  studiekategori    INTEGER,
  CONSTRAINT studiePK PRIMARY KEY (studieid),
  CONSTRAINT kategoriFK FOREIGN KEY (studiekategori) REFERENCES Kategori (kategoriid)
);

CREATE TABLE Prosjekt
(
  prosjektid          SERIAL,
  prosjektnavn        VARCHAR(100) UNIQUE,
  prosjektbeskrivelse VARCHAR(1000),
  studie              INTEGER,
  sammarbeidsbedrift  INTEGER,
  shortenedurl        VARCHAR(255),
  qrimagepath         VARCHAR(255),
  pictureurl          VARCHAR(255),
  backgroundurl       VARCHAR(255),
  prosjektuser        INTEGER,

  CONSTRAINT bedriftFK FOREIGN KEY (sammarbeidsbedrift) REFERENCES Bedrift (bedriftid),
  CONSTRAINT kategoriFK FOREIGN KEY (studie) REFERENCES Studie (studieid),
  CONSTRAINT prosjektPK PRIMARY KEY (prosjektid),
  CONSTRAINT userFk FOREIGN KEY (prosjektuser) REFERENCES Users (userid)
);

CREATE TABLE Arrangement
(
  arrangementid           SERIAL,
  arrangementnavn         VARCHAR(255),
  arrangementbeskrivelse  VARCHAR(255),
  arrangementStemmemetode INTEGER,
  arragementetutgaar      TIMESTAMP,
  CONSTRAINT arrangementPK PRIMARY KEY (arrangementid),
  CONSTRAINT stemmemetodeFK FOREIGN KEY (arrangementStemmemetode) REFERENCES StemmeMetode (metodeid)
);

CREATE TABLE ArrangementDeltagelse
(
  deltagelseid SERIAL,
  arrangement  INTEGER,
  prosjekt     INTEGER,
  CONSTRAINT deltagelsePK PRIMARY KEY (deltagelseid),
  CONSTRAINT prosjektFK FOREIGN KEY (prosjekt) REFERENCES Prosjekt (prosjektid),
  CONSTRAINT arrangementFK FOREIGN KEY (arrangement) REFERENCES Arrangement (arrangementid)
);

CREATE TABLE Stemme
(
  stemmeid                SERIAL,
  arrangementdeltagelseid INTEGER,
  epost                   VARCHAR(255),
  stemmeverdi             INTEGER,
  stemmetidspunkt         TIMESTAMP,
  CONSTRAINT prosjektidFK FOREIGN KEY (arrangementdeltagelseid) REFERENCES ArrangementDeltagelse (deltagelseid),
  CONSTRAINT stemmePK PRIMARY KEY (stemmeid)
);

INSERT INTO Usergroup (groupname, grouplevel)
VALUES ('Admin', 1),
       ('Stand', 2),
       ('User', 3);
INSERT INTO Users (username, password, usergroup, expired)
VALUES ('admin', 'admin', 1, false);
INSERT INTO Bedrift (bedriftnavn, bedriftbeskrivelse)
VALUES ('HVL', 'Høgskolen på Vestlandet');
INSERT INTO StemmeMetode (metodenavn, metodeparameter)
VALUES ('Like', 1);


INSERT INTO Kategori (kategorinavn)
VALUES ('Helse og funksjon'),
       ('Helse og omsorgsvitenskap'),
       ('Velferd og deltaking'),
       ('Dykkerutdanninga'),
       ('Bio- og Kjemiingenørfag'),
       ('Byggfag'),
       ('Data- og Realfag'),
       ('Elektrofag'),
       ('Maskin- og Marinfag'),
       ('Idrett, Kosthold og naturfag'),
       ('Kunstfag'),
       ('Pedagogikk, Religion og samfunnsfag'),
       ('Maritime studium'),
       ('Samfunnsvitenskap');


INSERT INTO Studie (studienavn, studiebeskrivelse, studiekategori)
VALUES ('Ergoterapi', 'Ergoterapi', 1),
       ('Fysioterapi', 'Fysio', 1),
       ('Radiografi', 'Radiografi', 1),
       ('Sykepleien', 'Sykepleie', 2),
       ('Anestesisjukepleie', 'anestesi', 2),
       ('Barnesjukepleie', 'Barnesjukeplein', 2),
       ('Kardiologisk sjukepleie', 'Kardiologi', 2),
       ('Barnevern', 'Barnevern', 3),
       ('Sosialt arbeid', 'Sosialt arbeid', 3),
       ('Vernepleie', 'Vernepleie', 3),
       ('Psykisk helse- og rusarbeid', 'Psykisk og rus', 3),
       ('Barnevern', 'Barnevern', 3),
       ('Yrkesdykking', 'yrkesdykking', 4),
       ('Redningsdykking', 'redning', 4),
       ('Bioingenør', 'Bioingenør', 5),
       ('Kjemiingenør', 'kjemi', 5),
       ('Medisinske laboratorianalysar', 'lab', 5),
       ('Areal og eigendom', 'areal', 6),
       ('Byggingenør', 'Byggindenør', 6),
       ('Dataingenør', 'Dataingenør', 7),
       ('Informasjonsteknologi', 'Informasjonsteknologi', 7),
       ('Programvareutvikling', 'Programvareutvikling', 7),
       ('Computer Science', 'Phd', 7),
       ('Automatisering med robotikk', 'robotikk', 8),
       ('Elektronikkingenør', 'Elektronikkingenør', 8),
       ('Elkraftteknikk', 'Elkraft', 8),
       ('Kommunikasjonsingenør', 'Kommunikasjonsingenør', 8),
       ('Energiteknologi', 'Energi', 9),
       ('Havteknologi', 'hav', 9),
       ('Marineteknikk', 'Marine', 9),
       ('Idrett, kosthold og naturfag', 'naturfag', 10),
       ('Kunstfag', 'kunst', 11),
       ('Pedagogikk, religion og samfunnsfag', 'samfunnsfag', 12),
       ('Nautikk', 'nautikk', 13),
       ('Nautikk y-veg', 'nautikk', 13),
       ('Maritime operasjoner', 'nautikk', 13),
       ('Samfunnsvitenskap', 'samfunnsvitenskap', 14);


INSERT INTO Arrangement (arrangementnavn, arrangementbeskrivelse, arragementetutgaar)
VALUES ('Første Arrangement', 'Dette er en beskrivelse', '2019-03-17 12:07:21.827000');
INSERT INTO Prosjekt (prosjektnavn,
                      prosjektbeskrivelse,
                      studie,
                      sammarbeidsbedrift,
                      shortenedurl,
                      qrimagepath,
                      pictureurl,
                      backgroundurl,
                      prosjektuser)
VALUES ('Prosjekt navn', 'Beskrivelse', 1, 1, 'short url', 'qr path', 'images/expo-logo-transparent.png', 'images/background.png', 1);
INSERT INTO ArrangementDeltagelse (arrangement, prosjekt)
VALUES (1, 1);
