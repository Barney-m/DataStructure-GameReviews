/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.adt.tree;

import com.app.adt.IIterator;

public interface ITreeIterator<T> {
  public IIterator<T> getPreorderIterator();
  public IIterator<T> getPostorderIterator();
  public IIterator<T> getInorderIterator();
}
