package faculdade.bo;

import faculdade.dao.DAOUsuario;
import faculdade.fw.Data;
import faculdade.fw.DateTime;
import faculdade.fw.Encrypt;
import faculdade.to.TOUsuario;

import java.sql.Connection;
import java.util.List;

public class BOUsuario {

    public static boolean isValid(String token) throws Exception {
        try (Connection conn = Data.openConnection()) {
            TOUsuario usuario = DAOUsuario.getByToken(conn, token);

            if (usuario == null) {
                return false;
            }

            DateTime now = DateTime.now();
            return usuario.getExpiraEm().getTime() > now.getMillis()
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

    public static TOUsuario esqueciMinhaSenha(TOUsuario model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOUsuario usuario = DAOUsuario.getByEmail(conn, model);
            if (usuario != null) {

                String novaSenha = Guid.getString().substring(0, 8);

                StringBuilder message = new StringBuilder();
                message.append("Olá ").append(model.getNome()).append(",<br/><br/>");
                message.append("Aqui está sua nova senha .... ").append(novaSenha).append("<br/><br/>");
                message.append("A Faculdade");

                Email email = new Email("Esqueci minha senha - Faculdade", message.toString(), model.getEmail());
                email.start();

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

                model.setId(Guid.getString());
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

                usuario.setExpiredAt(expiredAt.getTimestamp());

                usuario.setToken(Guid.getString());
                DAOUsuario.atualizarToken(conn, usuario);
            }

            return usuario;
        }

    }
}
