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
}
