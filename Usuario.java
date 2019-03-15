/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author DIANA
 */
public class Usuario {
    protected String nombre;
    protected Vector <String> formalidades;
    protected Nodos cuerpo;
    
    public Usuario(String n, Nodos f, Nodos b) throws Exception{
        nombre = n;
	if ( (!f.isList() && !f.toString().matches("NIL") ) || ( !b.isList() && !b.toString().matches("NIL") ) ){
            throw new Exception("Funcion invalida \n" + f.toString() + "\n" + b.toString());
	}
        String formalString = f.toString();
	formalidades = separarParametros(formalString);
	cuerpo = b;
    }
    protected Nodos evaluar(Nodos actuales) throws Exception{
	Hashtable <String, Nodos> bindings = enlazar(actuales);
	return cuerpo.evaluar(true, bindings);
    }
    private static Vector <String> separarParametros(String exp) throws Exception{
	String[] trozos = exp.substring(1, exp.length()-1).split("\\s");
	Vector <String> rtn = new Vector <> ();
	for ( int i = 0; i < trozos.length; i++ ){
            if ( trozos[i].matches(Patrones.FUNCION) ){
		if ( ! rtn.contains(trozos[i]) ){
                    rtn.add(trozos[i]);
		} else {
                    throw new Exception("¡Error! Los nombres de los parametros deben ser distintos");
		}
            } else {
		throw new Exception("¡Error! Invalido nombre de parametros: " + trozos[i]);
            }
	}
	return rtn;
    }
    private Hashtable <String, Nodos> enlazar (Nodos exp) throws Exception{
	if ( ! exp.isList() && !exp.toString().matches("NIL") ){
            throw new Exception("¡Error! Parametros invalidos para: " + nombre);
	}
	Hashtable <String, Nodos> amb = new Hashtable <> ();
        if ( ! exp.isList() ){
            return amb;
	}
        Funciones tmp = new Funciones(exp);
        int i;
        for ( i = 0; i < formalidades.size(); i++ ){
            amb.put(formalidades.get(i), tmp.direccion.evaluar());
            try{
                tmp = new Funciones(tmp.fichasdedatos);
            } catch (Exception e){
                break;
            }
        }
        if ( i < formalidades.size() - 1 ){
            throw new Exception("¡Error! Muy pocos argumentos para: " + nombre);
        } else if ( ! tmp.datos.evaluar().toString().matches("NIL") ){
            throw new Exception("¡Error! Muchos argumentos para: " + nombre);
	}
        return amb;
    }
}
