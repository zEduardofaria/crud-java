package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOAluguel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOAluguel {
        public static List<TOAluguel> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idLivro, idAluno, dataCriacao from Aluguel ");
        sql.append(" order by dataCriacao desc");

        List<TOAluguel> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOAluguel aluguel = new TOAluguel();
                aluguel.setId(rs.getInt("id"))
                aluguel.setIdLivro(rs.getInt("idLivro"))
                aluguel.setIdAluno(rs.getInt("idAluno"))
                aluguel.setDataCriacao(rs.getDateTime("dataCriacao"));
                lista.add(aluguel);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOAluguel aluguel) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Aluguel (idLivro, idAluno, dataCriacao) values ");
        sql.append(" (?, ?, ?) ");
        Data.executeUpdate(conn, sql.toString(), aluguel.getIdLivro(), aluguel.getIdAluno(), aluguel.getDataCriacao());
    }

    public static void atualizar(Connection conn, TOAluguel aluguel) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Aluguel set idLivro = ?, idAluno = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), aluguel.getNome(), aluguel.getMatricula(), aluguel.getId());
    }

    public static void deletar(Connection conn, int id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Aluguel");
        sql.append(" where ");
        sql.append(" id = ? ");

        Data.executeQuery(conn, sql.toString(), id);
    }
}
