package faculdade.bo;

import faculdade.dao.DAOTurma;
import faculdade.fw.Data;
import faculdade.to.TOTurma;

import java.sql.Connection;
import java.util.List;

public class BOTurma {

    public static List<TOTurma> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOTurma.lista(conn);
        }
    }

    public static TOTurma inserir(TOTurma model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            DAOTurma.inserir(conn, model);
            
            return model;

        }
    }

    public static void atualizar(TOTurma model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOTurma.atualizar(conn, model);
        }
    }

    public static TOTurma deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            TOTurma turma = DAOTurma.deletar(conn, id);

            return turma;
        }
    }
}
