# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
<<<<<<< HEAD
  id                        bigint auto_increment not null,
=======
  id                        varchar(255) not null,
>>>>>>> origin/master
  name                      varchar(255),
  constraint pk_person primary key (id))
;

<<<<<<< HEAD
=======
create sequence person_seq;

>>>>>>> origin/master



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists person;

SET REFERENTIAL_INTEGRITY TRUE;

<<<<<<< HEAD
=======
drop sequence if exists person_seq;

>>>>>>> origin/master
