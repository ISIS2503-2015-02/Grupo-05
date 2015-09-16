# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                        bigserial not null,
  name                      varchar(255),
  telefono                  integer,
  tarjeta_bancaria          varchar(255),
  constraint pk_cliente primary key (id))
;

create table estacion (
  id                        bigserial not null,
  capacidad                 integer,
  latitud                   bigint,
  longitud                  bigint,
  disponibles               integer,
  constraint pk_estacion primary key (id))
;

create table informe (
  id                        bigserial not null,
  numero_gravedad           integer,
  tipo                      varchar(255),
  contenido                 varchar(255),
  constraint pk_informe primary key (id))
;

create table person (
  id                        bigserial not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create table prestamo (
  id                        bigserial not null,
  fecha                     timestamp,
  cliente_id                bigint,
  vehiculo_id               bigint,
  constraint pk_prestamo primary key (id))
;

create table reserva (
  id                        bigserial not null,
  estado                    varchar(255),
  fecha                     bigint,
  cliente_id                bigint,
  vehiculo_id               bigint,
  constraint pk_reserva primary key (id))
;

create table ubicacion (
  id                        bigserial not null,
  latitud                   bigint,
  longitud                  bigint,
  hora                      bigint,
  estado                    varchar(255),
  kilometraje               integer,
  vehiculo_id               bigint,
  constraint pk_ubicacion primary key (id))
;

create table vehiculo (
  dtype                     varchar(10) not null,
  id                        bigserial not null,
  estado                    varchar(255),
  tipo                      varchar(255),
  color                     varchar(255),
  linea                     varchar(255),
  temperatura               integer,
  hora                      bigint,
  panico                    boolean,
  personas_pie              integer,
  personas_discapacitadas   integer,
  constraint pk_vehiculo primary key (id))
;

alter table prestamo add constraint fk_prestamo_cliente_1 foreign key (cliente_id) references cliente (id);
create index ix_prestamo_cliente_1 on prestamo (cliente_id);
alter table prestamo add constraint fk_prestamo_vehiculo_2 foreign key (vehiculo_id) references vehiculo (id);
create index ix_prestamo_vehiculo_2 on prestamo (vehiculo_id);
alter table reserva add constraint fk_reserva_cliente_3 foreign key (cliente_id) references cliente (id);
create index ix_reserva_cliente_3 on reserva (cliente_id);
alter table reserva add constraint fk_reserva_vehiculo_4 foreign key (vehiculo_id) references vehiculo (id);
create index ix_reserva_vehiculo_4 on reserva (vehiculo_id);
alter table ubicacion add constraint fk_ubicacion_vehiculo_5 foreign key (vehiculo_id) references vehiculo (id);
create index ix_ubicacion_vehiculo_5 on ubicacion (vehiculo_id);



# --- !Downs

drop table if exists cliente cascade;

drop table if exists estacion cascade;

drop table if exists informe cascade;

drop table if exists person cascade;

drop table if exists prestamo cascade;

drop table if exists reserva cascade;

drop table if exists ubicacion cascade;

drop table if exists vehiculo cascade;

