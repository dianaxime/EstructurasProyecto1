/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.Arrays;

/**
 *
 * @author DIANA
 */
public class Lisp {
     // Obtener el numero mayor 
    public double max(int[] miarray){
        //supone que el primero es el mayor
        int maxi = miarray[0];
        //recorre todos los numeros buscando uno mayor
        for (int i=1; i<miarray.length; i++){
            //si hay uno mas alto, lo define como el nuevo mayor
            if (miarray[i]>maxi){
                maxi = miarray[i];
            }
        }
        return maxi;
    }
    
     // Obtener el numero menor 
    public double min(int[] miarray){
        //supone que el primero es el menor
        int mini = miarray[0];
        //recorre todos los numeros buscando uno menor
        for (int i=1; i<miarray.length; i++){
            //si hay uno menor, lo define como el nuevo menor
            if (miarray[i]<mini){
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
    public double suma (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return suma(miarray, n-1)+miarray[n];
        }
    }
    public double resta (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return resta(miarray, n-1)-miarray[n];
        }
    }
    public double multiplicar (int[] miarray, int n){
       if (n==0){
            return miarray[n];
        }
        else {
            return multiplicar(miarray, n-1)*miarray[n];
        }
    }
    public double dividir (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return dividir(miarray, n-1)/miarray[n];
        }
    }
    public double car(int[] miarray){
       int a;
       a=miarray[0];
       return a;
    }
    public int[] cdr(int[] miarray){
        int[] newarray = new int[miarray.length];
        for (int i=1; i<miarray.length; i++){
            newarray[i-1] = miarray[i];
        }
        return newarray;
    }
    public boolean minusp(int num){
        boolean result = false;
        if (num<0){
            result = true;
        } else if (num>0){
            result = false;
        }
        return result;
    }
    public boolean plusp(int num){
        boolean result = false;
        if (num<0){
            result = false;
        } else if (num>0){
            result = true;
        }
        return result;
    }
    public boolean zerop(int num){
        boolean result = false;
        if (num==0){
            result = true;
        }
        return result;
    }
    public boolean equal(int num1, int num2){
        boolean result = false;
        if (num1==num2){
            result = true;
        }
        return result;
    }
    public int expt(int num1, int num2){
        int result = 1;
        for (int i=0; i<Math.abs(num2); i++){
            result *= num1;
        }
        return result;
    }
    public int abs(int num){
        int result = 0;
        if (num<0){
            result=-1*num;
        }
        return result;
    }
    public float sqrt(int num){
        float i=0;
        float result;
        do{
            i+=0.001;
            result = i*i;
        } while(num>=result);
        return i;
    }
}
