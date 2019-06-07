package faculdade.bo;

import faculdade.dao.DAOLivro;
import faculdade.fw.Data;
import faculdade.to.TOLivro;

import java.sql.Connection;
import java.util.List;

public class BOLivro {
    public static List<TOLivro> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOLivro.lista(conn);
        }
    }

    public static TOLivro inserir(TOLivro model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOLivro.inserir(conn, model);
            return model;

        }
    }

    public static void atualizar(TOLivro model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOLivro.atualizar(conn, model);
        }
    }

    public static void deletar(int id) throws Exception {

        try (Connection conn = Data.openConnection()) {
            DAOLivro.deletar(conn, id);
        }
    }
}
