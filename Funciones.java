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
// se encarga de la entrada, salida, evaluacion y construccion de varios puntos del programa
public class Funciones extends Nodos {
    protected Nodos direccion;
    protected Nodos datos;
    protected Vector <String> fichasdedatos;
    protected Vector <String> direcciondefichas;
    
    public Funciones(Vector<String> exp) throws Exception{
	constante(exp);
    }
    public Funciones(Nodos t) throws Exception{
	constante(t.fichas);
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
	if ( exp.size() > 0 && exp.get(0).matches("[(]") ){ 
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
    protected Nodos evaluar(boolean flag, Hashtable <String, Nodos> amb) throws Exception{	
	Hashtable <String, Nodos> antigua = Ambiente.obtener();
	Ambiente.unirVariables(amb);
	Nodos rtn = evaluar(flag);
	Ambiente.modificarVariable(antigua);
	return rtn;
    }
    @Override
    protected Nodos evaluar( boolean flag ) throws Exception{
	String a = direccion.evaluar().toString();
	Funciones parametros;
	Nodos rtn;
        if ( flag && a.matches(Patrones.OPERADOR) ){
            return direccion.evaluar();
	} else if ( a.matches("NIL") || a.matches("T") ){
            return a.matches("NIL") ? FuncionesPrimitivas.NIL() : FuncionesPrimitivas.T();
	} else if ( Ambiente.funcionExiste(a) ){
            return Ambiente.getValor(a);
	} else if ( Ambiente.funcionExiste(a) ){
            return Ambiente.ejecutarFuncion(a, Nodos.crear(fichasdedatos));
	} else if ( a.matches("CAR") || a.matches("CDR") ){
            Funciones s;
            if ( datos.isList() ){
                s = new Funciones(fichasdedatos);
                s = new Funciones(s.direccion.evaluar().fichas);
            } else {
                s = new Funciones(fichasdedatos);
            }
        parametros = s;
	} else if ( a.matches("DEFUN") ){
            return FuncionesPrimitivas.DEFUN((Funciones) datos);
	} else {
            parametros = (Funciones) datos;
	}
	try{
            rtn = invocar(a, parametros);
            return rtn;
	} catch (Exception e){
            throw e;
	}
    }
    private Nodos invocar(String name, Funciones obj) throws Exception{
	java.lang.reflect.Method m;
        m = FuncionesPrimitivas.class.getDeclaredMethod(name, Funciones.class);
	m.setAccessible(true);
	Object o = m.invoke(null, obj);
	if ( o.toString().matches("true") ){
            return new Atom1("T");
	} else if ( o.toString().matches("false") ){
            return new Atom1("NIL");
        } else {
            return (Nodos) o;
	}
    }
}
