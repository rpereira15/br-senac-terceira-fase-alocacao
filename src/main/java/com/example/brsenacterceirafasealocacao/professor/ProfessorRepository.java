package com.example.brsenacterceirafasealocacao.professor;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long>, QuerydslPredicateExecutor<Professor> {
}
