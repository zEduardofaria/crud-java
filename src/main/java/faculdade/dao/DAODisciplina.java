package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TODisciplina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class DAODisciplina {
    public static List<TODisciplina> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome from Disciplina ");
        sql.append(" order by nome ");

        List<TODisciplina> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TODisciplina disciplina = new TODisciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                lista.add(disciplina);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TODisciplina disciplina) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Disciplina (nome) values ");
        sql.append(" (?) ");
        Data.executeUpdate(conn, sql.toString(), disciplina.getNome());
    }

    public static void atualizar(Connection conn, TODisciplina disciplina) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Disciplina set nome = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), disciplina.getNome(), disciplina.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Disciplina");
        sql.append(" where ");
        sql.append(" id = ? ");

        Data.executeQuery(conn, sql.toString(), id);
    }
}
