/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author DIANA
 */
//Ayudarse trabajando con cadenas y vectores de cadenas 
public class ManipuladordeStrings {
    //unir una lista 
    public static String Acoplar(List milist, String elemento){
        String nuevo="";
        for (int i=0; i<milist.size()-1;i++){
            nuevo+= milist.get(i).toString() + elemento;
        }
        return nuevo + milist.get(milist.size()-1).toString();
    }
    // un vector donde cada uno de los elementos de la cadena 
    public static Vector<String> Separar(String palabra){
        Vector<String> mivector = new Vector<>();
        int i;
        for (i=0; i<palabra.length()-1; i++){
            mivector.add(palabra.substring(i, i+1));
        }
        if (i>0){
            mivector.add(palabra.substring(i));
        }
        return mivector;
    }
}
