package faculdade.bo;

import faculdade.dao.DAOProfessor;
import faculdade.fw.Data;
import faculdade.to.TOProfessor;

import java.sql.Connection;
import java.util.List;

public class BOProfessor {
    public static List<TOProfessor> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOProfessor.lista(conn);
        }
    }

    public static TOProfessor inserir(TOProfessor model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOProfessor.inserir(conn, model);
            
            return model;

        }
    }

    public static void atualizar(TOProfessor model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOProfessor.atualizar(conn, model);
        }
    }

    public static void deletar(int id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            DAOProfessor.deletar(conn, id);
        }
    }
}
