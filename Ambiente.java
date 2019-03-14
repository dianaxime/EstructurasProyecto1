/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author DIANA
 */
public class Ambiente {
    public static java.util.Hashtable <String, Usuario> funcion = new Hashtable <String, Usuario>();
    public static java.util.Hashtable <String, Nodos> variable = new Hashtable <String, Nodos>();
    
    public static Nodos ejecutarFuncion(String nombre, Nodos parametros) throws Exception{
	if ( funcion.containsKey(nombre) ){
	} else {
            throw new Exception("¡Error! La funcion: " + nombre + "no esta definida");
        }
        Usuario vfun = funcion.get(nombre);
	return vfun.evaluar(parametros);
    }
    public static void registrarFuncion(String nombre, Nodos parametros, Nodos cuerpo) throws Exception{
	Usuario vfun = new Usuario(nombre, parametros, cuerpo);
	funcion.put(nombre, vfun);
    }
    public static boolean funcionExiste(String nombre){
	return funcion.containsKey(nombre);
    }
    public static void separarTodo(Hashtable <String, Nodos> entorno){
	Iterator iterador = entorno.entrySet().iterator();
	while (iterador.hasNext()) {
	    Map.Entry pairs = (Map.Entry)iterador.next();
	    if ( variable.get(pairs.getKey()) == pairs.getValue() ){
        	variable.remove(pairs.getKey());
            }
	    iterador.remove(); 
        }
    }
    public static void separar(String nombre){
	variable.remove(nombre);
    }
    public static Nodos getValor(String nombre) throws Exception{
	if ( variable.containsKey(nombre) ){
            return variable.get(nombre);
	} else {
            throw new Exception("¡Error! No existe esa variable");
	}
    }
    public static void unirVariables(Hashtable <String, Nodos> nueva){
	Iterator iterador = nueva.entrySet().iterator();
	while (iterador.hasNext()) {
	    Map.Entry pairs = (Map.Entry)iterador.next();
            if ( variable.contains(pairs.getKey()) ){ // Do not let it store multiple things in one bucket
		variable.remove(pairs.getKey());
            }
            variable.put( (String) pairs.getKey(), (Nodos) pairs.getValue() );
	    iterador.remove(); // avoids a ConcurrentModificationException
        }
    }
    public static Hashtable <String, Nodos> obtener(){
	return new Hashtable <> (variable);
    }
    public static void modificarVariable(Hashtable <String, Nodos> modificada){
	variable = new Hashtable <> (modificada);
    }
}
