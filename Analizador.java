/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 *
 * @author DIANA
 */
public class Analizador {
    
    private String miprograma ="";
    private Vector<String> simbolos = new Vector<>();
    
    
    public Analizador(InputStream clasificar) throws IOException{
        byte[] bytes = new byte[1024];
        while (clasificar.available()>0){
            clasificar.read(bytes, 0, 1024);
            miprograma = miprograma.concat(new String(bytes)).trim().toUpperCase();
        }
        simbolos = simbologia(miprograma);
    }
    public Analizador (String clasificar){
        miprograma=clasificar;
        simbolos = simbologia(miprograma);
    }
    public Vector <String> obtenersimbolos(){
        return simbolos;
    }
    
}
