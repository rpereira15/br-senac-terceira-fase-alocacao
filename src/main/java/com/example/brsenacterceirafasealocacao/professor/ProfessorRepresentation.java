package com.example.brsenacterceirafasealocacao.professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface ProfessorRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    class CreateOrUpdate {

        @NotEmpty(message = "O campo nome, não pode ser vazio")
        @NotNull(message = "O campo nome, não pode ser null")
        private String nome;
        private Date dataNascimento;
        private String sexo;
        private String titulacao;

    }


    @Data
    @Builder
    @AllArgsConstructor
    class Details {
        private Long id;
        private String nome;
        private Date dataNascimento;
        private String sexo;
        private String titulacao;

        public static Details from(Professor professor) {
            return Details.builder()
                    .id(professor.getId())
                    .nome(professor.getNome())
                    .dataNascimento(professor.getDataNascimento())
                    .sexo(professor.getSexo())
                    .titulacao(professor.getTitulacao())
                    .build();
        }
    }


    @Data
    @Builder
    @AllArgsConstructor
    class SimpleList {
        private Long id;
        private String nome;

        private static SimpleList from(Professor professor) {
            return SimpleList.builder()
                    .id(professor.getId())
                    .nome(professor.getNome())
                    .build();
        }

        public static List<SimpleList> from(List<Professor> professor) {
            return professor.stream().map(SimpleList::from)
                    .collect(Collectors.toList());
        }
    }
}
