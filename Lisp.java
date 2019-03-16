/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package proyecto1;

import java.util.Arrays;

/**
 *
 * @author DIANA
 */
public class Lisp {
     // Obtener el numero mayor 
    public int max(int[] miarray){
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
    public boolean mayor(int[] miarray){
        int mayor= miarray[0];
        for (int i=1; i<miarray.length; i++){
            if (miarray[i]>=mayor){
                return false;
            }
        }
        return true;
    }
    public boolean menor(int[] miarray){
        int menor= miarray[0];
        for (int i=1; i<miarray.length; i++){
            if (miarray[i]<=menor){
                return false;
            }
        }
        return true;
    }
    public boolean equal(int[] miarray){
        int comparar= miarray[0];
        for (int i=1; i<miarray.length; i++){
            if (miarray[i]!=comparar){
                return false;
            }
        }
        return true;
    }
    //verifica si todos son verdaderos o falsos
    public boolean cond(boolean[] miarray){
        for (int i=0; i<miarray.length; i++){
            if (miarray[i]==false){
                return false;
            }
        }
        return true;
    }
    // Obtener el numero menor 
    public int min(int[] miarray){
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
            return resta(miarray, n-1)-miarray[n];
        }
    }
    public int multiplicar (int[] miarray, int n){
       if (n==0){
            return miarray[n];
        }
        else {
            return multiplicar(miarray, n-1)*miarray[n];
        }
    }
    public int dividir (int[] miarray, int n){
        if (n==0){
            return miarray[n];
        }
        else {
            return dividir(miarray, n-1)/miarray[n];
        }
    }
    public int car(int[] miarray){
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
    public String TorNIL(boolean valor){
        return valor ? "T": "NIL";
    }
}
