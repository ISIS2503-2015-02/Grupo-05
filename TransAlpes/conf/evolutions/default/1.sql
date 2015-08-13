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
  cliente_id                bigint,
  estacion_id               bigint,
  vcub_id                   bigint,
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

create table ubicacion (
  id                        bigint not null,
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
  id                        bigint auto_increment not null,
  tipo                      varchar(255),
  estado                    varchar(255),
  color                     varchar(255),
  estacion_id               bigint,
  linea                     varchar(255),
  temperatura               integer,
  hora                      bigint,
  panico                    boolean,
  personas_pie              integer,
  personas_discapacitadas   integer,
  constraint pk_vehiculo primary key (id))
;

create sequence ubicacion_seq;

alter table prestamo add constraint fk_prestamo_cliente_1 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_prestamo_cliente_1 on prestamo (cliente_id);
alter table prestamo add constraint fk_prestamo_estacion_2 foreign key (estacion_id) references estacion (id) on delete restrict on update restrict;
create index ix_prestamo_estacion_2 on prestamo (estacion_id);
alter table prestamo add constraint fk_prestamo_vcub_3 foreign key (vcub_id) references vehiculo (id) on delete restrict on update restrict;
create index ix_prestamo_vcub_3 on prestamo (vcub_id);
alter table reserva add constraint fk_reserva_cliente_4 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_reserva_cliente_4 on reserva (cliente_id);
alter table reserva add constraint fk_reserva_mobibus_5 foreign key (mobibus_id) references vehiculo (id) on delete restrict on update restrict;
create index ix_reserva_mobibus_5 on reserva (mobibus_id);
alter table ubicacion add constraint fk_ubicacion_vehiculo_6 foreign key (vehiculo_id) references vehiculo (id) on delete restrict on update restrict;
create index ix_ubicacion_vehiculo_6 on ubicacion (vehiculo_id);
alter table vehiculo add constraint fk_vehiculo_estacion_7 foreign key (estacion_id) references estacion (id) on delete restrict on update restrict;
create index ix_vehiculo_estacion_7 on vehiculo (estacion_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists cliente;

drop table if exists estacion;

drop table if exists informe;

drop table if exists person;

drop table if exists prestamo;

drop table if exists reserva;

drop table if exists ubicacion;

drop table if exists vehiculo;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ubicacion_seq;

