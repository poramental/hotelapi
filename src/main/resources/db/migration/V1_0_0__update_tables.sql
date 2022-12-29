CREATE TABLE "hotels"(
    id UUID  NOT NULL,
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
      hotel_id UUID NOT NULL,
      CONSTRAINT pk_roomId PRIMARY KEY(hotel_id,number)



  );

CREATE TABLE "excursions"(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price numeric NOT NULL,
    hotel_id UUID NOT NULL


);

CREATE TABLE "services"(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    price numeric NOT NULL,
    hotel_id UUID NOT NULL


);

