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
public class Lisp {
     // Obtener el numero mayor 
    public Comparable max(Comparable[] miarray){
        //supone que el primero es el mayor
        Comparable maxi = miarray[0];
        //recorre todos los numeros buscando uno mayor
        for (int i=1; i<miarray.length; i++){
            //si hay uno mas alto, lo define como el nuevo mayor
            if (miarray[i].compareTo(maxi)==1){
                maxi = miarray[i];
            }
        }
        return maxi;
    }
    
     // Obtener el numero menor 
    public Comparable min(Comparable[] miarray){
        //supone que el primero es el menor
        Comparable mini = miarray[0];
        //recorre todos los numeros buscando uno menor
        for (int i=1; i<miarray.length; i++){
            //si hay uno menor, lo define como el nuevo menor
            if (miarray[i].compareTo(mini)==1){
                mini = miarray[i];
            }
        }
        return mini;
    }
   /* public int calculate (int num1, int num2, String op){
        //recibe dos numeros y un operador 
        //dependiendo del operador asi sera la operacion que realice
        // en resta y division se intercambian los operandos por el orden de las operaciones 
        int resultado;
        resultado = 0;
        switch (op){
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num2 - num1;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                resultado = num2 / num1;
                break;
        }
        return resultado;
    }*/
    public int suma (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return suma(miarray, n-1)+miarray[n];
        }
    }
    public int resta (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return miarray[n]-resta(miarray, n-1);
        }
    }
    public int multiplicar (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return miarray[n]*multiplicar(miarray, n-1);
        }
    }
    public int dividir (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return miarray[n]/multiplicar(miarray, n-1);
        }
    }
    public int car(int[] miarray){
       int a;
       a=miarray[0];
       return a;
    }
    public int[] cdr(int[] miarray){
        int[] newarray = new int[miarray.length-2];
        for (int i=1; i<miarray.length; i++){
            newarray[i-1] = miarray[i];
        }
        return newarray;
    }
}
