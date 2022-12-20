CREATE TABLE "hotels"(
    id BIGINT PRIMARY KEY NOT NULL,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100) NOT NULL

);

CREATE TABLE "rooms"(
      hotel_id BIGINT NOT NULL ,
      type VARCHAR(20) NOT NULL,
      price BIGINT NOT NULL,
      description VARCHAR(255),
      number BIGINT NOT NULL,
      CONSTRAINT pk_roomId PRIMARY KEY (hotel_id, number)

  );


CREATE TABLE "services"(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    hotel_id BIGINT NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL

);

CREATE TABLE "excursions"(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    hotel_id BIGINT NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL
);