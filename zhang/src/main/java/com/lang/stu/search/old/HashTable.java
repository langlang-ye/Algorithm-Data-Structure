//采用链地址法的散列表类，包括插入、删除、查找、遍历等操作。
package com.lang.stu.search.old;

import com.lang.stu.linearlist.Node; //导入单链表结点类
import com.lang.stu.linearlist.SinglyLinkedList; //导入单链表类

public class HashTable<E> {

	private Object[] table; // 散列表的对象数组

	public HashTable(int capacity) { // 构造指定容量的空表
		this.table = new Object[Math.abs(capacity)];
	}

	public HashTable() { // 构造默认容量的空表
		this(97); // 97 是 100 以内的最大素数
	}

	// 散列函数，确定关键字为key元素的散列地址
	public int hash(int key) {
		// 除留余数法，除数是散列表长度
		return key % table.length;
	}

	// 在散列表中插入指定元素
	public void insert(E element) {
		// 每个对象的 hashCode()方法返回整数值
		int key = element.hashCode();
		System.out.print(key + " ");

		int i = hash(key); // 散列地址
		if (table[i] == null) // 散列表中的指定位置空
			table[i] = new Node<E>(element);
		else { // 产生冲突，插入同义词冲突单链表
			Node<E> q = new Node<E>(element);
			// 散列表的一项相当于单链表的头结点
			Node<E> head = (Node<E>) table[i];  // 第一项
			// 单链表头插入
			q.next = head.next;	// 插入在第一个结点的后面
			head.next = q;
		}
	}

	// 遍历散列表及冲突单链表并输出其中全部元素
	public void print() {
		for (int i = 0; i < table.length; i++) {
			// 创建单链表; 散列表的一项相当于单链表的头结点
			SinglyLinkedList list = new SinglyLinkedList((Node<E>) table[i]);
			// 遍历单链表并输出元素值
			System.out.println("table[" + i + "]= " + list.toString());
		}
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < table.length; i++){
			SinglyLinkedList list = new SinglyLinkedList((Node<E>) table[i]);			
			str += "table[" + i + "]= " + list.toString() + "\n";
		}
		return str;
	}

	// 在散列表及冲突单链表中查找
	public Node<E> search(E element) {
		// 若查找成功返回结点，否则返回null
		int key = element.hashCode();
		int i = hash(key);
		if (table[i] == null)
			return null;
		else {
			Node<E> head = (Node<E>) table[i];
			SinglyLinkedList list = new SinglyLinkedList(head);
			// 返回在单链表中的查找结果
			return list.search(element);
		}
	}

	// 以查找结果判断单链表是否包含指定对象
	public boolean contain(E element) {
		// 若包含返回true，否则返回false
		return this.search(element) != null;
	}

	public boolean remove(E element) {
		int key = element.hashCode();
		int i = hash(key);
		if (table[i] == null)
			return false;
		else {
			
			Node<E> head = (Node<E>) table[i];
			// 欲删除散列表中当前对象
			if (element.equals(head.data)) {
				if (head.next == null) // 若冲突单链表空，则删除散列表中对象[只有一个对象， 可以直接将 table[i] = null;]
					table[i] = null;
				else { // 否则，将冲突单链表的第1个结点移动到散列表中
					Node<E> p = head.next;
					head.data = p.data;
					head.next = p.next;
				}
				return true; // 删除成功
			} else {
				// 在冲突单链表中删除对象，并返回删除结果
				SinglyLinkedList list = new SinglyLinkedList(head);
				return list.remove(element);
			}
		}
	}
	
	// langlang.sir 
	public boolean removeT(E element) {
		int key = element.hashCode();
		int i = hash(key);
		if (table[i] == null)
			return false;
		else {
			Node<E> head = (Node<E>) table[i];
			SinglyLinkedList list = new SinglyLinkedList(head);
			return list.removeT(element);
		}
	}

	public static void main(String[] args) {

		int n = 7;
		HashTable<Integer> hashTable = new HashTable<Integer>(n);
		System.out.print("插入关键字： ");

		int key;
		Integer element = null;
		for (int i = 0; i < n; i++) {
			// 产生n个随机数作为关键字
			key = (int) (Math.random() * 100);
			element = new Integer(key);
			// 散列表插入整数类型对象
			hashTable.insert(element);
		}
		System.out.println("\n散列表：");
		hashTable.print();

		System.out.println("查找 " + element + ", " + (hashTable.contain(element) ? "" : "不") + "成功");
		key = 50;
		System.out.println("查找 " + key + ", " + (hashTable.contain(new Integer(key)) ? "" : "不") + "成功");

		System.out.println("删除 " + element + ", " + (hashTable.remove(element) ? "" : "不") + "成功");

		System.out.println("删除 " + key + ", " + (hashTable.remove(new Integer(key)) ? "" : "不") + "成功");
		System.out.println("散列表：");
		hashTable.print();
	}

}


/*
程序运行结果如下：
插入关键字： 28 11 55 17 31 75 80 
散列表：
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 80, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}
17? 80? 查找 80, 成功
查找 50, 不成功
删除 80, 成功
删除 50, 不成功
散列表：
table[0]= {28}
table[1]= {}
table[2]= {}
table[3]= {17, 31}
table[4]= {11}
table[5]= {75}
table[6]= {55}



public final class Integer
    public int hashCode()                //覆盖Object类中方法
    {
        return value;
    }
} 
 **/
 
