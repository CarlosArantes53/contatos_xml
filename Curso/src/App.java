import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar Curso");
            System.out.println("2 - Adicionar Aluno");
            System.out.println("3 - Associar Aluno a Curso");
            System.out.println("4 - Listar Cursos");
            System.out.println("5 - Listar Alunos");
            System.out.println("0 - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    adicionarCurso(scanner);
                    break;
                case 2:
                    adicionarAluno(scanner);
                    break;
                case 3:
                    associarAlunoAoCurso(scanner);
                    break;
                case 4:
                    listarCursos();
                    break;
                case 5:
                    listarAlunos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarCurso(Scanner scanner) {
        Curso curso = new Curso();
        System.out.println("Nome do curso:");
        curso.setNome(scanner.nextLine());

        System.out.println("Descrição do curso:");
        curso.setDescricao(scanner.nextLine());

        System.out.println("Duração do curso em horas:");
        curso.setDuracaoHoras(scanner.nextInt());

        DAO.inserirCurso(curso);
        System.out.println("Curso adicionado com sucesso!");
    }

    private static void adicionarAluno(Scanner scanner) {
        Estudante estudante = new Estudante();
        System.out.println("Nome do aluno:");
        estudante.setNome(scanner.nextLine());

        System.out.println("Idade do aluno:");
        estudante.setIdade(scanner.nextInt());
        scanner.nextLine();

        System.out.println("E-mail do aluno:");
        estudante.setEmail(scanner.nextLine());

        DAO.inserirEstudante(estudante);
        System.out.println("Aluno adicionado com sucesso!");
    }

    private static void associarAlunoAoCurso(Scanner scanner) {
        System.out.println("Digite o ID do aluno:");
        int idAluno = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o ID do curso:");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        DAO.associarEstudanteAoCurso(idAluno, idCurso);
        System.out.println("Aluno associado ao curso com sucesso!");
    }

    private static void listarCursos() {
        System.out.println("Lista de Cursos:");
        DAO.listarCursos().forEach(curso -> {
            System.out.println("ID: " + curso.getIdCurso());
            System.out.println("Nome: " + curso.getNome());
            System.out.println("Descrição: " + curso.getDescricao());
            System.out.println("Duração em horas: " + curso.getDuracaoHoras());
            System.out.println("----------------------");
        });
    }

    private static void listarAlunos() {
        System.out.println("Lista de Alunos:");
        DAO.listarEstudantes().forEach(estudante -> {
            System.out.println("ID: " + estudante.getIdEstudante());
            System.out.println("Nome: " + estudante.getNome());
            System.out.println("Idade: " + estudante.getIdade());
            System.out.println("E-mail: " + estudante.getEmail());
            System.out.println("----------------------");
        });
    }
}