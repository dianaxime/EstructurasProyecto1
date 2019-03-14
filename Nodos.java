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
public abstract class Nodos {
    protected abstract boolean isList();
    protected Vector <String> fichas = new Vector <> ();
    static Nodos crear(Vector <String> exp) throws Exception{
        if ( exp.size() > 0 && exp.get(0).matches("[(]") ){
            return new SExpression(exp);
        } else if ( exp.size() > 0 ){
            return new Atom1(exp.get(0));
        } else {
            throw new Exception("No hay datos");
        }
    }
    static Nodos crear(boolean exp){
	return new Atom1(exp);
    }
    static Nodos crear(int exp){
	return new Atom1(exp);
    }
    abstract Nodos evaluar() throws Exception;
    abstract Nodos evaluar(boolean flag) throws Exception;
    
    abstract Nodos evaluar(boolean flag, Hashtable <String, Nodos> env) throws Exception;
    
    abstract Nodos evaluar(Hashtable <String, Nodos> env) throws Exception;
    
}
