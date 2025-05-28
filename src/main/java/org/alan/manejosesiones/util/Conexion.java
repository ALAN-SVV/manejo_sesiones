package org.alan.manejosesiones.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //inicializo tres variables globales
    //la url esta integrada con jdbc, nombre de la aplicacion de la base de datos, el puerto , nombre de la clase, y la zona horaria, todo es obligatorio
    private static String url="jdbc:mysql://localhost:3306/trabajoenclase?serverTimezone=UTC";
    //nombre de usuario de la base de datos
    private static String username="root";
    //constraseña
    private static String password="";
    //implemenatamos un metodo para realizar la conexion, con control de errores
    public static Connection getConnection()throws SQLException {
        //retorna la conexion
        return DriverManager.getConnection(url, username, password);
    }
    //main
    public static void main(String[] args) {
        System.out.println("Intentando conectar a la base de datos...");

        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("¡Conexión exitosa!");
                System.out.println("Base de datos: " + connection.getCatalog());
                System.out.println("URL: " + url);
            } else {
                System.out.println("Conexión rechazada");
            }
        } catch (SQLException e) {
            System.out.println("Conexión rechazada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

