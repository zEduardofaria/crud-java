package faculdade.bo;

import faculdade.dao.DAOAluguel;
import faculdade.fw.Data;
import faculdade.to.TOAluguel;

import java.sql.Connection;
import java.util.List;

public class BOAluguel {

    public static List<TOAluguel> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOAluguel.lista(conn);
        }
    }

    public static TOAluguel inserir(TOAluguel model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOAluguel.inserir(conn, model);
            
            return model;
        }
    }

    public static void atualizar(TOAluguel model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOAluguel.atualizar(conn, model);
        }
    }

    public static void deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            DAOAluguel.deletar(conn, id);
        }
    }
}
