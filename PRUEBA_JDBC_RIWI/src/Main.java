import controller.CoderController;
import controller.ContratacionController;
import controller.EmpresaController;
import controller.VacanteController;
import database.ConfigDB;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option =0, option2 = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("""
                    1. Administrar CODER
                    2.Administrar EMPRESA
                    3. Administrar VACANTE
                    4. Administrar CONTRATACION
                    5.Salir
                    
                    Ingrese una opcion:
                    """));

            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar CODERS
                                2. Crear CODER
                                3. Eliminar CODER
                                4. Actualizar CODER
                                5.Salir
                    
                                Ingrese una opcion:
                                """));

                        switch (option2){
                            case 1:
                                CoderController.getAll();
                                break;
                            case 2:
                                CoderController.insert();
                                break;
                            case 3:
                                CoderController.delete();
                                break;
                            case 4:
                                CoderController.updated();
                                break;
                        }
                    }while (option2 != 5);

                    break;

                case 2:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar EMPRESAS
                                2. Crear EMPRESA
                                3.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                EmpresaController.getAll();
                                break;
                            case 2:
                                EmpresaController.insert();
                                break;
                        }

                    }while(option2 !=5);
                    break;

                case 3:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar VACANTES
                                2. Crear VACANTE
                                3. Eliminar VACANTE
                                4. Actualizar VACANTE
                                5.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                VacanteController.getAll();
                                break;
                            case 2:
                                VacanteController.insert();
                                break;
                            case 3:
                                VacanteController.deleted();
                                break;
                            case 4:
                                VacanteController.updated();
                                break;
                        }
                    }while(option2 != 5);
                    break;

                case 4:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog("""
                                1. Listar CONTRATACIONES ACTIVAS
                                2  listar CONTRATACIONES INACTIVAS
                                3. Crear CONTRATACION
                                4. Eliminar CONTRATACION
                                5. Actualizar CONTRATACION
                                6.Salir
                    
                                Ingrese una opcion:
                                """));
                        switch (option2){
                            case 1:
                                ContratacionController.getAll();
                                break;
                            case 2:
                                ContratacionController.getiInactive();
                                break;
                            case 3:
                                ContratacionController.insert();
                                break;
                            case 4:
                                ContratacionController.delete();
                                break;
                            case 5:
                                ContratacionController.update();
                                break;
                        }
                    }while(option2 != 6);
                    break;
            }
        }while (option != 5);
    }
}