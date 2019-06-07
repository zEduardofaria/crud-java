package faculdade.bo;

import faculdade.dao.DAOMateria;
import faculdade.fw.Data;
import faculdade.to.TOMateria;

import java.sql.Connection;
import java.util.List;

public class BOMateria {

    public static List<TOMateria> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOMateria.lista(conn);
        }
    }

    public static TOMateria inserir(TOMateria model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOMateria.inserir(conn, model);
            
            return model;
        }
    }

    public static void atualizar(TOMateria model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOMateria.atualizar(conn, model);
        }
    }

    public static void deletar(int id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            DAOMateria.deletar(conn, id);
        }
    }
}
