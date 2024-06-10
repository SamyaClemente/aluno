package com.example.aluno.aluno.controller;

import com.example.aluno.aluno.domain.Aluno;
import com.example.aluno.aluno.domain.AlunoCompleto;
import com.example.aluno.aluno.service.AlunoCompletoService;
import com.example.aluno.aluno.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Gerenciamento de Alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @Autowired
    private AlunoCompletoService alunoCompletoService;

    @GetMapping
    @Operation(summary = "Obter todos os alunos", description = "Retorna uma lista de todos os alunos.")
    public List<AlunoCompleto> getAlunosCompletos() {
        return alunoCompletoService.obterTodosAlunosCompletos();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obter aluno por ID", description = "Retorna um único aluno com base no ID fornecido.")
    public AlunoCompleto getAlunoCompletoPorId(@PathVariable Long id) {
        return alunoCompletoService.obterAlunoCompletoPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo aluno", description = "Cria um novo aluno com os detalhes fornecidos.")
    public ResponseEntity<Void> createAluno(@RequestBody Aluno aluno) {
        alunoService.createAluno(aluno);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os detalhes de um aluno existente com base no ID fornecido.")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
         alunoService.updateAluno(id, alunoDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Deleta um aluno existente com base no ID fornecido.")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.ok().build();
    }
}
