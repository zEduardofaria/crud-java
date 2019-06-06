package faculdade.to;

import java.sql.Timestamp;

public class TOUsuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String token;
    private boolean ativo;
    private Timestamp criadoEm;
    private Timestamp expiraEm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Timestamp getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Timestamp criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Timestamp getExpiraEm() {
        return expiraEm;
    }

    public void setExpiraEm(Timestamp expiraEm) {
        this.expiraEm = expiraEm;
    }
}
