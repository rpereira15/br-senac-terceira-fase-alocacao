package com.example.brsenacterceirafasealocacao.professor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProfessor is a Querydsl query type for Professor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProfessor extends EntityPathBase<Professor> {

    private static final long serialVersionUID = -380894224L;

    public static final QProfessor professor = new QProfessor("professor");

    public final DateTimePath<java.util.Date> dataNascimento = createDateTime("dataNascimento", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath sexo = createString("sexo");

    public final StringPath titulacao = createString("titulacao");

    public QProfessor(String variable) {
        super(Professor.class, forVariable(variable));
    }

    public QProfessor(Path<? extends Professor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfessor(PathMetadata metadata) {
        super(Professor.class, metadata);
    }

}

