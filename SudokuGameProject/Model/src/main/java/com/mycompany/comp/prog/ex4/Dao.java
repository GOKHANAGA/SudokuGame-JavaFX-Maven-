/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comp.prog.ex4;

/**
 *
 * @author student
 */
public interface Dao <T> {
    public T read();
    public void write(T obj);
}
