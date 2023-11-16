/*
    Apenas anexei como criei as tabelas para facilitar sua análise willian, mas
*/

CREATE TABLE Curso (
    idCurso INT AUTO_INCREMET PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    duracao_horas INT
);

CREATE TABLE Estudante (
    idEstudante INT AUTO_INCREMET PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT,
    email VARCHAR(255) UNIQUE
);

CREATE TABLE CursoEstudante (
    idCurso INT,
    idEstudante INT,
    PRIMARY KEY (idCurso, idEstudante),
    FOREIGN KEY (idCurso) REFERENCES Curso(idCurso),
    FOREIGN KEY (idEstudante) REFERENCES Estudante(idEstudante)
);