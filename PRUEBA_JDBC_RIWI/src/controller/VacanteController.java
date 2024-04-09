package controller;

import entity.Empresa;
import entity.Vacante;
import model.VacanteModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class VacanteController {
    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp : list){
            Vacante objVacante = (Vacante) temp;
            listaString += objVacante.toString() + "\n";
        }

        return listaString;
    }

    public static void deleted(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Vacante objVuelo = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione la Vacante a eliminar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        instanciarModel().delete(objVuelo);
    }

    public static void updated(){
        Object[] opciones = Utils.listaAarray(instanciarModel().findAll());
        Object[] estado = {"ACTIVO","INACTIVO"};

        Vacante objVacante = (Vacante) JOptionPane.showInputDialog(
                null,
                "Seleccione la Vacante a actualizar: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        objVacante.setTitulo(JOptionPane.showInputDialog(null,"Ingresa el Titulo: ",objVacante.getTitulo()));
        objVacante.setDescripcion(JOptionPane.showInputDialog(null,"Ingresa la Descripcion: ",objVacante.getDescripcion()));
        objVacante.setDuracion(JOptionPane.showInputDialog(null,"Ingresa la Duracion: ",objVacante.getDuracion()));
        objVacante.setEstado((String) JOptionPane.showInputDialog(
                null,
                "Selecciona el estado",
                "Elegir",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estado,
                estado[0]));
        objVacante.setTecnologia(JOptionPane.showInputDialog(null,"Ingrese la tecnologia: ",objVacante.getTecnologia()));

        Object[] opcionesEmpresas = Utils.listaAarray(EmpresaController.instanciarModel().findAll());

        objVacante.setObjEmpresa((Empresa) JOptionPane.showInputDialog(
                null,
                "Seleccione una Empresa: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEmpresas,
                opcionesEmpresas[0]
        ));

        instanciarModel().update(objVacante);
    }

    public static void insert(){
        Object [] opcionesEstado ={"ACTIVO","INACTIVO"};

        String titulo = JOptionPane.showInputDialog("Ingresa el Titulo: ");
        String descripcion = JOptionPane.showInputDialog("Ingresa la Descripcion");
        String duracion = JOptionPane.showInputDialog("Ingresa la Duracion");
        Object estado = JOptionPane.showInputDialog(
                null,
                "Selecciona un Estado",
                "Elegir",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEstado,
                opcionesEstado[0]);
        String tecnologia = JOptionPane.showInputDialog("Ingresa la tecnologia");

        Object[] opcionesEmpresas = Utils.listaAarray(EmpresaController.instanciarModel().findAll());

        Empresa objEmpresa = (Empresa) JOptionPane.showInputDialog(
                null,
                "Seleccione una Empresa: ",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEmpresas,
                opcionesEmpresas[0]
        );

        instanciarModel().insert(new Vacante(titulo,descripcion,duracion,(String) estado,objEmpresa.getId(),objEmpresa,tecnologia));

    }

    public static VacanteModel instanciarModel(){
        return new VacanteModel();
    }
}
