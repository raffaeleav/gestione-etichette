DROP database IF EXISTS gestioneEtichette2022;
CREATE database gestioneEtichette2022;
USE gestioneEtichette2022;

CREATE TABLE Artista(
	nomeArtista char(20) not null primary key,
    dischiOro numeric not null,
    dischiPlatino numeric not null
);

CREATE TABLE Etichetta(
	trademark char(20) not null primary key,
    sede char(20) not null
);

CREATE TABLE Progetto(
	idProgetto char(15) not null primary key,
    nCanzoni numeric not null,
    nomeArtista char(20) not null,
    trademark char(20) not null,
    
    foreign key(nomeArtista) references Artista(nomeArtista),
    foreign key(trademark) references Etichetta(trademark)
);

CREATE TABLE Fisico(
	idDistributore char(15) not null primary key,
    indirizzo char(30) not null
);

CREATE TABLE Digitale(
	idDistributore char(15) not null primary key,
    indirizzo char(30) not null
);

CREATE TABLE LicenzaFisico(
	tipoLicenza char(30) not null primary key,
	idDistributore char(15) not null,
    
    foreign key(idDistributore) references Fisico(idDistributore)
);

CREATE TABLE LicenzaDigitale(
	tipoLicenza char(30) not null primary key,
	idDistributore char(15) not null,
    
    foreign key(idDistributore) references Digitale(idDistributore)
);

CREATE TABLE Consegnare(
	idDistributore char(15) not null,
    trademark char(20) not null,
    dataConsegna date not null,
    
    foreign key(idDistributore) references Fisico(idDistributore),
    foreign key(trademark) references Etichetta(trademark)
);

CREATE TABLE Fornire(
	idDistributore char(15) not null,
    trademark char(20) not null,
    dataConsegna date not null,
    
    foreign key(idDistributore) references Digitale(idDistributore),
    foreign key(trademark) references Etichetta(trademark)
);

INSERT INTO Artista VALUES
("Drake", 20, 15),
("Kanye West", 20, 17),
("John Magic", 2, 16),
("Joji", 10, 5)
;

INSERT INTO Etichetta VALUES
("Def Jam Recordings", "New York"),
("Decca", "Londra"),
("Death Row Records", "Barni Hills"),
("Capitol Records", "Los Angeles")
;

INSERT INTO Progetto VALUES
("pr00", 12, "John Magic", "Death Row Records"),
("pr01", 16, "Kanye West", "Def Jam Recordings"),
("pr02", 18, "Joji", "Decca"),
("pr04", 5, "Drake", "Capitol Records"),
("pr05", 6, "John Magic", "Def Jam Recordings"),
("pr06", 10, "John Magic", "Capitol Records"),
("pr07", 20, "Drake", "Decca"),
("pr08", 10, "Joji", "Decca"),
("pr09", 4, "John Magic", "Def Jam Recordings"),
("pr10", 16, "Drake", "Def Jam Recordings"),
("pr11", 13, "Joji", "Def Jam Recordings")
;

INSERT INTO Fisico VALUES
("df00", "221B Baker Street"),
("df01", "Mulholland Drive"),
("df02", "First Avenue"),
("df03", "Madison Avenue")
;

INSERT INTO Digitale VALUES
("dd00", "www.accent.com"),
("dd01", "www.digitalmusic.com"),
("dd02", "www.silverlakedst.com"),
("dd03", "www.silkmarketing.com")
;

INSERT INTO LicenzaFisico VALUES
("Blanket license", "df00"),
("Mechanical license", "df01"),
("Performance license", "df02"),
("Print license", "df03")
;

INSERT INTO LicenzaDigitale VALUES
("Synchronization license", "dd00"),
("Creative Commons", "dd01"),
("Principal license", "dd02"),
("Royalty-free license", "dd03")
;

INSERT INTO Consegnare VALUES
("df00", "Def Jam Recordings", "2017-11-30"),
("df01", "Decca", "2017-10-01"),
("df02", "Capitol Records", "2014-5-16"),
("df03", "Death Row Records", "2020-11-06")
;

INSERT INTO Fornire VALUES
("dd00", "Decca", "2020-4-16"),
("dd01", "Death Row Records", "2012-9-17"),
("dd02", "Def Jam Recordings", "2021-1-30"),
("dd03", "Capitol Records", "2019-4-05")
;

-- Selezione ordinata su un attributo di una tabella con condizioni AND e OR
-- Trovare gli artisti il cui nome d'arte inizia con D e i dischi d'oro sono 20 oppure gli artisti il cui nome d'arte iniza con K e i dischi d'oro sono 20, ordinando per il nome d'arte l' output
SELECT a.nomeArtista
FROM Artista a
WHERE (a.nomeArtista like "D%" and a.dischiOro = 20) or (a.nomeArtista like "K%" and a.dischiOro = 20)
ORDER BY a.nomeArtista
;

-- Selezione su due o più tabelle con condizioni
-- Trovare gli artisti e i relativi progetti con più di 10 canzoni
SELECT a.nomeArtista, p.idProgetto
FROM Artista a JOIN Progetto p ON a.nomeArtista = p.nomeArtista
WHERE p.nCanzoni > 10
;

-- Selezione aggregata su tutti i valori 
-- Trovare la somma di tutti i dischi d'oro di tutti gli artisti
SELECT sum(a.dischiOro) as sommaDischi
FROM Artista a 
;

-- Selezione aggregata su raggruppamenti 
-- Trovare la somma del numero di canzoni per ogni artista
SELECT a.nomeArtista, sum(p.nCanzoni) as sommaCanzoni
FROM Artista a join Progetto p on a.nomeArtista = p.nomeArtista
GROUP BY a.nomeArtista
;

-- Selezione aggregata su raggruppamenti con condizioni 
-- Trovare gli artisti la cui somma del numero di canzoni dei relativi progetti è maggiore di 20
SELECT a.nomeArtista, sum(p.nCanzoni) as sommaCanzoni
FROM Artista a join Progetto p on a.nomeArtista = p.nomeArtista
GROUP BY a.nomeArtista
HAVING sommaCanzoni > 20
;

-- Selezione aggregata su raggruppamenti con condizioni che includano un’altra funzione di raggruppamento 
-- Trovare gli artisti la cui somma del numero di canzoni dei relativi progetti è la più alta
CREATE VIEW totaleCanzoni as (
SELECT a.nomeArtista, sum(p.nCanzoni) as sommaCanzoni
FROM Artista a join Progetto p on a.nomeArtista = p.nomeArtista
GROUP BY a.nomeArtista
);

SELECT t.nomeArtista, t.sommaCanzoni
FROM totaleCanzoni t
WHERE t.sommaCanzoni = (
	SELECT max(t.sommaCanzoni)
    FROM totaleCanzoni t
)
;

-- Selezione con operazioni sugli insiemi (IN oppure NOT IN)
-- Trovare gli artisti che hanno creato Progetti per l'etichetta Death Row Records ma non per l'etichetta Decca
SELECT a.nomeArtista
FROM Artista a JOIN Progetto p on a.nomeArtista = p.nomeArtista
WHERE p.trademark = "Death Row Records" and a.nomeArtista not in(
	SELECT a.nomeArtista
    FROM Artista a JOIN Progetto p on a.nomeArtista = p.nomeArtista
    WHERE p.trademark = "Decca"
)
;

-- Selezione con uso appropriato del doppio not exists
-- Trovare le etichette che hanno comprato progetti da tutti gli artisti
SELECT e.trademark
FROM Etichetta e
WHERE not exists(
	SELECT *
    FROM Artista a 
    WHERE not exists(
		SELECT * 
        FROM Progetto p
        WHERE a.nomeArtista = p.nomeArtista and e.trademark = p.trademark
    )
);