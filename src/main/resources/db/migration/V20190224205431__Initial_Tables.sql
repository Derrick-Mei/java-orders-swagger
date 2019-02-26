CREATE TABLE IF NOT EXISTS agents (
    agentcode   INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    agentname   STRING,
    workingarea STRING,
    commission  DOUBLE,
    phone       STRING,
    country     STRING
);

CREATE TABLE IF NOT EXISTS customers (
    custcode       INTEGER NOT NULL
                           PRIMARY KEY AUTOINCREMENT,
    custname       STRING  NOT NULL,
    custcity       STRING,
    workingarea    STRING,
    custcountry    STRING,
    grade          STRING,
    openingamt     DOUBLE,
    receiveamt     DOUBLE,
    paymentamt     DOUBLE,
    outstandingamt DOUBLE,
    phone          STRING,
    agentcode      INTEGER REFERENCES agents (agentcode)
);

CREATE TABLE IF NOT EXISTS orders (
    ordnum         INTEGER PRIMARY KEY AUTOINCREMENT,
    ordamount      DOUBLE,
    advanceamount  DOUBLE,
    custcode       INTEGER REFERENCES customers (custcode),
    agentcode      INTEGER REFERENCES agents (agentcode),
    orddescription STRING
);
