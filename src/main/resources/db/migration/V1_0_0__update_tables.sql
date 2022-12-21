CREATE TABLE "hotels"(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100) NOT NULL,
    name VARCHAR(40) NOT NULL

);

CREATE TABLE "rooms"(
      hotel_id VARCHAR(40) NOT NULL ,
      type VARCHAR(20) NOT NULL,
      price BIGINT NOT NULL,
      description VARCHAR(255),
      number BIGINT NOT NULL,
      free_mark boolean DEFAULT true NOT NULL,
      CONSTRAINT pk_roomId PRIMARY KEY (hotel_id, number)

  );

CREATE TABLE "excursions"(
    id VARCHAR(40) NOT NULL,
    name VARCHAR(60) NOT NULL,
    hotel_id VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL,
    CONSTRAINT pk_excursionId PRIMARY KEY (hotel_id, id)
);

CREATE TABLE "services"(
    id VARCHAR(40) NOT NULL,
    name VARCHAR(60) NOT NULL,
    hotel_id VARCHAR(40) NOT NULL,
    description VARCHAR(255),
    price BIGINT NOT NULL,
    CONSTRAINT pk_serviceId PRIMARY KEY (hotel_id, id)
);

