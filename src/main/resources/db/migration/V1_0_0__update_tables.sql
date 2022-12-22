CREATE TABLE "hotels"(
    id VARCHAR(40)  NOT NULL,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100) NOT NULL,
    name VARCHAR(40) PRIMARY KEY NOT NULL,
    tour_location VARCHAR(20) NOT NULL


);

CREATE TABLE "rooms"(
      id VARCHAR(40) NOT NULL PRIMARY KEY,
      type VARCHAR(20) NOT NULL,
      price BIGINT NOT NULL,
      description VARCHAR(255),
      number BIGINT NOT NULL,
      free_tag boolean DEFAULT true,
      hotel_id VARCHAR(40) NOT NULL



  );

CREATE TABLE "excursions"(
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL,
    hotel_id VARCHAR(40) NOT NULL

);

CREATE TABLE "services"(
    id VARCHAR(40) NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL,
    hotel_id VARCHAR(40) NOT NULL

);

