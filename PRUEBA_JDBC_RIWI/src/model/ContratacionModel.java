package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql1 = "INSERT INTO contratacion (estado,salario,id_vacante,id_coder) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objContratacion.getEstado());
            objPrepare.setDouble(2, objContratacion.getSalario());
            objPrepare.setInt(3, objContratacion.getId_vacante());
            objPrepare.setInt(4, objContratacion.getId_coder());

            objPrepare.execute();


            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objContratacion.setId(objResult.getInt(1));

            }
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");


        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        ConfigDB.closeConnection();
        return objContratacion;

    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaContrataciones = new ArrayList<>();

        try{
            String sql = "SELECT * FROM contratacion INNER JOIN vacante ON vacante.id = contratacion.id_vacante INNER JOIN coder ON coder.id = contratacion.id_coder INNER JOIN empresa ON empresa.id = vacante.id_empresa where vacante.estado = \"ACTIVO\";";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Contratacion objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setSalario(objResult.getInt("contratacion.salario"));
                objContratacion.setId_vacante(objResult.getInt("contratacion.id_vacante"));
                objContratacion.setId_coder(objResult.getInt("contratacion.id_coder"));


                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));

                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));

                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);
                objContratacion.setObjCoder(objCoder);


                listaContrataciones.add(objContratacion);


            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDB.closeConnection();
        return listaContrataciones;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE contratacion SET estado = ?,salario = ?, id_vacante = ?, id_coder = ? WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            objPrepare.setString(1,objContratacion.getEstado());
            objPrepare.setDouble(2,objContratacion.getSalario());
            objPrepare.setInt(3,objContratacion.getId_vacante());
            objPrepare.setInt(4,objContratacion.getId_coder());
            objPrepare.setInt(5,objContratacion.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Registro Actulizado correctamente");
            }


        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isDeleted = false;

        try{
            String sql = "DELETE FROM contratacion WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objContratacion.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }

        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public List<Object> findInactive() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaContrataciones = new ArrayList<>();

        try{
            String sql = "SELECT * FROM contratacion INNER JOIN vacante ON vacante.id = contratacion.id_vacante INNER JOIN coder ON coder.id = contratacion.id_coder INNER JOIN empresa ON empresa.id = vacante.id_empresa where vacante.estado = \"INACTIVO\";";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Contratacion objContratacion = new Contratacion();
                Vacante objVacante = new Vacante();
                Coder objCoder = new Coder();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId(objResult.getInt("contratacion.id"));
                objContratacion.setSalario(objResult.getInt("contratacion.salario"));
                objContratacion.setId_vacante(objResult.getInt("contratacion.id_vacante"));
                objContratacion.setId_coder(objResult.getInt("contratacion.id_coder"));


                objVacante.setTitulo(objResult.getString("vacante.titulo"));
                objVacante.setDescripcion(objResult.getString("vacante.descripcion"));
                objVacante.setTecnologia(objResult.getString("vacante.tecnologia"));
                objVacante.setId_empresa(objResult.getInt("vacante.id_empresa"));
                objEmpresa.setNombre(objResult.getString("empresa.nombre"));
                objEmpresa.setUbicacion(objResult.getString("empresa.ubicacion"));

                objCoder.setNombre(objResult.getString("coder.nombre"));
                objCoder.setApellidos(objResult.getString("coder.apellidos"));
                objCoder.setDocumento(objResult.getString("coder.documento"));

                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);
                objContratacion.setObjCoder(objCoder);


                listaContrataciones.add(objContratacion);


            }
        }catch (SQLException e){
            System.out.println("ERROR: "+e);
        }

        ConfigDB.closeConnection();
        return listaContrataciones;
    }
}
