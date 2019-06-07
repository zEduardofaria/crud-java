package faculdade.bo;

import faculdade.dao.DAOAluno;
import faculdade.fw.Data;
import faculdade.to.TOAluno;

import java.sql.Connection;
import java.util.List;

public class BOAluno {

    public static List<TOAluno> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOAluno.lista(conn);
        }
    }

    public static TOAluno inserir(TOAluno model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOAluno.inserir(conn, model);
            
            return model;
        }
    }

    public static void atualizar(TOAluno model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOAluno.atualizar(conn, model);
        }
    }

    public static void deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            DAOAluno.deletar(conn, id);
        }
    }
}
