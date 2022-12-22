CREATE TABLE "hotels"(
    id VARCHAR(40)  NOT NULL,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    address VARCHAR(100) NOT NULL,
    name VARCHAR(40) PRIMARY KEY NOT NULL,
    tour_location VARCHAR(20) NOT NULL


);

CREATE TABLE "rooms"(
      type VARCHAR(20) NOT NULL,
      price numeric NOT NULL,
      description VARCHAR(255),
      number SMALLINT NOT NULL,
      free_tag boolean DEFAULT true,
      hotel_id VARCHAR(40) NOT NULL,
      CONSTRAINT pk_roomId PRIMARY KEY(hotel_id,number)



  );

CREATE TABLE "excursions"(
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price numeric NOT NULL,
    hotel_id VARCHAR(40) NOT NULL,
    CONSTRAINT pk_excursionId PRIMARY KEY(hotel_id,name)

);

CREATE TABLE "services"(
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price numeric NOT NULL,
    hotel_id VARCHAR(40) NOT NULL,
    CONSTRAINT pk_serviceId PRIMARY KEY(hotel_id,name)

);

