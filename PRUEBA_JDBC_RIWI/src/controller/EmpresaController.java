package controller;

import entity.Coder;
import entity.Empresa;
import model.CoderModel;
import model.EmpresaModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class EmpresaController {

    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el Nombre");
        String sector = JOptionPane.showInputDialog("Ingrese el Sector");
        String ubicacion = JOptionPane.showInputDialog("Ingrese la ubicacion");
        String contacto = JOptionPane.showInputDialog("Ingrece el Contacto");

        instanciarModel().insert(new Empresa(nombre,sector,ubicacion,contacto));
    }

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);

    }

    public static String getAll(List<Object> list){

        String listaString = "LISTA DE REGISTROS: \n";

        for(Object temp : list){
            Empresa objEmpresas = (Empresa) temp;
            listaString += objEmpresas.toString() + "\n";
        }

        return listaString;
    }

    public static EmpresaModel instanciarModel(){
        return new EmpresaModel();
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Empresa objSeleccionar = (Empresa) JOptionPane.showInputDialog(
                null,
                "Selecciona una Empresa",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(objSeleccionar);
    }

    public static void updated(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Empresa objSeleccionar = (Empresa) JOptionPane.showInputDialog(
                null,
                "Selecciona una Empresa a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSeleccionar.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo Nombre",objSeleccionar.getNombre()));
        objSeleccionar.setSector(JOptionPane.showInputDialog(null,"Ingresa los nuevos Sector ",objSeleccionar.getSector()));
        objSeleccionar.setUbicacion(JOptionPane.showInputDialog(null,"Ingresa la nueva Ubicacion",objSeleccionar.getUbicacion()));
        objSeleccionar.setContacto(JOptionPane.showInputDialog(null,"Ingresa el nuevo Contacto",objSeleccionar.getContacto()));

        instanciarModel().update(objSeleccionar);

    }

}
