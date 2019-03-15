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
public class Descifrador {
    Vector <ArboldeAnalisis> declaraciones = new Vector <> ();
    public Descifrador(Vector <String> fichas) throws Exception{
	Vector <String> estados;
	int i = 0;
	int k, l;
        while ( i < fichas.size() && i >= 0 ){
            k = fichas.indexOf("(", i);
            if ( k == i ){
                l = endOfExpression(new Vector <String> (fichas.subList(i, fichas.size()))) + i;
            } else if ( k > i ){
		l = k - 1;
            } else {
		l = fichas.size() - 1;
            }
            estados = convertToDotNotation( new Vector <String> (fichas.subList(i, l+1)));
            declaraciones.add(new ArboldeAnalisis(estados));
            i = l + 1;
	}
    }
}
