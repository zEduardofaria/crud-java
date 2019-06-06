CREATE TABLE `Materia` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	`Descricao` varchar(255) NOT NULL,
	`IdDisciplina` INT NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Disciplina` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Professor` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	`Cpf` varchar(11) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Usuario` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	`Email` varchar(255) NOT NULL,
	`Senha` varchar(255) NOT NULL,
	`Token` varchar(255) NOT NULL,
	`Ativo` BOOLEAN NOT NULL,
	`CriadoEm` TIMESTAMP NOT NULL,
	`ExpiraEm` TIMESTAMP,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Livro` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	`Autor` varchar(255) NOT NULL,
	`Categoria` varchar(255) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Aluno` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`Nome` varchar(255) NOT NULL,
	`Matricula` varchar(10) NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Aluguel` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`IdLivro` BINARY NOT NULL,
	`IdAluno` INT NOT NULL,
	`DataCriacao` DATETIME NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `Turma` (
	`Id` INT NOT NULL AUTO_INCREMENT,
	`IdDisciplina` INT NOT NULL,
	`IdProfessor` INT NOT NULL,
	PRIMARY KEY (`Id`)
);

CREATE TABLE `AlunosDaTurma` (
	`IdAluno` INT NOT NULL,
	`idTurma` INT NOT NULL
);

ALTER TABLE `Materia` ADD CONSTRAINT `Materia_fk0` FOREIGN KEY (`IdDisciplina`) REFERENCES `Disciplina`(`Id`);

ALTER TABLE `Aluguel` ADD CONSTRAINT `Aluguel_fk0` FOREIGN KEY (`IdLivro`) REFERENCES `Livro`(`Id`);

ALTER TABLE `Aluguel` ADD CONSTRAINT `Aluguel_fk1` FOREIGN KEY (`IdAluno`) REFERENCES `Aluno`(`Id`);

ALTER TABLE `Turma` ADD CONSTRAINT `Turma_fk0` FOREIGN KEY (`IdDisciplina`) REFERENCES `Disciplina`(`Id`);

ALTER TABLE `Turma` ADD CONSTRAINT `Turma_fk1` FOREIGN KEY (`IdProfessor`) REFERENCES `Professor`(`Id`);

ALTER TABLE `AlunosDaTurma` ADD CONSTRAINT `AlunosDaTurma_fk0` FOREIGN KEY (`IdAluno`) REFERENCES `Aluno`(`Id`);

ALTER TABLE `AlunosDaTurma` ADD CONSTRAINT `AlunosDaTurma_fk1` FOREIGN KEY (`idTurma`) REFERENCES `Turma`(`Id`);
