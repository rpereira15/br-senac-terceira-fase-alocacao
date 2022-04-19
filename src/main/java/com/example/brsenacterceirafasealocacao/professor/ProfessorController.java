package com.example.brsenacterceirafasealocacao.professor;

import com.example.brsenacterceirafasealocacao.exceptions.NotFoundException;
import com.example.brsenacterceirafasealocacao.util.Paginacao;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/professor")
@AllArgsConstructor
@CrossOrigin("*")
public class ProfessorController {
        private ProfessorRepository professorRepository;

        @PostMapping("/")
        public ResponseEntity<ProfessorRepresentation.Details>
        create(@Valid @RequestBody ProfessorRepresentation.CreateOrUpdate create) {
                Professor professor = Professor.builder()
                        .nome(create.getNome())
                        .sexo(create.getSexo())
                        .dataNascimento(create.getDataNascimento())
                        .titulacao(create.getTitulacao())
                        .build();

                return ResponseEntity
                        .status(201)
                        .body(ProfessorRepresentation.Details.from(this.professorRepository.save(professor)));
        }

        @PutMapping("/{idProfessor}")
        public ResponseEntity<ProfessorRepresentation.Details> update(@PathVariable Long idProfessor,
                                                                      @Valid @RequestBody ProfessorRepresentation.CreateOrUpdate update) {

                Professor professor = this.professorRepository.findOne(QProfessor.professor.id.eq(idProfessor))
                        .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

               Professor prof2 = professor.toBuilder()
                        .nome(update.getNome())
                        .sexo(update.getSexo())
                        .dataNascimento(update.getDataNascimento())
                        .titulacao(update.getTitulacao())
                        .build();

                return ResponseEntity.status(200)
                        .body(ProfessorRepresentation.Details.from(this.professorRepository.save(prof2)));
        }

        @GetMapping("/{idProfessor}")
        public ResponseEntity<ProfessorRepresentation.Details> getOne(@PathVariable Long idProfessor) {

                Professor professor = this.professorRepository.findOne(QProfessor.professor.id.eq(idProfessor))
                        .orElseThrow(() -> new NotFoundException("Professor não encontrado"));

                return ResponseEntity.ok(ProfessorRepresentation.Details.from(professor));
        }

        @GetMapping("/")
        public ResponseEntity<Paginacao> getAll(
                @QuerydslPredicate(root = Professor.class) Predicate filtroURI,
                @RequestParam(name = "tamanhoPagina", defaultValue = "30") int tamanhoPagina,
                @RequestParam(name = "paginaDesejada", defaultValue = "0")int pagina){

                Pageable paginaDesejada = PageRequest.of(pagina, tamanhoPagina);

                Page<Professor> listaProduto = Objects.isNull(filtroURI) ?
                        this.professorRepository.findAll(paginaDesejada) :
                        this.professorRepository.findAll(filtroURI, paginaDesejada);

                Paginacao paginacao = Paginacao.builder()
                        .paginaSelecionada(pagina)
                        .tamanhoPagina(tamanhoPagina)
                        .proximaPagina(listaProduto.hasNext())
                        .conteudo(ProfessorRepresentation.SimpleList.from(listaProduto.getContent()))
                        .build();
                return ResponseEntity.ok(paginacao);
        }
}
