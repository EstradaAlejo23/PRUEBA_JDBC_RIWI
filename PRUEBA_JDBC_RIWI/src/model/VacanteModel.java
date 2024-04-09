package model;

import database.CRUD;
import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Vacante objVacante = (Vacante) obj;

        try{
            String sql = "INSERT INTO vacante (titulo,descripcion,duracion,estado,id_empresa,tecnologia) VALUES (?,?,?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setInt(5,objVacante.getId_empresa());
            objPrepare.setString(6,objVacante.getTecnologia());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVacante.setId(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listaVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM vacante\n" +
                    "INNER JOIN empresa ON empresa.id = vacante.id_empresa;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();//devuelve todos los registro de la base de datos

            while(objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId(objResult.getInt("vacante.id"));
                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setDuracion(objResult.getString("vacante.duracion"));
                objVacante.setEstado(objResult.getString("vacante.estado"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));
                objVacante.setTecnologia(objResult.getString("tecnologia"));

                objEmpresa.setId(objResult.getInt("empresa.id"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setSector(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContacto(objResult.getString("empresa.contacto"));


                objVacante.setObjEmpresa(objEmpresa);

                listaVacantes.add(objVacante);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } ;

        ConfigDB.closeConnection();
        return listaVacantes;
    }


    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE vacante SET titulo = ?,descripcion = ?,duracion = ?,estado = ?,id_empresa = ?, tecnologia = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objVacante.getTitulo());
            objPrepare.setString(2,objVacante.getDescripcion());
            objPrepare.setString(3,objVacante.getDuracion());
            objPrepare.setString(4,objVacante.getEstado());
            objPrepare.setInt(5,objVacante.getObjEmpresa().getId());
            objPrepare.setString(6,objVacante.getTecnologia());
            objPrepare.setInt(7,objVacante.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVuelo = (Vacante) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM vacante WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objVuelo.getId());

            int totalRowAfected = objPrepare.executeUpdate();

            if(totalRowAfected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        } catch (SQLException e) {
            System.out.println("Error: "+e);
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }
}
