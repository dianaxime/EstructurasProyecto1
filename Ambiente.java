/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Hashtable;

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
}
