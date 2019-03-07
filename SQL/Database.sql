DROP SCHEMA IF EXISTS prosjekt1 CASCADE;
CREATE SCHEMA prosjekt1;
SET search_path TO prosjekt1;

CREATE TABLE Bedrift (
  bedriftid          SERIAL,
  bedriftnavn        VARCHAR(100) UNIQUE,
  bedriftbeskrivelse VARCHAR(1000),
  CONSTRAINT bedriftPK PRIMARY KEY (bedriftid)
);

CREATE TABLE Prosjekt (
  prosjektid          SERIAL,
  prosjektnavn        VARCHAR(100) UNIQUE,
  prosjektbeskrivelse VARCHAR(1000),
  sammarbeidsbedrift  INTEGER,
  qrcodeurl           VARCHAR(255),
  CONSTRAINT bedriftFK FOREIGN KEY (sammarbeidsbedrift) REFERENCES Bedrift (bedriftid),
  CONSTRAINT prosjektPK PRIMARY KEY (prosjektid)

);

CREATE TABLE Stemme (
  stemmeid    SERIAL,
  prosjektid  INTEGER,
  epost       VARCHAR(255),
  stemmeverdi INTEGER,
  CONSTRAINT prosjektidFK FOREIGN KEY (prosjektid) REFERENCES Prosjekt (prosjektid),
  CONSTRAINT stemmePK PRIMARY KEY (stemmeid)
);