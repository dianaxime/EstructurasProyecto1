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
public interface Stack<E> {
    public void push(E item);
   //Añadir uno
   
   public E pop();
   //Borra el ultimo y lo returna
   
   public E peek();
   //Retorna el ultimo valor
   
   public boolean empty();
   //verifica si esta vacio
   
   public int size();
   // 
    
}
