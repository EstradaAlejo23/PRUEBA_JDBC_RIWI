package controller;

import entity.Coder;
import model.CoderModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class CoderController {
    public static void insert() {
        String nombre = JOptionPane.showInputDialog("Ingrese el Nombre");
        String apellidos = JOptionPane.showInputDialog("Ingrese los Apellidos");
        String documento = JOptionPane.showInputDialog("Ingrese el Documento");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte"));
        String cv = JOptionPane.showInputDialog("Ingrece el CV");

        instanciarModel().insert(new Coder(nombre,apellidos,documento,cohorte,cv));
    }

    public static void getAll(){
        String list = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,list);

    }

    public static String getAll(List<Object> list){

        String listaString = "LISTA DE REGISTROS: \n";

        for(Object temp : list){
            Coder objAvion = (Coder) temp;
            listaString += objAvion.toString() + "\n";
        }

        return listaString;
    }

    public static CoderModel instanciarModel(){
        return new CoderModel();
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());

        Coder objSeleccionar = (Coder) JOptionPane.showInputDialog(
                null,
                "Selecciona una Coder",
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
        Coder objSeleccionar = (Coder) JOptionPane.showInputDialog(
                null,
                "Selecciona un Coder a actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        objSeleccionar.setNombre(JOptionPane.showInputDialog(null,"Ingresa el nuevo Nombre",objSeleccionar.getNombre()));
        objSeleccionar.setApellidos(JOptionPane.showInputDialog(null,"Ingresa los nuevos Apellidos ",objSeleccionar.getApellidos()));
        objSeleccionar.setDocumento(JOptionPane.showInputDialog(null,"Ingresa el nuevo Documento",objSeleccionar.getDocumento()));
        objSeleccionar.setCohorte(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el nuevo cohorte",objSeleccionar.getCohorte())));
        objSeleccionar.setCv(JOptionPane.showInputDialog(null,"Ingresa la nueva CV",objSeleccionar.getCv()));

        instanciarModel().update(objSeleccionar);

    }
}
