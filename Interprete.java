/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.io.IOException;

/**
 *
 * @author DIANA
 */
public class Interprete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
	    Analizador l = new Analizador(System.in);
	    Descifrador p = new Descifrador(l.Obtenersimbolos());
		p.evaluacion();
	} catch (IOException e){
            System.out.println("Fin...");
    	} catch (Exception e){	
            System.out.println("¡Error!");
            if ( args.length > 0 && args[0].matches("-d") ){
            System.out.println(e.getMessage());
	    e.printStackTrace();
	}
            System.exit(3);
    	}
    }
    
}
