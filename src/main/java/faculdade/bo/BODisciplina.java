package faculdade.bo;

import faculdade.dao.DAODisciplina;
import faculdade.to.TODisciplina;
import faculdade.fw.Data;
import java.sql.Connection;
import java.util.List;

public class BODisciplina {
    public static List<TODisciplina> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAODisciplina.lista(conn);
        }
    }

    public static TODisciplina inserir(TODisciplina model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAODisciplina.inserir(conn, model);
            return model;
        }
    }

    public static void atualizar(TODisciplina model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAODisciplina.atualizar(conn, model);
        }
    }

    public static TODisciplina deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            TODisciplina disciplina = DAODisciplina.deletar(conn, id);

            return disciplina;
        }
    }
}
