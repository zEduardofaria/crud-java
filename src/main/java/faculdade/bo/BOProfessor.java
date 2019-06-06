package faculdade.bo;

public class BOProfessor {
    public static List<TOProfessor> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOProfessor.lista(conn);
        }
    }

    public static TOProfessor inserir(TOProfessor model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOProfessor professor = DAOProfessor.getByEmail(conn, model);
            if (professor != null) {
                return null;
            }

            model.setId(Guid.getString());
            DAOProfessor.inserir(conn, model);
            
            return model;

        }
    }

    public static void atualizar(TOProfessor model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOProfessor.atualizar(conn, model);
        }
    }

    public static TOProfessor deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            TOProfessor professor = DAOProfessor.deletar(conn, id);

            return professor;
        }
    }
}
