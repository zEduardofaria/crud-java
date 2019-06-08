package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOAluno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOAluno {
        public static List<TOAluno> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, matricula from Aluno ");
        sql.append(" order by nome ");

        List<TOAluno> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOAluno aluno = new TOAluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                lista.add(aluno);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOAluno aluno) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Aluno (nome, matricula) values ");
        sql.append(" (?, ?) ");
        Data.executeUpdate(conn, sql.toString(), aluno.getNome(), aluno.getMatricula());
    }

    public static void atualizar(Connection conn, TOAluno aluno) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Aluno set nome = ?, matricula = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), aluno.getNome(), aluno.getMatricula(), aluno.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Aluno where id = ?");

        Data.executeUpdate(conn, sql.toString(), id);
    }
}
