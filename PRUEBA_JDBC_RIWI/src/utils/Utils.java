package utils;

import java.util.List;

public class Utils {
    // los joption solo reciben arrays entonces este metodo nos ayuda a convertir listas en array
    public static <T> T[] listaAarray(List<T> list){
        //los genericos: es lo que le paso detro de los simbolos de mayor y menor y puedo crear mis propios genericos
        //T puede ser cualquier cosa

        T[] array =(T[]) new Object[list.size()];

        int i=0;
        for (T iterador : list){
            array[i++] = iterador;
        }

        return array;
    }
}
