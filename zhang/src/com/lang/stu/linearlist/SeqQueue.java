package com.lang.stu.linearlist;

//顺序循环队列类
public class SeqQueue<E> implements QQueue<E> {
	
	private Object value[];	  //存储队列的数据元素
	private int front, rear;	 //front、rear为队列头尾下标
	
	 //构造指定容量的空队列
	public SeqQueue(int capacity){
		this.value = new Object[Math.abs(capacity)];
		this.front = this.rear = 0;
	}

	//构造默认容量的空队列
	public SeqQueue() {
		this(10);
	}
	
	  //判断队列是否空，若空返回true
	public boolean isEmpty() {
		return this.front == this.rear;
	}

	//元素element入队，若操作成功返回true
	public boolean enqueue(E element) {
		if(element == null)
			return false;	//空对象（null）不能入队
		if(this.front == (this.rear + 1) % this.value.length){ //若队列满，则扩充容量
			Object[] temp = this.value;
			this.value = new Object[temp.length * 2];
			int i = this.front, j = 0;
			while(i != this.rear){	//按队列元素次序，复制数组元素
				this.value[j] = temp[i];
				i = (i + 1) % temp.length;
				j++;
			}
			this.front = 0;
			this.rear = j;
		}
		this.value[this.rear] = element;
		this.rear = (this.rear + 1) % this.value.length;
		return true;
	}
	 //出队，返回当前队头元素，若队列空返回null 
	public E dequeue() {
		if( !isEmpty()) {	 //队列不空
			E temp = (E) this.value[this.front]; //取得队头元素
			this.front = (this.front + 1) % this.value.length;
			return temp;
		}
		return null;
	}
	
	public String toString() { //返回队列中各元素的字符串描述
		String str = " [";
		if( !isEmpty()){
			str += this.value[this.front].toString();
			int i = (this.front + 1) % this.value.length;
			while(i != this.rear){
				str += "," + this.value[i].toString();
				i = (i + 1 )% this.value.length;
			}
		}
		
		return str += "] ";
	}
	
	public static void main(String[] args) {
		SeqQueue<Integer> que = new SeqQueue<Integer>(5);
		que.enqueue(new Integer(10));
		que.enqueue(new Integer(20));

		System.out.println("dequeue : " + que.dequeue().toString() + "  " + que.dequeue().toString() + "  ");
		System.out.println(que.toString());

		que.enqueue(new Integer(30));
		que.enqueue(new Integer(40));
		que.enqueue(new Integer(50));
		que.enqueue(new Integer(60));
		System.out.println(que.toString());

		que.enqueue(new Integer(70));
		System.out.println(que.toString());
		
		que.enqueue(new Integer(80));
		System.out.println(que.toString());
	}
}

/*
dequeue : 10  20  
 []
 [30, 40, 50, 60]
 [30, 40, 50, 60, 70]

*/

