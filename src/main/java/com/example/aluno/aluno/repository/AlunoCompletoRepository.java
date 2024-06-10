package com.example.aluno.aluno.repository;

import com.example.aluno.aluno.domain.Aluno;
import com.example.aluno.aluno.domain.AlunoCompleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlunoCompletoRepository extends JpaRepository<AlunoCompleto, Long> {
    AlunoCompleto findByIdAluno(Long id);
}


