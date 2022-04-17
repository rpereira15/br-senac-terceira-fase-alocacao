CREATE SCHEMA IF NOT EXISTS terceira_fase;

create table professor(
      id serial not null primary key,
      nome varchar(100) not null,
      nascimento date not null,
      sexo char(1) not null,
      titulacao char(1) not null
);

create table curso(
      id serial not null primary key,
      nome varchar(100) not null,
      turno char(1) not null,
      id_coordenador int not null,
      carga int not null,
      constraint fk_curso_professor foreign key(id_coordenador)
          references professor(id)
);

create table disciplina(
    id serial not null primary key,
    nome varchar(100) not null,
    fase int not null,
    id_professor int not null,
    id_curso int not null,
    constraint fk_disc_professor foreign key(id_professor)
        references professor(id),
    constraint fk_disc_curso foreign key(id_curso)
        references curso(id)
)