package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel implements CRUD{
    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = (Empresa) obj;

        try{
            String sql = "INSERT INTO empresa (nombre,sector,ubicacion,contacto) VALUE (?,?,?,?);";

            PreparedStatement objPrepare =objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//devuelve la id que es autoincrementable

            objPrepare.setString(1,objEmpresa.getNombre());
            objPrepare.setString(2,objEmpresa.getSector());
            objPrepare.setString(3,objEmpresa.getUbicacion());
            objPrepare.setString(4,objEmpresa.getContacto());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objEmpresa.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Empresa Ingresada Correctamente");


        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return objEmpresa;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listaEmpresas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//executeQuery devuelve todos los registros de la base de datos

            while (objResult.next()){
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId(objResult.getInt("id"));
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));

                listaEmpresas.add(objEmpresa);
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaEmpresas;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }


}
