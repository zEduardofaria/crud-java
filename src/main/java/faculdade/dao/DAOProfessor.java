package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOProfessor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOProfessor {
    public static List<TOProfessor> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, cpf from Professor ");
        sql.append(" order by nome ");

        List<TOProfessor> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOProfessor professor = new TOProfessor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setNome(rs.getString("cpf"));
                lista.add(professor);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOProfessor professor) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Professor (nome, cpf) values ");
        sql.append(" (?, ?) ");
        Data.executeUpdate(conn, sql.toString(), professor.getNome());
    }

    public static void atualizar(Connection conn, TOProfessor professor) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Professor set nome = ?, cpf = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), professor.getNome(), professor.getCpf(), professor.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Professor");
        sql.append(" where ");
        sql.append(" id = ? ");

        Data.executeQuery(conn, sql.toString(), id);
    }
}
