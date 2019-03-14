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
public class ArboldeAnalisis {
    private Nodos raiz;
    
    public ArboldeAnalisis(Vector <String> exp) throws Exception{
	raiz = Nodos.crear(exp);
    }
    
    protected String evaluacion() throws Exception{
	String rtn = raiz.evaluar().toString();
	return rtn;
    }
    protected String imprimirResultado(){
	String resultado = raiz.toString();
	return resultado;
    }
}

