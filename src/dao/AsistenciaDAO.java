package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaDAO {

    /**
     * Registra la entrada del dia: si ya existe una fila para ese empleado
     * y esa fecha, no hace nada especial (se asume que se llamara a
     * registrarSalida despues). Usa INSERT ... ON DUPLICATE KEY para
     * aprovechar el indice UNIQUE(IdEmpleado, Fecha) del esquema.
     */
    public void registrarEntrada(int idEmpleado) throws SQLException {
        String sql = "INSERT INTO ASISTENCIAS (IdEmpleado, Fecha, HoraEntrada) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE HoraEntrada = VALUES(HoraEntrada)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            ps.setObject(2, LocalDate.now());
            ps.setTime(3, Time.valueOf(LocalTime.now().withNano(0)));
            ps.executeUpdate();
        }
    }

    public void registrarSalida(int idEmpleado) throws SQLException {
        String sql = "UPDATE ASISTENCIAS SET HoraSalida = ? WHERE IdEmpleado = ? AND Fecha = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTime(1, Time.valueOf(LocalTime.now().withNano(0)));
            ps.setInt(2, idEmpleado);
            ps.setObject(3, LocalDate.now());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No hay entrada registrada hoy para este empleado.");
            }
        }
    }

    public void imprimirReporteDelDia() throws SQLException {
        String sql = "SELECT e.Nombre, a.Fecha, a.HoraEntrada, a.HoraSalida "
                + "FROM ASISTENCIAS a INNER JOIN EMPLEADOS e ON a.IdEmpleado = e.IdEmpleado "
                + "WHERE a.Fecha = ? ORDER BY e.Nombre";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, LocalDate.now());
            try (ResultSet rs = ps.executeQuery()) {
                boolean hayDatos = false;
                while (rs.next()) {
                    hayDatos = true;
                    System.out.printf("%-25s Entrada: %-10s Salida: %-10s%n",
                            rs.getString("Nombre"), rs.getTime("HoraEntrada"), rs.getTime("HoraSalida"));
                }
                if (!hayDatos) {
                    System.out.println("Sin registros de asistencia hoy.");
                }
            }
        }
    }
}
