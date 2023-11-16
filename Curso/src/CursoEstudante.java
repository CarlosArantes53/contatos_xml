public class CursoEstudante {
    private int idCurso;
    private int idEstudante;

    public CursoEstudante(int idCurso, int idEstudante) {
        this.idCurso = idCurso;
        this.idEstudante = idEstudante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(int idEstudante) {
        this.idEstudante = idEstudante;
    }
}
