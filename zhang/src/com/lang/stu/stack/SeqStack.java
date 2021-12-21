package com.lang.stu.stack;

//顺序栈类
public class SeqStack<E> implements SStack<E> {
	
	private Object value[]; //存储栈的数据元素
	private int top;  //top为栈顶元素下标
	
	public SeqStack(int capacity) { //构造指定容量的空栈
		this.value = new Object[Math.abs(capacity)];
		this.top = -1;
	}
	
	public SeqStack() { //构造默认容量的空栈
		this(10);
	}

	@Override
	public boolean isEmpty() {
		//判断是否空栈，若空栈返回true
		return this.top == -1;
	}

	@Override
	public boolean push(E element) { //元素element入栈，若操作成功返回true
		if (element == null) 
			return false; //空对象（null）不能入栈
		if (this.top == value.length -1) {  //若栈满，则扩充容量
			Object[] temp = this.value;
			this.value = new Object[temp.length * 2]; // 扩容 2 倍
			for (int i = 0; i < temp.length; i++) {
				this.value[i] = temp[i];
			}
		}
		this.top++;
		this.value[this.top] = element;
		return true;
	}

	@Override
	public E pop() { //出栈，返回当前栈顶元素，若栈空返回null
		if (!isEmpty())
			return (E) this.value[this.top--];
		else 
			return null;
	}

	@Override
	public E get() { // 取栈顶元素值，未出栈，栈顶元素未改变
		if (!isEmpty())
			return (E) this.value[this.top]; // 与出栈的 pop 方法区别: 没有修改栈顶top的值
		else
			return null;
	}
	
	@Override
	public String toString() { // 返回栈中各元素的字符串描述
		String str = "{";
		if (this.top != -1)
			str += this.value[this.top].toString();
		for (int i = this.top - 1; i >= 0; i--)
			str += ", " + this.value[i].toString();
		return str + "} ";
	}
	public static void main(String[] args) {
		SeqStack<String> stack = new SeqStack<String>(20);
		System.out.print("Push: ");
		char ch = 'a';
		for (int i = 0; i < 5; i++) {
			String str = (char) (ch + i) + "";
			stack.push(str);
			System.out.print(str + "  ");
		}
		
		System.out.println("\n" + stack.toString());
		System.out.print("Pop : ");

		while (!stack.isEmpty()) // 全部出栈
			System.out.print(stack.pop().toString() + " ");

		System.out.println();
	}

}

/*
Push: a  b  c  d  e  
{e, d, c, b, a} 
Pop : e d c b a 


*/