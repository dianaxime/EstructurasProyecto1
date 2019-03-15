/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Vector;

/**
 *
 * @author DIANA
 */
public class Descifrador {
    Vector <ArboldeAnalisis> declaraciones = new Vector <> ();
    public Descifrador(Vector <String> fichas) throws Exception{
	Vector <String> estados;
	int i = 0;
	int k, l;
        while ( i < fichas.size() && i >= 0 ){
            k = fichas.indexOf("(", i);
            if ( k == i ){
                l = finalExpresion(new Vector <> (fichas.subList(i, fichas.size()))) + i;
            } else if ( k > i ){
		l = k - 1;
            } else {
		l = fichas.size() - 1;
            }
            estados = convertir( new Vector <> (fichas.subList(i, l+1)));
            declaraciones.add(new ArboldeAnalisis(estados));
            i = l + 1;
	}
    }
    public void evaluacion() throws Exception{
	for ( int i = 0; i < declaraciones.size(); i++ ){
            System.out.println(declaraciones.get(i).evaluacion());
	}
    }
    private Vector <String> convertir(Vector <String> exp){
	Vector <String> r = new Vector <>();
	Vector <String> temp;
	int siguiente;
	if ( exp.get(0).matches("[(]") ){
            int cerrarse = finalExpresion(exp);
            if ( cerrarse > 1 ){
                r.add("(");
		if ( cerrarse > 2 ){
                    if ( exp.get(1).matches("[(]") ){
			siguiente = finalExpresion(new Vector <>(exp.subList(1, cerrarse))) + 2;
                    } else {
			siguiente = 2;
                    }
                    if ( ! exp.get(siguiente).matches("[.]") ){
			r.addAll(convertir(new Vector <>(exp.subList(1,siguiente))));
			r.add(".");
			temp = new Vector <>();
			temp.add("(");
			temp.addAll(exp.subList(siguiente, cerrarse));
			temp.add(")");
			r.addAll(convertir(temp));
                    } else {
                        r.addAll(convertir(new Vector <>(exp.subList(1,siguiente))));
			r.add(".");
			r.addAll(convertir(new Vector <>(exp.subList(siguiente+1, cerrarse))));
                    }
		} else {
                    r.add(exp.get(1));
                    r.add(".");
                    r.add("NIL");
		}
                    r.add(")");
		} else {
                    r.add("NIL");
		}
		} else {
                    if ( exp.indexOf("(") > 0 ){
			r.addAll(exp.subList(0, exp.indexOf("(")));
			r.addAll(convertir(new Vector <>(exp.subList(exp.indexOf("("), exp.size()))));
                    } else {
			r = exp;	
                    }
		}
	return r;
    }
    private int finalExpresion(Vector <String> exp) throws IllegalArgumentException, ArrayIndexOutOfBoundsException{
	if ( ! exp.get(0).matches("[(]") ){
            throw new IllegalArgumentException("ERROR: Se trata de encontrar una expresion que no comienza con '('.");
	}
	int inicio = 1;
	int fin = 1;
	while ( inicio > 0 ){
            if ( fin >= exp.size() ){
                    throw new ArrayIndexOutOfBoundsException("¡Error! Faltan parentesis");
            }
            if ( exp.get(fin).matches("[)]") ){
                inicio--;
            } else if ( exp.get(fin).matches("[(]") ){
                inicio++;
            }
            if ( inicio == 0 ){
                break;
            } else {
                fin++;
            }
        }
	return fin;
    }
}
