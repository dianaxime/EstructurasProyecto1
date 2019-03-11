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
        simbolos = Simbologia(miprograma);
    }
    public Analizador (String clasificar){
        miprograma=clasificar;
        simbolos = Simbologia(miprograma);
    }
    public Vector <String> Obtenersimbolos(){
        return simbolos;
    }
    public Vector<String> Simbologia (String palabra){
        int i = 0;
        Vector <String> claves = new Vector <>();
        if ( palabra.length() == 1 ){
            claves.add(palabra);
            return claves;
	}
	while (  i < palabra.length() ){
            int j = i + 1;
            if ( palabra.substring(i, j).matches(Patrones.LETRA) || palabra.substring(i, j).matches(Patrones.OPERADOR) ){
		while ( palabra.substring(i,j + 1).matches(Patrones.LETRAS) || palabra.substring(i, j + 1).matches(Patrones.OPERADOR) ){
			j++;
		}
		claves.add(palabra.substring(i,j));
            } else if ( palabra.substring(i, j).matches(Patrones.SIMBOLO) ){
		claves.add(palabra.substring(i,j));
            }
            i = j;
	}
	return claves;  
    }
    private int Final (Vector <String> mivector) throws IllegalArgumentException, ArrayIndexOutOfBoundsException{
	if ( ! mivector.get(0).matches("[(]") ){
            throw new IllegalArgumentException("ERROR");
	}
	int abrir = 1;
	int fin = 1;
	while ( abrir > 0 ){
            if ( fin >= mivector.size() ){
		throw new ArrayIndexOutOfBoundsException("Error: Verificar tus parentesis");
            }
            if ( mivector.get(fin).matches("[)]") ){
		abrir--;
            } else if ( mivector.get(fin).matches("[(]") ){
		abrir++;
            }
            if ( abrir == 0 ){
		break;
            } else {
		fin++;
            }
	}
        return fin;
    }
}
