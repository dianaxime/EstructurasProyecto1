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

}
