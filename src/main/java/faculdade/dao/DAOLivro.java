package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOLivro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOLivro {
    public static List<TOLivro> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, autor, categoria from Livro ");
        sql.append(" order by nome ");

        List<TOLivro> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOLivro livro = new TOLivro();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setCategoria(rs.getString("categoria"));
                lista.add(livro);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOLivro livro) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Livro (nome, autor, categoria) values ");
        sql.append(" (?, ?, ?) ");
        Data.executeUpdate(conn, sql.toString(), livro.getNome(), livro.getAutor(), livro.getCategoria());
    }

    public static void atualizar(Connection conn, TOLivro livro) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Livro set nome = ?, autor = ?, categoria = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), livro.getNome(), livro.getAutor(), livro.getCategoria(), livro.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Livro");
        sql.append(" where ");
        sql.append(" id = ? ");

        try (Data.executeQuery(conn, sql.toString(), id)) {

        }
    }
}
