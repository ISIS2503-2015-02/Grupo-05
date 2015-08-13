# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  telefono                  integer,
  tarjeta_bancaria          varchar(255),
  constraint pk_cliente primary key (id))
;

create table estacion (
  id                        bigint auto_increment not null,
  capacidad                 integer,
  latitud                   bigint,
  longitud                  bigint,
  disponibles               integer,
  constraint pk_estacion primary key (id))
;

create table informe (
  id                        bigint auto_increment not null,
  numero_gravedad           integer,
  tipo                      varchar(255),
  contenido                 varchar(255),
  constraint pk_informe primary key (id))
;

create table person (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create table prestamo (
  id                        bigint auto_increment not null,
  linea                     timestamp,
  constraint pk_prestamo primary key (id))
;

create table reserva (
  id                        bigint auto_increment not null,
  estado                    varchar(255),
  fecha                     bigint,
  cliente_id                bigint,
  mobibus_id                bigint,
  constraint pk_reserva primary key (id))
;

create table sistema_principal (
  nombre                    varchar(255))
;

create table ubicacion (
  id                        bigint not null,
  latitud                   bigint,
  longitud                  bigint,
  hora                      bigint,
  estado                    varchar(255),
  kilometraje               integer,
  constraint pk_ubicacion primary key (id))
;

create table vehiculo (
  dtype                     varchar(10) not null,
  id                        bigint auto_increment not null,
  estado                    varchar(255),
  color                     varchar(255),
  linea                     varchar(255),
  temperatura               integer,
  hora                      bigint,
  panico                    boolean,
  personas_pie              integer,
  personas_discapacitadas   integer,
  constraint pk_vehiculo primary key (id))
;

create sequence ubicacion_seq;

alter table reserva add constraint fk_reserva_cliente_1 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_reserva_cliente_1 on reserva (cliente_id);
alter table reserva add constraint fk_reserva_mobibus_2 foreign key (mobibus_id) references vehiculo (id) on delete restrict on update restrict;
create index ix_reserva_mobibus_2 on reserva (mobibus_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cliente;

drop table if exists estacion;

drop table if exists informe;

drop table if exists person;

drop table if exists prestamo;

drop table if exists reserva;

drop table if exists sistema_principal;

drop table if exists ubicacion;

drop table if exists vehiculo;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ubicacion_seq;

