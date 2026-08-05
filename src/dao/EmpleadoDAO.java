package dao;

import modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDAO {

    /**
     * Carga todos los empleados en un arreglo de tamano fijo (patron de
     * arreglo de objetos de Programacion II): primero se cuenta cuantas
     * filas hay, se crea el arreglo de ese tamano y se llena con un ciclo.
     */
    public Empleado[] listarComoArreglo() throws SQLException {
        String sqlConteo = "SELECT COUNT(*) FROM EMPLEADOS";
        String sqlDatos = "SELECT IdEmpleado, Cedula, Nombre, Puesto FROM EMPLEADOS ORDER BY Nombre";

        try (Connection con = ConexionBD.obtenerConexion()) {
            int total;
            try (PreparedStatement ps = con.prepareStatement(sqlConteo);
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                total = rs.getInt(1);
            }

            Empleado[] empleados = new Empleado[total];
            try (PreparedStatement ps = con.prepareStatement(sqlDatos);
                 ResultSet rs = ps.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    empleados[i] = new Empleado(
                            rs.getInt("IdEmpleado"),
                            rs.getString("Cedula"),
                            rs.getString("Nombre"),
                            rs.getString("Puesto"));
                    i++;
                }
            }
            return empleados;
        }
    }
}
