CREATE TABLE airport
(
    airport_code         text                                 NOT NULL,
    name                 text                                 NOT NULL,
    city                 text                                 NOT NULL,
    country_code         text                                 NOT NULL,
    date_created         timestamp without time zone          NOT NULL,
    last_updated         timestamp without time zone          NOT NULL,
    CONSTRAINT airport_pkey PRIMARY KEY (airport_code)
);

--Some test data
INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('TOR', 'Toronto International Airport','Toronto','CA',current_timestamp,current_timestamp);

INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('BER', 'Berlin International Airport','Berlin','DE',current_timestamp,current_timestamp);

INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('HKG', 'Honkong International Airport','Honkong','CH',current_timestamp,current_timestamp);

INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('DOH', 'Doha International Airport','Doha','QT',current_timestamp,current_timestamp);

INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('BLR', 'Bengaluru International Airport','Bengaluru','IN',current_timestamp,current_timestamp);

INSERT INTO airport (airport_code, name, city,country_code,date_created,last_updated)
VALUES ('LAX', 'Los-Angeles International Airport','Los-Angeles','US',current_timestamp,current_timestamp);

CREATE TABLE airline
(
    airline_code         text                                 NOT NULL,
    name                 text                                 NOT NULL,
    country_code         text                                 NOT NULL,
    date_created         timestamp without time zone          NOT NULL,
    last_updated         timestamp without time zone          NOT NULL,
    CONSTRAINT airline_pkey PRIMARY KEY (airline_code)
);

--Some test data
INSERT INTO airline (airline_code, name, country_code,date_created,last_updated)
VALUES ('CP', 'Cathey Pacific','US',current_timestamp,current_timestamp);

INSERT INTO airline (airline_code, name, country_code,date_created,last_updated)
VALUES ('SA', 'Singapore Airline','MA',current_timestamp,current_timestamp);

INSERT INTO airline (airline_code, name, country_code,date_created,last_updated)
VALUES ('TA', 'Thai Airways','TH',current_timestamp,current_timestamp);

CREATE TABLE route
(
    uuid                             uuid                        NOT NULL,
    airline_code                     character(3)                NOT NULL,
    departure_airport_code           character(3)                NOT NULL,
    arrival_airport_code             character(3)                NOT NULL,
    departure_date                   timestamp with time zone    NOT NULL,
    arrival_date                     timestamp with time zone    NOT NULL,
    cost_in_euro                     double precision            NOT NULL,
    date_created                     timestamp without time zone NOT NULL,
    last_updated                     timestamp without time zone NOT NULL,
    CONSTRAINT route_pkey PRIMARY KEY (uuid),
    CONSTRAINT fk_airline_code FOREIGN KEY (airline_code)
        REFERENCES airline (airline_code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_dep_airport_code FOREIGN KEY (departure_airport_code)
        REFERENCES airport (airport_code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_arr_airport_code FOREIGN KEY (arrival_airport_code)
        REFERENCES airport (airport_code) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE INDEX routes_dep_airport_code_idx ON route (departure_airport_code);
CREATE INDEX routes_arr_airport_code_idx ON route (arrival_airport_code);
CREATE INDEX routes_departure_date_idx ON route (departure_date);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00001', 'CP','TOR','BER','2021-01-01 06:00:00.000000','2021-01-01 15:45:00.000000','700',current_timestamp,current_timestamp);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00002', 'SA','BER','HKG','2021-01-01 06:00:00.000000','2021-01-02 03:30:00.000000','1100',current_timestamp,current_timestamp);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00003', 'TA','BER','DOH','2021-01-01 18:00:00.000000','2021-01-02 04:00:00.000000','350',current_timestamp,current_timestamp);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00004', 'SA','DOH','BLR','2021-01-02 06:00:00.000000','2021-01-02 13:0030:00.000000','300',current_timestamp,current_timestamp);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00005', 'CP','TOR','LAX','2021-01-01 06:00:00.000000','2021-01-01 016:00:00.000000','450',current_timestamp,current_timestamp);

INSERT INTO route (uuid, airline_code, departure_airport_code, arrival_airport_code, departure_date , arrival_date, cost_in_euro, date_created,last_updated)
VALUES ('5c8bb7ea-5b9a-aa5a-2f11-7fdccfc00006', 'TA','BER','BLR','2021-01-01 18:23:00.000000','2021-01-02 15:15:00.000000','700',current_timestamp,current_timestamp);