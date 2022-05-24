CREATE TABLE MP_Part (
    Part_ID int NOT NULL PRIMARY KEY,
    Repaired boolean NOT NULL,
    Station_ID int NOT NULL,
    Date_Time_Assembled TIMESTAMP NOT NULL,
    Station_Color VARCHAR(30) NOT NULL,
    FOREIGN KEY (Station_ID) REFERENCES MP_Station(Station_ID)
);