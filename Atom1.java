/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Vector;
import javax.swing.tree.TreeNode;


public class Atom1 extends Nodos {
    private String letras;
    protected boolean isList(){ 
        return false; 
    }
    public Atom(String exp) throws Exception{
	if ( ! exp.matches(Patrones.LETRAS) && ! exp.matches(Patrones.OPERADOR) ){
            throw new Exception("Error!");
	}
	letras = exp;
	fichas = new Vector <> ();
	fichas.add(letras);
    }
    public Atom(boolean valor){
	letras = valor ? "T" : "NIL";
	fichas = new Vector <> ();
	fichas.add(letras);
    }
    public Atom(int i){
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
    protected TreeNode evaluate() throws Exception{
        if ( Environment.varIsDefined(letras) ){
            return Environment.getVarValue(letras);
	} else {
            return this;
	}
    }
}
