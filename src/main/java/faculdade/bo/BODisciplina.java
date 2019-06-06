package faculdade.bo;

public class BODisciplina {
    public static List<TODisciplina> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAODisciplina.lista(conn);
        }
    }

    public static TODisciplina inserir(TODisciplina model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TODisciplina disciplina = DAODisciplina.getByEmail(conn, model);
            if (disciplina != null) {
                return null;
            }

            model.setId(Guid.getString());
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
