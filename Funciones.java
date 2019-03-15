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
public class Funciones extends Nodos {
    protected Nodos direccion;
    protected Nodos datos;
    protected Vector <String> fichasdedatos;
    protected Vector <String> direcciondefichas;
    
    public Funciones(Vector <String> exp) throws Exception{
	consHelper(exp);
    }
    public Funciones(Nodos t) throws Exception{
	consHelper(t.fichas);
    }
    public Funciones(Nodos a, Nodos d){
	direccion = a;
	datos = d;
	fichasdedatos = d.fichas;
	direcciondefichas = a.fichas;
	fichas = new Vector <> ();
	fichas.add("(");
	fichas.addAll(a.fichas);
	fichas.add(".");
	fichas.addAll(d.fichas);
	fichas.add(")");
    }
    public Funciones(Funciones exp) throws Exception{
	datos = Nodos.crear(exp.fichasdedatos);
	direccion = Nodos.crear(exp.direcciondefichas);
	fichasdedatos = new Vector <> (exp.fichasdedatos);
	direcciondefichas = new Vector <> (exp.direcciondefichas);
    }
    
    private void constante(Vector <String> exp) throws Exception{
	if ( exp.size() > 0 && exp.get(0).matches("[(]") ){ // some sanity checking for now
            int i = 1;
            int inicio = 3;
            if ( "(".equals(exp.get(i)) ){
		int open = 1;
                    while ( open > 0 && i < exp.size() ){
			i++;
			if ( "(".equals(exp.get(i)) ){
                            open++;
			} else if ( ")".equals(exp.get(i)) ){
                            open--;
			}
                    }
		inicio = i + 1;
            }
            i = inicio > 3 ? exp.indexOf(".", inicio) : 2;
            direcciondefichas = new Vector <>(exp.subList(1,i));
            direccion = Nodos.crear(direcciondefichas);
            fichasdedatos = new Vector <>(exp.subList(i+1, exp.size() - 1));
            datos = Nodos.crear(fichasdedatos);
            fichas = new Vector <> ();
            fichas.add("(");
            fichas.addAll(direcciondefichas);
            fichas.add(".");
            fichas.addAll(fichasdedatos);
            fichas.add(")");
        } else {
            throw new Exception("¡Error! Expresion invalida: " + exp.toString());
        }
    }
    @Override
    protected boolean isList(){
	return datos.toString().matches("NIL") || datos.isList();
    }
    @Override
    public String toString(){
	if ( isList() ){
            try {
		return toListString();
            } catch (Exception e){
                return "(" + direccion.toString() + " . " + datos.toString() + ")";
            }
	} else {
            return "(" + direccion.toString() + " . " + datos.toString() + ")";
	}
    }
    protected String toListString() throws Exception{
	return "(" + ManipuladordeStrings.Acoplar(toVector() , " ") + ")";
    }
    private Vector <String> toVector() throws Exception{
	if ( ! isList() ){
            throw new Exception("¡Error!");
	}
	Vector <String> mivector = new Vector <> ();
	Funciones tmp = this;
	while ( tmp.isList() ){
            mivector.add(tmp.direccion.toString());
            try { 
		tmp = new Funciones(tmp.fichasdedatos);
            } catch (Exception e){
		break;
            }
        }
        return mivector;
    }
    @Override
    protected Nodos evaluar() throws Exception{
        return this.evaluar(false);
    }
    @Override
    protected Nodos evaluar(Hashtable <String, Nodos> env) throws Exception{
	return evaluar(false, env);
    }
    @Override
    protected Nodos evaluar(boolean flag, Hashtable <String, Nodos> env) throws Exception{	
	Hashtable <String, Nodos> oldVars = Ambiente.getVarTable();
	Environment.mergeVars(env);
	Nodos rtn = evaluar(flag);
	Environment.setVars(oldVars);
	return rtn;
    }
    @Override
    protected Nodos evaluar( boolean flag ) throws Exception{
	String a = direccion.evaluar().toString();
	Funciones parametros;
	Nodos rtn;
        if ( flag && a.matches(Patrones.NUMERIC_ATOM) ){
            return direccion.evaluar();
	} else if ( a.matches("NIL") || a.matches("T") ){
            return a.matches("NIL") ? Primitives.NIL() : Primitives.T();
	} else if ( Environment.varIsDefined(a) ){
            return Environment.getVarValue(a);
	} else if ( Environment.functionIsDefined(a) ){
            return Environment.executeFunction(a, TreeNode.create(dataTokens));
	} else if ( a.matches("CAR") || a.matches("CDR") ){
			SExpression s;
			if ( data.isList() ){
				s = new SExpression(dataTokens);
				s = new SExpression(s.address.evaluate().tokens);
				// s = new SExpression(s.evaluate().tokens);
			} else {
				s = new SExpression(dataTokens);
			}
			params = s;
		} else if ( a.matches("DEFUN") ){
			return Primitives.DEFUN((SExpression) data);
		} else {
			params = (SExpression) data;
		}

		try{
			rtn = invokePrimitive(a, params);
			
			
			return rtn;
		} catch (Exception e){
			throw e;
			// throw new Exception("Error! Undefined literal: " + toString());
			// throw new Exception("Error!");
		}
    }
}
