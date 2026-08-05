-- ============================================================
-- Sistema de Control de Asistencia - Script de base de datos
-- ============================================================

CREATE DATABASE IF NOT EXISTS ASISTENCIA;
USE ASISTENCIA;

CREATE TABLE EMPLEADOS (
    IdEmpleado  INT AUTO_INCREMENT PRIMARY KEY,
    Cedula      VARCHAR(15) NOT NULL UNIQUE,
    Nombre      VARCHAR(60) NOT NULL,
    Puesto      VARCHAR(60)
);

CREATE TABLE ASISTENCIAS (
    IdAsistencia INT AUTO_INCREMENT PRIMARY KEY,
    IdEmpleado   INT NOT NULL,
    Fecha        DATE NOT NULL,
    HoraEntrada  TIME,
    HoraSalida   TIME,
    CONSTRAINT FK_ASISTENCIAS_EMPLEADOS FOREIGN KEY (IdEmpleado)
        REFERENCES EMPLEADOS (IdEmpleado),
    UNIQUE (IdEmpleado, Fecha)
);

INSERT INTO EMPLEADOS (Cedula, Nombre, Puesto) VALUES
('101110111', 'Ana Ramirez', 'Analista'),
('202220222', 'Luis Solano', 'Vendedor'),
('303330333', 'Marta Vindas', 'Contadora');
