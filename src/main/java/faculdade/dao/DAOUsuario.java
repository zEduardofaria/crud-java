package faculdade.dao;

import faculdade.fw.Data;
import faculdade.to.TOUsuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    
    public static TOUsuario getByToken(Connection conn, String token) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, email, senha, token, criadoEm, ativo, expiraEm from account ");
        sql.append(" where ");
        sql.append(" token = ? ");
        sql.append(" and ativo ");

        try (ResultSet rs = Data.executeQuery(conn, sql.toString(), token)) {

            if (rs.next()) {
                TOUsuario usuario = new TOUsuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setToken(rs.getString("token"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setCriadoEm(rs.getTimestamp("criadoEm"));
                usuario.setExpiraEm(rs.getTimestamp("expiraEm"));
                return usuario;
            } else {
                return null;
            }

        }
    }

    public static TOUsuario getByEmail(Connection conn, TOUsuario model) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, email, senha, token, criadoEm, ativo, expiraEm from account ");
        sql.append(" where ");
        sql.append(" email = ? ");
        sql.append(" and ativo ");

        try (ResultSet rs = Data.executeQuery(conn, sql.toString(), model.getEmail())) {

            if (rs.next()) {
                TOUsuario usuario = new TOUsuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setToken(rs.getString("token"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setCriadoEm(rs.getTimestamp("criadoEm"));
                usuario.setExpiraEm(rs.getTimestamp("expiraEm"));
                return usuario;
            } else {
                return null;
            }
        }
    }

    public static List<TOUsuario> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, email, senha, token, criadoEm, ativo, expiraEm from account ");
        sql.append(" where ");
        sql.append(" ativo ");
        sql.append(" order by nome ");

        List<TOUsuario> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOUsuario usuario = new TOUsuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setToken(rs.getString("token"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setCriadoEm(rs.getTimestamp("criadoEm"));
                lista.add(usuario);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOUsuario usuario) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into account(id, nome, email, senha, criadoEm, ativo) values ");
        sql.append(" (?, ?, ?, ?, now(), true) ");
        Data.executeUpdate(conn, sql.toString(), usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    public static void atualizar(Connection conn, TOUsuario usuario) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update account set nome = ?, email = ?, senha = ? ");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getId());
    }

    public static TOUsuario autenticar(Connection conn, TOUsuario usuario) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, nome, email, senha, token, criadoEm, ativo, expiraEm from account ");
        sql.append(" where ");
        sql.append(" email = ? and senha = ? ");
        sql.append(" and ativo ");

        try (ResultSet rs = Data.executeQuery(conn, sql.toString(), usuario.getEmail(), usuario.getSenha())) {

            if (rs.next()) {
                usuario = new TOUsuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setToken(rs.getString("token"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setCriadoEm(rs.getTimestamp("criadoEm"));
                return usuario;
            } else {
                return null;
            }

        }

    }

    public static void updateToken(Connection conn, TOUsuario usuario) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" update account ");
        sql.append(" set token = ?, expiraEm = ? ");
        sql.append(" where id = ? ");

        Data.executeUpdate(conn, sql.toString(), usuario.getToken(), usuario.getExpiraEm()iraEm(), usuario.getId());
    }
}
