/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Hashtable;
import java.util.Vector;
//import javax.swing.tree.TreeNode;


public class Atom1 extends Nodos {
    private String letras;
    @Override
    protected boolean isList(){ 
        return false; 
    }
    public Atom1(String exp) throws Exception{
	if ( ! exp.matches(Patrones.LETRAS) && ! exp.matches(Patrones.OPERADOR) ){
            throw new Exception("Error!");
	}
	letras = exp;
	fichas = new Vector <> ();
	fichas.add(letras);
    }
    public Atom1(boolean valor){
	letras = valor ? "T" : "NIL";
	fichas = new Vector <> ();
	fichas.add(letras);
    }
    public Atom1(int i){
	letras = Integer.toString(i);
	fichas = new Vector <> ();
	fichas.add(letras);
    }
    @Override
    public String toString(){
	if ( letras.matches(Patrones.OPERADOR) ){
            return letras.replaceAll("\\A\\+", "");
	} else {
            return letras;
	}
    }
    protected Nodos evaluar() throws Exception{
        if ( Environment.varIsDefined(letras) ){
            return Environment.getVarValue(letras);
	} else {
            return this;
	}
    }
    @Override
    protected Nodos evaluar(Hashtable <String, Nodos> ambiente) throws Exception{
	return evaluar();
    }
    @Override
    protected Nodos evaluar(boolean falso, Hashtable <String, Nodos> ambiente) throws Exception{
        return evaluar();
    }
    @Override
    protected Nodos evaluar(boolean falso) throws Exception{
	return evaluar();
    }
}
