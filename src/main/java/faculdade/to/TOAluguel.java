package faculdade.to;
import com.fasterxml.jackson.annotation.JsonFormat;
import faculdade.fw.*;

import java.sql.Timestamp;

public class TOAluguel {
    private int id;
    private int idLivro;
    private int idAluno;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-3")
    private Timestamp dataCriacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
