package faculdade.to;

import java.util.ArrayList;
import java.util.List;

public class TOTurma {
    private int id;
    private int idProfessor;
    private int idDisciplina;
    private List<TOAluno> alunos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public List<TOAluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<TOAluno> alunos) {
        this.alunos = alunos;
    }
}
