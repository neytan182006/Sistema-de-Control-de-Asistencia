package modelo;

public class Empleado {

    private int idEmpleado;
    private String cedula;
    private String nombre;
    private String puesto;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String cedula, String nombre, String puesto) {
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s)", idEmpleado, cedula, nombre, puesto);
    }
}
