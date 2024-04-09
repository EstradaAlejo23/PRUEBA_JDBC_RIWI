package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection objConnection = null;

    public static Connection openConnection(){
        try{
            //Llamanos al driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creamos la variable de conexion
            String url = "jdbc:mysql://localhost:3306/prueba_desempeno_jdbc"; // para local: "jdbc:mysql://localhost:3306/simulacro_01"
            String user = "root"; // para local: "root"
            String password = ""; // para local: "" vacia
            //Establecemos conexion
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Ingreso a db");

        } catch (ClassNotFoundException error){
            JOptionPane.showMessageDialog(null,"ERROR >> Driver no instalado"+error.getMessage());
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"ERROR >> No conecté a base de datos"+e.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null) {
                objConnection.close();
                System.out.println("Se finalizó con éxito la conexión");
            } else {
                System.out.println("No hay conexiones abiertas");
            }
        } catch (SQLException error) {
            System.out.println("Error" + error.getMessage());
        }
    }
}
