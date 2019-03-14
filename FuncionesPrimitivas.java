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
    public static Nodos MINUS ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluate(true).toString()) - Integer.parseInt(exp.data.evaluate(true).toString()));
    }
    public static Nodos QUOTIENT ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluate(true).toString()) / Integer.parseInt(exp.data.evaluate(true).toString()));
    }
    public static Nodos TIMES ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluar(true).toString()) * Integer.parseInt(exp.data.evaluar(true).toString()));
    }
    public static Nodos REMAINDER ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluar(true).toString()) % Integer.parseInt(exp.data.evaluar(true).toString()));
    }
    public static Nodos LESS ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluar(true).toString()) < Integer.parseInt(exp.data.evaluar(true).toString()));
    }
    public static Nodos GREATER ( SExpression exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.address.evaluar(true).toString()) > Integer.parseInt(exp.data.evaluar(true).toString()));
    }
    public static Nodos COND ( SExpression exp ) throws Exception {
	SExpression a = new SExpression(exp.addressTokens);
	if ( a.address.evaluar().toString().matches("T") ){
            SExpression tmp = new SExpression(a.dataTokens);
            return tmp.address.evaluar(true);
	} else {
            SExpression b = new SExpression(exp.dataTokens);
            return COND(b);
	}
    }
    public static Nodos QUOTE ( SExpression exp ) throws Exception {
	return exp.address;
    }
    public static Nodos DEFUN (SExpression exp) throws Exception {
	String nombre = exp.address.toString();
	if ( ! nombre.matches(Patrones.FUNCION) ){
            throw new Exception("¡Error! Solo se pueden usar letras");
	}
	if ( FuncionesPrimitivas.primitivoExiste(nombre) ){
            throw new Exception("¡Error! No se puede reescribir una funcion");
	}
        SExpression d = new SExpression(exp.dataTokens);
	Nodos parametros = Nodos.create(d.addressTokens);
	SExpression tmp = new SExpression(d.dataTokens);
	Nodos cuerpo = Nodos.crear(tmp.addressTokens);
	Environment.registerFunction(nombre, parametros, cuerpo);
	return new Atom1(nombre);
    }
    private static boolean primitivoExiste(String nombre){
	java.lang.reflect.Method metodo;
	try{
            metodo = FuncionesPrimitivas.class.getDeclaredMethod(nombre, SExpression.class);
            return true;
	} catch (SecurityException e){
            return false;
	}
    }
}
