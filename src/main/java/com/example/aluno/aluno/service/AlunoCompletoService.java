package com.example.aluno.aluno.service;

import com.example.aluno.aluno.domain.AlunoCompleto;
import com.example.aluno.aluno.repository.AlunoCompletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoCompletoService {

    @Autowired
    private AlunoCompletoRepository alunoCompletoRepository;

    public List<AlunoCompleto> obterTodosAlunosCompletos() {
        return alunoCompletoRepository.findAll();
    }

    public AlunoCompleto obterAlunoCompletoPorId(Long id) {
        return alunoCompletoRepository.findByIdAluno(id);
    }
}
