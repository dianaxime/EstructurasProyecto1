/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package proyecto1;

/**
 *
 * @author DIANA
 */

//funciones incluidas en un lisp
public class FuncionesPrimitivas {
    public static Nodos T(){ 
	return new Atom1(true); 
    }
    public static Nodos NIL(){ 
	return new Atom1(false); 
    }
    public static Funciones CONS ( Funciones exp ) throws Exception {
	Funciones concatenar = new Funciones(exp.fichasdedatos);
	return new Funciones(exp.direccion.evaluar(), concatenar.direccion.evaluar());
    }
    public static Nodos CAR ( Funciones exp ) throws Exception{
		return exp.direccion;
    }
    public static Nodos CDR ( Funciones exp )  throws Exception{
	return exp.datos;
    }
    public static Nodos ATOM ( Funciones exp ) throws Exception{
	return Nodos.crear(exp.direccion.evaluar().toString().matches(Patrones.LETRAS));
    }
    public static Nodos EQ ( Funciones exp ) throws Exception{
	return Nodos.crear(exp.direccion.evaluar(true).toString().matches(exp.datos.evaluar(true).toString()));
    }
    public static Nodos NULL ( Funciones exp ) throws Exception{
	return Nodos.crear(exp.datos.evaluar().toString().matches("NIL"));
    }
    public static Nodos INT ( Funciones exp ) throws Exception{
	return Nodos.crear(exp.direccion.evaluar(true).toString().matches(Patrones.OPERADOR));
    }
    public static Nodos PLUS ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) + Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos MINUS ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) - Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos QUOTIENT ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) / Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos TIMES ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) * Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos REMAINDER ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) % Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos LESS ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) < Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos GREATER ( Funciones exp ) throws Exception{
	return Nodos.crear(Integer.parseInt(exp.direccion.evaluar(true).toString()) > Integer.parseInt(exp.datos.evaluar(true).toString()));
    }
    public static Nodos COND ( Funciones exp ) throws Exception {
	Funciones a = new Funciones(exp.direcciondefichas);
	if ( a.direccion.evaluar().toString().matches("T") ){
            Funciones tmp = new Funciones(a.fichasdedatos);
            return tmp.direccion.evaluar(true);
	} else {
            Funciones b = new Funciones(exp.fichasdedatos);
            return COND(b);
	}
    }
    public static Nodos QUOTE ( Funciones exp ) throws Exception {
	return exp.direccion;
    }
    public static Nodos DEFUN (Funciones exp) throws Exception {
	String nombre = exp.direccion.toString();
	if ( ! nombre.matches(Patrones.FUNCION) ){
            throw new Exception("¡Error! Solo se pueden usar letras");
	}
	if ( FuncionesPrimitivas.primitivoExiste(nombre) ){
            throw new Exception("¡Error! No se puede reescribir una funcion");
	}
        Funciones d = new Funciones (exp.fichasdedatos);
	Nodos parametros = Nodos.crear(d.direcciondefichas);
        Funciones tmp = new Funciones (d.fichasdedatos);
	Nodos cuerpo = Nodos.crear(tmp.direcciondefichas);
	Ambiente.registrarFuncion(nombre, parametros, cuerpo);
	return new Atom1(nombre);
    }
    private static boolean primitivoExiste(String nombre) throws NoSuchMethodException{
	java.lang.reflect.Method metodo;
	try{
            metodo = FuncionesPrimitivas.class.getDeclaredMethod(nombre, Funciones.class);
            return true;
	} catch (NoSuchMethodException | SecurityException e){
            return false;
	}
    }
}
