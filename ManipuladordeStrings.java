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
public class ManipuladordeStrings {
    public String Acoplar(List milist, String elemento){
        String nuevo="";
        for (int i=0; i<milist.size()-1;i++){
            nuevo+= milist.get(i).toString() + elemento;
        }
        return nuevo + milist.get(milist.size()-1).toString();
    }
    public Vector<String> Separar(String palabra){
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
