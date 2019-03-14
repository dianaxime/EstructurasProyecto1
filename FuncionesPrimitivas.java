/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

/**
 *
 * @author DIANA
 */
public class FuncionesPrimitivas {
    public static Nodos T(){ 
	return new Atom1(true); 
    }
    public static Nodos NIL(){ 
	return new Atom1(false); 
    }
    public static SExpression CONS ( SExpression exp ) throws Exception {
	SExpression concatenar = new SExpression(exp.dataTokens);
	return new SExpression(exp.address.evaluar(), concatenar.address.evaluar());
    }
    public static Nodos CAR ( SExpression exp ) throws Exception{
		return exp.address;
    }
    public static Nodos CDR ( SExpression exp )  throws Exception{
	return exp.data;
    }
    public static Nodos ATOM ( SExpression exp ) throws Exception{
	return Nodos.crear(exp.address.evaluate().toString().matches(Patrones.LETRAS));
    }
    public static Nodos EQ ( SExpression exp ) throws Exception{
	return Nodos.crear(exp.address.evaluar(true).toString().matches(exp.data.evaluar(true).toString()));
    }
    public static Nodos NULL ( SExpression exp ) throws Exception{
	return Nodos.crear(exp.data.evaluar().toString().matches("NIL"));
    }
    public static Nodos INT ( SExpression exp ) throws Exception{
	return Nodos.crear(exp.address.evaluar(true).toString().matches(Patrones.OPERADOR));
    }
    public static Nodos PLUS ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluar(true).toString()) + Integer.parseInt(exp.data.evaluar(true).toString()));
    }
}
