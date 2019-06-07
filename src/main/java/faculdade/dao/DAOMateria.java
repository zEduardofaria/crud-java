package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOMateria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOMateria {
        public static List<TOMateria> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, descricao, idDiscplina from Materia ");
        sql.append(" order by nome desc");

        List<TOMateria> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOMateria materia = new TOMateria();
                materia.setId(rs.getInt("id"));
                materia.setNome(rs.getString("nome"));
                materia.setDescricao(rs.getString("descricao"));
                materia.setIdDiscplina(rs.getInt("idDiscplina"));
                lista.add(materia);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOMateria materia) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Materia (nome, descricao, idDiscplina) values ");
        sql.append(" (?, ?, ?) ");
        Data.executeUpdate(conn, sql.toString(), materia.getNome(), materia.getDescricao(), materia.getIdDiscplina());
    }

    public static void atualizar(Connection conn, TOMateria materia) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Materia set nome = ?, descricao = ?, idDiscplina = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), materia.getNome(), materia.getDescricao(), materia.getIdDiscplina(), materia.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Materia");
        sql.append(" where ");
        sql.append(" id = ? ");

        Data.executeQuery(conn, sql.toString(), id);
    }
}
