package faculdade.bo;

import faculdade.dao.DAOUsuario;
import faculdade.fw.Data;
import faculdade.fw.DateTime;
import faculdade.fw.Encrypt;
import faculdade.fw.Guid;
import faculdade.to.TOUsuario;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.List;
import java.util.Random;

public class BOUsuario {

    public static boolean isValid(String token) throws Exception {
        try (Connection conn = Data.openConnection()) {
            TOUsuario usuario = DAOUsuario.getByToken(conn, token);

            if (usuario == null) {
                return false;
            }

            DateTime now = DateTime.now();
            boolean bool = usuario.getExpiraEm().getTime() > DateTime.now().getMillis();
            return true;
        }
    }

    public static TOUsuario meuUsuario(String token) throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOUsuario.getByToken(conn, token);
        }
    }

    public static List<TOUsuario> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOUsuario.lista(conn);
        }
    }

    private static String generateRandomPassword(int qtd) {
        byte[] array = new byte[qtd];
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }

    public static TOUsuario esqueciMinhaSenha(TOUsuario model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOUsuario usuario = DAOUsuario.getByEmail(conn, model);
            if (usuario != null) {

                String novaSenha = Guid.getString().substring(0,8);

                usuario.setSenha(Encrypt.sha1(novaSenha));
                DAOUsuario.atualizar(conn, usuario);

            }
            return usuario;

        }
    }

    public static void atualizar(TOUsuario model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOUsuario.atualizar(conn, model);
        }
    }

    public static TOUsuario inserir(TOUsuario model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOUsuario usuario = DAOUsuario.getByEmail(conn, model);
            if (usuario == null) {

                model.setSenha(Encrypt.sha1(model.getSenha()));
                DAOUsuario.inserir(conn, model);
                
                return model;
            } else {
                return null;
            }

        }
    }

    public static TOUsuario autenticar(TOUsuario model) throws Exception {

        try (Connection conn = Data.openConnection()) {

            model.setSenha(Encrypt.sha1(model.getSenha()));

            TOUsuario usuario = DAOUsuario.autenticar(conn, model);
            if (usuario != null) {

                DateTime expiredAt = DateTime.now();
                expiredAt.addMinute(5);

                usuario.setExpiraEm(expiredAt.getTimestamp());
                usuario.setToken(Guid.getString());
                DAOUsuario.atualizarToken(conn, usuario);
            }

            return usuario;
        }

    }
}
