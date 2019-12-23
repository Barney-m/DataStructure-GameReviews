package com.app.adt.list;

//Author: Ng Jun Jet

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>,ILinkedList<E>{
   private Node<E> head; 
	private int size; 

	public LinkedList() {
		head = new Node<E>(null);
	}

	public Node<E> getHead() {
		return head;
	}

	public E add(E data, int index){
		if (index > size) {
			throw new ArrayIndexOutOfBoundsException("Array Out Of Bounds");
		}

		Node<E> cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		Node<E> node = new Node<E>(data);
		cur.next = node;
		size++;
		return node.data;
	}


	public E add(E data){
		return add(data, size);
	}
	

	public void add(Node<E> node){
		Node<E> cur = head;
		while(cur.next != null){
			cur = cur.next;
		}
		cur.next = node;
		
		while(node != null){
			size ++;
			node = node.next;
		}
	}

	public E remove(int index) {
		if (index > size - 1 || index < 0) {
			throw new ArrayIndexOutOfBoundsException("Index Out Of Bounds");
		}

		Node<E> cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}

		Node<E> temp = cur.next;
		cur.next = temp.next;
		temp.next = null;

		size--;
		return temp.data;
	}


	public E remove(){
		return remove(size - 1);
	}


	public void removeDuplicateNodes() {
		Node<E> cur = head.next;
		while (cur != null) { // 外循环
			Node<E> temp = cur;
			while (temp != null && temp.next != null) {
				if (cur.data.equals(temp.next.data)) {
					Node<E> duplicateNode = temp.next;
					temp.next = duplicateNode.next;
					duplicateNode.next = null;
					size --;
				}
				temp = temp.next;
			}
			cur = cur.next;
		}
	}


	public E get(int k){
		Node<E> pre = head.next;
		Node<E> post = head.next;
		for (int i = 1; i < k; i++) {
			if (pre != null) {
				pre = pre.next;
			}
		}
		if (pre != null) {
			while (pre != null && pre.next != null) {
				pre = pre.next;
				post = post.next;
			}
			return post.data;
		}
		return null;
	}

	public void reverse() {
		Node<E> cur = head.next; 
		Node<E> pre = null; 

		while (cur != null) { 
			Node<E> next = cur.next; 
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		head.next = pre;
	}

	public boolean isEmpty() {
		return size == 0;
	}


	public String toString() {
            String str = "";
		Node<E> cur = head.next;
		while (cur != null) {
			str = cur.data + " ";
			cur = cur.next;
		}
		return str;
	}

	

	public int size(){
		return size;
	}

 /*******************************************************
 *
 *  The Node class
 *
 ********************************************************/
   private static class Node<E>
   {
      private E data;
      private Node<E> next;

      public Node(E data) { 
		this.data = data;
	}
   }

 /*******************************************************
 *
 *  The Iterator class
 *
 ********************************************************/

   public Iterator<E> iterator()
   {
      return new LinkedListIterator();
   }

   private class LinkedListIterator  implements Iterator<E>
   {
      private Node<E> nextNode;

      public LinkedListIterator()
      {
         nextNode = head;
      }

      public boolean hasNext()
      {
         return nextNode != null;
      }

      public E next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         E res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }
}
