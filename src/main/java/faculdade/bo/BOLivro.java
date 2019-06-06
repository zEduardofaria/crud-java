package faculdade.bo;

public class BOLivro {
    public static List<TOLivro> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOLivro.lista(conn);
        }
    }

    public static TOLivro inserir(TOLivro model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOLivro livro = DAOLivro.getByEmail(conn, model);
            if (livro != null) {
                return null;
            }

            model.setId(Guid.getString());
            DAOLivro.inserir(conn, model);
            
            return model;

        }
    }

    public static void atualizar(TOLivro model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOLivro.atualizar(conn, model);
        }
    }

    public static TOLivro deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            TOLivro livro = DAOLivro.deletar(conn, id);

            return livro;
        }
    }
}
