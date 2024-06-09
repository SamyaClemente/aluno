CREATE TABLE Alunos (
                        id_aluno SERIAL PRIMARY KEY,
                        nome_completo VARCHAR(100) NOT NULL,
                        data_nascimento DATE NOT NULL,
                        sexo CHAR(1) CHECK (sexo IN ('M', 'F', 'O')), -- 'M' para masculino, 'F' para feminino, 'O' para outro
                        endereco VARCHAR(255) NOT NULL,
                        telefone VARCHAR(15),
                        email VARCHAR(100),
                        nome_responsavel VARCHAR(100),
                        telefone_responsavel VARCHAR(15),
                        data_matricula DATE NOT NULL,
                        observacoes TEXT
);

CREATE TABLE Cursos (
                        id_curso SERIAL PRIMARY KEY,
                        nome_curso VARCHAR(100) NOT NULL,
                        descricao TEXT
);
CREATE TABLE Turmas (
                        id_turma SERIAL PRIMARY KEY,
                        nome_turma VARCHAR(50) NOT NULL,
                        id_curso INT NOT NULL REFERENCES Cursos(id_curso),
                        ano_letivo INT NOT NULL
);
CREATE TABLE Matriculas (
                            id_matricula SERIAL PRIMARY KEY,
                            id_aluno INT NOT NULL REFERENCES Alunos(id_aluno),
                            id_turma INT NOT NULL REFERENCES Turmas(id_turma),
                            data_matricula DATE NOT NULL
);
