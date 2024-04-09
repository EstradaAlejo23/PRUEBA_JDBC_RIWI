package entity;

public class Contratacion {
    private int id;
    private String fecha;
    private String estado;
    private double salario;
    private int id_vacante;
    private Vacante objVacante;
    private int id_coder;
    private Coder objCoder;

    public Contratacion() {
    }

    public Contratacion(String estado, double salario, int id_vacante, Vacante objVacante, int id_coder, Coder objCoder) {
        this.estado = estado;
        this.salario = salario;
        this.id_vacante = id_vacante;
        this.objVacante = objVacante;
        this.id_coder = id_coder;
        this.objCoder = objCoder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "VACANTE = Titulo: " + objVacante.getTitulo() +
                ", Descripcion:" + objVacante.getDescripcion() +
                ", \n            EMPRESA= Nombre:" + objVacante.getObjEmpresa().getNombre() +
                ", Ubicacion:" + objVacante.getObjEmpresa().getUbicacion() +
                ", \n            CODER= Nombre:" + objCoder.getNombre() +
                ", Apellidos:" + objCoder.getApellidos() +
                ", Documento:" + objCoder.getDocumento() +
                ", Tecnologia:" + objVacante.getTecnologia() +
                ", salario=" + salario +
                "\n}";
    }
}
