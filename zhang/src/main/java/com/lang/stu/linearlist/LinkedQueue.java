package com.lang.stu.linearlist;

//链式队列类
public class LinkedQueue<E> implements QQueue<E> {

	// front 和 rear 分别指向队头和队尾结点
	private Node<E> front, rear;

	// 构造空队列
	public LinkedQueue() {
		this.front = this.rear = null;
	}

	// 判断队列是否空，若空返回true
	public boolean isEmpty() {
		return this.front == null && this.rear == null;
	}

	// 元素 element 入队，若操作成功返回 true
	public boolean enqueue(E element) {
		if (element == null)
			return false; // 空对象（null）不能入队

		Node<E> q = new Node(element);
		if (!isEmpty()) // 队列不空时
			this.rear.next = q; // q结点作为新的队尾结点
		else
			this.front = q;
		this.rear = q;
		return true;
	}

	// 出队，返回当前队头元素，若队列空返回 null
	public E dequeue() {
		if (!isEmpty()) {
			E temp = this.front.data; // 取得队头元素
			this.front = this.front.next; // 删除队头结点
			if (this.front == null)
				this.rear = null;
			return temp;
		}
		return null;
	}

	// 返回栈中各元素的字符串描述
	public String toString() {
		String str = " {";
		Node<E> p = this.front;
		while (p != null) {
			str += p.data.toString();
			p = p.next;
			if (p != null)
				str += ", ";
		}
		return str + "} ";
	}

	public static void main(String[] args) {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>();
		System.out.print("enqueue: ");

		for (int i = 1; i <= 5; i++) {
			Integer intObj = new Integer(i);
			q.enqueue(intObj);
			System.out.print(intObj + "  ");
		}
		System.out.println("\n" + q.toString());

		System.out.print("dequeue : ");
		while (!q.isEmpty())
			System.out.print(q.dequeue().toString() + "  ");
		System.out.println();
	}

	/*
	enqueue: 1  2  3  4  5  
	 {1, 2, 3, 4, 5} 
	dequeue : 1  2  3  4  5  
	*/
	
}
