package com.example.aluno.aluno.service;

import com.example.aluno.aluno.domain.Aluno;
import com.example.aluno.aluno.domain.Curso;
import com.example.aluno.aluno.domain.Matricula;
import com.example.aluno.aluno.domain.Turma;

import com.example.aluno.aluno.repository.AlunoRepository;
import com.example.aluno.aluno.repository.CursoRepository;
import com.example.aluno.aluno.repository.MatriculaRepository;
import com.example.aluno.aluno.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAllAlunos();
    }



    public ResponseEntity<Aluno> getAlunoById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public Aluno createAluno(Aluno aluno) {
        for (Matricula matricula : aluno.getMatriculas()) {
            matricula.setAluno(aluno);
            Turma turma = matricula.getTurma();

            Curso curso = turma.getCurso();
            if (curso.getId_curso() == null) {
                curso = cursoRepository.save(curso);
            }

            turma.setCurso(curso);
            if (turma.getId_turma() == null) {
                turma = turmaRepository.save(turma);
            }

            matricula.setTurma(turma);
        }

        return alunoRepository.save(aluno);
    }

    public ResponseEntity<Aluno> updateAluno(Long id, Aluno alunoDetails) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno aluno = alunoOptional.get();
            aluno.setNome_completo(alunoDetails.getNome_completo());
            aluno.setData_nascimento(alunoDetails.getData_nascimento());
            aluno.setSexo(alunoDetails.getSexo());
            aluno.setEndereco(alunoDetails.getEndereco());
            aluno.setTelefone(alunoDetails.getTelefone());
            aluno.setEmail(alunoDetails.getEmail());
            aluno.setNome_responsavel(alunoDetails.getNome_responsavel());
            aluno.setTelefone_responsavel(alunoDetails.getTelefone_responsavel());
            aluno.setData_matricula(alunoDetails.getData_matricula());
            aluno.setObservacoes(alunoDetails.getObservacoes());

            Set<Matricula> matriculasToUpdate = new HashSet<>();

            for (Matricula matricula : alunoDetails.getMatriculas()) {
                Turma turma = matricula.getTurma();
                Curso curso = turma.getCurso();

                if (curso.getId_curso() == null) {
                    curso = cursoRepository.save(curso);
                }

                turma.setCurso(curso);
                if (turma.getId_turma() == null) {
                    turma = turmaRepository.save(turma);
                }

                matricula.setTurma(turma);
                matricula.setAluno(aluno);

                // Verifica se a matrícula já existe
                if (matricula.getId_matricula() != null) {
                    matriculasToUpdate.add(matricula);
                }
            }

            // Limpa as matrículas existentes e adiciona as atualizadas
            aluno.getMatriculas().clear();
            aluno.getMatriculas().addAll(matriculasToUpdate);

            return ResponseEntity.ok(alunoRepository.save(aluno));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteAluno(Long id) {
        alunoRepository.findById(id).ifPresent(alunoRepository::delete);
    }


}
