import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class DAO {
    private static final String INSERIR_ESTUDANTE_SQL = "INSERT INTO Estudante (nome, idade, email) VALUES (?, ?, ?)";
    private static final String INSERIR_CURSO_SQL = "INSERT INTO Curso (nome, descricao, duracao_horas) VALUES (?, ?, ?)";
    private static final String INSERIR_CURSO_ESTUDANTE_SQL = "INSERT INTO CursoEstudante (idCurso, idEstudante) VALUES (?, ?)";
    private static final String LISTAR_CURSOS_SQL = "SELECT * FROM Curso";
    private static final String LISTAR_ESTUDANTES_SQL = "SELECT * FROM Estudante";
    private static final String LISTAR_ESTUDANTES_POR_CURSO_SQL = "SELECT e.* FROM Estudante e " +
            "JOIN CursoEstudante ce ON e.idEstudante = ce.idEstudante " +
            "WHERE ce.idCurso = ?";

    public static void inserirEstudante(Estudante estudante) {
        try (Connection connection = Controller.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_ESTUDANTE_SQL)) {

            preparedStatement.setString(1, estudante.getNome());
            preparedStatement.setInt(2, estudante.getIdade());
            preparedStatement.setString(3, estudante.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserirCurso(Curso curso) {
        try (Connection connection = Controller.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_CURSO_SQL)) {

            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setInt(3, curso.getDuracaoHoras());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void associarEstudanteAoCurso(int idEstudante, int idCurso) {
        try (Connection connection = Controller.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERIR_CURSO_ESTUDANTE_SQL)) {

            preparedStatement.setInt(1, idCurso);
            preparedStatement.setInt(2, idEstudante);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();

        try (Connection connection = Controller.getConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(LISTAR_CURSOS_SQL)) {

            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(resultSet.getInt("idCurso"));
                curso.setNome(resultSet.getString("nome"));
                curso.setDescricao(resultSet.getString("descricao"));
                curso.setDuracaoHoras(resultSet.getInt("duracao_horas"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public static List<Estudante> listarEstudantes() {
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection connection = Controller.getConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(LISTAR_ESTUDANTES_SQL)) {

            while (resultSet.next()) {
                Estudante estudante = new Estudante();
                estudante.setIdEstudante(resultSet.getInt("idEstudante"));
                estudante.setNome(resultSet.getString("nome"));
                estudante.setIdade(resultSet.getInt("idade"));
                estudante.setEmail(resultSet.getString("email"));
                estudantes.add(estudante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudantes;
    }

    public static List<Estudante> listarEstudantesPorCurso(int idCurso) {
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection connection = Controller.getConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(LISTAR_ESTUDANTES_POR_CURSO_SQL)) {

            preparedStatement.setInt(1, idCurso);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Estudante estudante = new Estudante();
                    estudante.setIdEstudante(resultSet.getInt("idEstudante"));
                    estudante.setNome(resultSet.getString("nome"));
                    estudante.setIdade(resultSet.getInt("idade"));
                    estudante.setEmail(resultSet.getString("email"));
                    estudantes.add(estudante);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudantes;
    }
}