package faculdade.bo;

public class BOAluno {

    public static List<TOAluno> lista() throws Exception {
        try (Connection conn = Data.openConnection()) {
            return DAOAluno.lista(conn);
        }
    }

    public static TOAluno inserir(TOAluno model) throws Exception {
        try (Connection conn = Data.openConnection()) {

            TOAluno aluno = DAOAluno.getByEmail(conn, model);
            if (aluno != null) {
                return null;
            }

            model.setId(Guid.getString());
            model.setSenha(Encrypt.sha1(model.getSenha()));
            DAOAluno.inserir(conn, model);
            
            return model;

        }
    }

    public static void atualizar(TOAluno model) throws Exception {
        try (Connection conn = Data.openConnection()) {
            DAOAluno.atualizar(conn, model);
        }
    }

    public static TOAluno deletar(String id) throws Exception {

        try (Connection conn = Data.openConnection()) {

            TOAluno aluno = DAOAluno.deletar(conn, id);

            return aluno;
        }
    }
}
