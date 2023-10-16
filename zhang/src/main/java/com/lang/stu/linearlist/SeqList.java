package com.lang.stu.linearlist;

//顺序表类，实现线性表接口
public class SeqList<E> implements LList<E> {

	private Object[] table; // 对象数组，私有成员
	private int n; // 顺序表长度

	// 构造方法，创建指定容量的空表
	public SeqList(int capacity) {
		// Math.abs(i)返回参数的绝对值
		this.table = new Object[Math.abs(capacity)];
		this.n = 0;
	}

	public SeqList() {
		this(16); // 指定空表的默认容量
	}

	// 判断顺序表是否为空，若空返回true
	public boolean isEmpty() { // O(1)
		return this.n == 0;
	}

	// 返回顺序表长度，O(1)
	public int length() {
		return this.n;
	}

	// 返回 index 位置的对象，index 初值为 0
	public E get(int index) {
		// O(1)，若index指定序号无效则返回null
		if (index >= 0 && index < this.n)
			// 使用了未经检查或不安全的操作
			return (E) this.table[index];
		return null;
	}

	// 设置 index 位置的对象为 element，O(1)
	public E set(int index, E element) {
		// 若操作成功返回原对象，否则返回 null
		if (index >= 0 && index < this.n && element != null) {
			E old = (E) this.table[index];
			this.table[index] = element;
			return old;
		}
		return null;
	}

	// 在 index 位置插入 element 对象，O(n)
	public boolean add(int index, E element) {
		if (element == null) // 不能插入 null，若操作成功返回 true
        return false; // 不能添加null

		if (this.n == table.length) {
			Object[] temp = this.table;
			this.table = new Object[temp.length * 2];
			for (int i = 0; i < temp.length; i++)
				this.table[i] = temp[i];
		}

		if (index < 0) // 下标容错
			index = 0;
		if (index > this.n)
			index = this.n;
		
		// 元素后移，平均移动 n/2
		for (int j = this.n - 1; j >= index; j--)
			this.table[j + 1] = this.table[j];
		this.table[index] = element;
		this.n++;
		return true;
	}

	// 在顺序表最后插入 element 对象
	public boolean add(E element) {
		return add(this.n, element);
	}

	// 移去 index 位置的对象，O(n)
	public E remove(int index) {
		// 若操作成功返回被移去对象，否则返回 null
		if (this.n != 0 && index >= 0 && index < this.n) {
			E old = (E) this.table[index];
			// 元素前移，平均移动n/2
			for (int j = index; j < this.n - 1; j++)
				this.table[j] = this.table[j + 1];
			this.table[this.n - 1] = null;
			this.n--;
			return old;
		}
		return null;
	}

	// 清空线性表
	public void clear() {
		if (this.n != 0) {
			for (int i = 0; i < this.n; i++)
				this.table[i] = null;
			this.n = 0;
		}
	}

	// 返回显示线性表所有元素值的字符串，形式为{,}
	public String toString() {
		String str = " (";
		if (this.n != 0) {
			for (int i = 0; i < this.n - 1; i++) 
				str += this.table[i].toString() + ", ";
			str += this.table[this.n - 1].toString();
		}
		return str + ") ";
	}

}
