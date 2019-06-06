package faculdade.dao;

public class DAOTurma {
    public static List<TOTurma> lista(Connection conn) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" select id, idProfessor, idDisciplina from Turma ");
        sql.append(" order by id ");

        List<TOTurma> lista = new ArrayList<>();

        try (ResultSet rs = Data.executeQuery(conn, sql.toString())) {

            while (rs.next()) {
                TOTurma turma = new TOTurma();
                turma.setId(rs.getString("id"));
                turma.setIdProfessor(rs.getString("idProfessor"));
                turma.setIdDisciplina(rs.getString("idDisciplina"));
                lista.add(turma);
            }

        }
        return lista;
    }

    public static void inserir(Connection conn, TOTurma turma) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into Turma (idProfessor, idDisciplina) values ");
        sql.append(" (?, ?) ");
        Data.executeUpdate(conn, sql.toString(), turma.getIdProfessor(), turma.getIdDisciplina());
    }

    public static void atualizar(Connection conn, TOTurma turma) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" update Turma set idProfessor = ?, idDisciplina = ?");
        sql.append(" where id = ? ");
        Data.executeUpdate(conn, sql.toString(), turma.getIdProfessor(), turma.getIdDisciplina(), turma.getId());
    }

    public static void deletar(Connection conn, String id) throws Exception {

        StringBuilder sql = new StringBuilder();
        sql.append(" delete from Turma");
        sql.append(" where ");
        sql.append(" id = ? ");

        try (Data.executeQuery(conn, sql.toString(), id)) {

        }
    }
}
