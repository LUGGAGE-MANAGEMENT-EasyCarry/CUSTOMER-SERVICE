CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  customers(
                           id    uuid     NOT NULL  default  uuid_generate_v4(),
                           name   character varying(245)    NOT NULL,
                           email  character varying(245)   NOT NULL,
                           created_at  DATE      ,
                           password varchar(245),
                           phone_number varchar(245)      NOT NULL ,
                           PRIMARY KEY (id),
                           UNIQUE(name)
);