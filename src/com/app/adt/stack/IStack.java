/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.adt.stack;

public interface IStack<T> {
	boolean push(T value);
	
	T pop();
	
	boolean contains(T value);
	
	int size();
	
	void clear();
	
	boolean isEmpty();
}
