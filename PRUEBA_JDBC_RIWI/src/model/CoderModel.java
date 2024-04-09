package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;

        try{
            String sql = "INSERT INTO coder (nombre,apellidos,documento,cohorte,cv,clan) VALUE (?,?,?,?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//devuelve la id que es autoincrementable

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCoder.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Coder Ingresado Correctamente");


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listaCoders= new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//executeQuery devuelve todos los registros de la base de datos

            while (objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId(objResult.getInt("id"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellidos(objResult.getString("apellidos"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
                objCoder.setClan(objResult.getString("clan"));

                listaCoders.add(objCoder);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaCoders;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?,cohorte = ?,cv = ?, clan = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objCoder.getNombre());
            objPrepare.setString(2,objCoder.getApellidos());
            objPrepare.setString(3,objCoder.getDocumento());
            objPrepare.setInt(4,objCoder.getCohorte());
            objPrepare.setString(5,objCoder.getCv());
            objPrepare.setString(6,objCoder.getClan());
            objPrepare.setInt(7,objCoder.getId());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El registro fue actualizado correctamente");

            }

        }catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        ConfigDB.closeConnection();
        return isUpdated;
    }


    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM coder WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,objCoder.getId());
            int totalAffectedRow = objPrepare.executeUpdate();// executeUpdate devuelve un numero de filas afectadas

            if (totalAffectedRow>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente "+objCoder.toString());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isDeleted;
    }
}
