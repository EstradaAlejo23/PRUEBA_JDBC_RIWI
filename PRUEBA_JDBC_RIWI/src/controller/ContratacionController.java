package controller;

import entity.Coder;
import entity.Contratacion;
import entity.Vacante;
import model.ContratacionModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class ContratacionController {

    public static void update(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Object [] estado ={"ACTIVO","INACTIVO"};

        Contratacion contratacionSeleccionada = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la Contratacion a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        contratacionSeleccionada.setEstado((String) JOptionPane.showInputDialog(
                null,
                "Selecciona el estado",
                "Elegir",
                JOptionPane.QUESTION_MESSAGE,
                null,
                estado,
                estado[0]));
        contratacionSeleccionada.setSalario(Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa el Salario",contratacionSeleccionada.getSalario())));

        Object[] optionsVacantes = Utils.listaAarray(VacanteController.instanciarModel().findAll());
        Object[] optionsCoders = Utils.listaAarray(CoderController.instanciarModel().findAll());

        Vacante vacanteSeleccionada = (Vacante) JOptionPane.showInputDialog(
                null,
                "Selecciona una Vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsVacantes,
                optionsVacantes[0]
        );

        contratacionSeleccionada.setId_vacante(vacanteSeleccionada.getId());
        contratacionSeleccionada.setObjVacante(vacanteSeleccionada);

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Selecciona un Coder",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCoders,
                optionsCoders[0]
        );

        contratacionSeleccionada.setId_coder(coderSeleccionado.getId());
        contratacionSeleccionada.setObjCoder(coderSeleccionado);


        instanciarModel().update(contratacionSeleccionada);
    }

    public static void insert(){

        Object [] opcionesEstado ={"ACTIVO","INACTIVO"};
        Object estado = JOptionPane.showInputDialog(
                null,
                "Selecciona un estado",
                "Elegir",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesEstado,
                opcionesEstado[0]);

        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el Salario"));

        Object[] optionsVacantes = Utils.listaAarray(VacanteController.instanciarModel().findAll());
        Object[] optionsCoders = Utils.listaAarray(CoderController.instanciarModel().findAll());


        Vacante vacanteSeleccionada = (Vacante) JOptionPane.showInputDialog(
                null,
                "Selecciona la Vacante",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsVacantes,
                optionsVacantes[0]
        );

        Coder coderSeleccionado = (Coder) JOptionPane.showInputDialog(
                null,
                "Selecciona el Coder",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsCoders,
                optionsCoders[0]
        );


        instanciarModel().insert(new Contratacion((String) estado,salario, vacanteSeleccionada.getId(),vacanteSeleccionada, coderSeleccionado.getId(), coderSeleccionado));
    }

    public static void delete(){
        Object[] options = Utils.listaAarray(instanciarModel().findAll());
        Contratacion contratacionSeleccionada = (Contratacion) JOptionPane.showInputDialog(
                null,
                "Seleccione la Reservacion a Eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanciarModel().delete(contratacionSeleccionada);

    }

    public static void getiInactive(){
        String listaString = getAll(instanciarModel().findInactive());
        JOptionPane.showMessageDialog(null,listaString);
    }

    public static void getAll(){
        String listaString = getAll(instanciarModel().findAll());
        JOptionPane.showMessageDialog(null,listaString);
    }

    public static String getAll(List<Object> list){
        String listaString = "Lista de registros \n";

        for(Object temp: list ){
            Contratacion obj = (Contratacion) temp;
            listaString += obj.toString() + "\n";
        }
        return listaString;
    }

    public static ContratacionModel instanciarModel(){
        return new ContratacionModel();
    }
}
