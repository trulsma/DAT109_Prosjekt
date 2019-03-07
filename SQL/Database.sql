DROP SCHEMA IF EXISTS prosjekt1 CASCADE;
CREATE SCHEMA prosjekt1;
SET search_path TO prosjekt1;

CREATE TABLE Bedrift (
  bedriftid          SERIAL,
  bedriftnavn        VARCHAR(100),
  bedriftbeskrivelse VARCHAR(1000),
  CONSTRAINT uniqueBedriftNavn UNIQUE (bedriftnavn)
);

CREATE TABLE Prosjekt (
  prosjektid          SERIAL,
  prosjektnavn        VARCHAR(100),
  prosjektbeskrivelse VARCHAR(1000),
  sammarbeidsbedrift  INTEGER,
  qrcodeurl           VARCHAR(255),
  CONSTRAINT uniqueProsjektNavn UNIQUE (prosjektnavn),
  CONSTRAINT bedriftFK FOREIGN KEY (sammarbeidsbedrift) REFERENCES Bedrift (bedriftid)
);

CREATE TABLE Stemme (
  stemmeid SERIAL,
  prosjektid INTEGER,
  epost VARCHAR(255),
  stemmeverdi INTEGER,
  CONSTRAINT prosjektidFK FOREIGN KEY (prosjektid) REFERENCES Prosjekt(prosjektid)
);