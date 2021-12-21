package com.lang.stu.linearlist;

//带头结点的单链表类，实现线性表接口
public class HSLinkedList<E> implements LList<E> {

	// 单链表结点类
	protected Node<E> head; // 头指针，指向单链表的头结点
	protected Node<E> rear; // 尾指针，指向单链表最后一个结点
	protected int n; // 单链表长度

	// 构造空链表
	public HSLinkedList() {
		this.head = new Node<E>(); // 创建头结点，值为 null
		this.rear = this.head;
		this.n = 0;
	}

	@Override
	public boolean isEmpty() { //判断单链表是否为空，O(1)
		return this.head.next == null;
	}

	@Override
	public int length() {  //返回单链表长度，O(1)
		return this.n;
	}


	@Override
	public E get(int index) { // 返回序号为 index 的对象，index 初值为0
        // 若单链表空或序号错误返回null，O(n)
		if (index >= 0) {
			int j = 0;
			Node<E> p = this.head.next;
			while (p != null && j < index) {
				j++;
				p = p.next;
			}
			if ( p != null)
				return (E) p.data;
		}
		return null;
	}

	 //设置序号为 index 的对象为 element，O(n)
	@Override
	public E set(int index, E element) {
		 // 若操作成功返回原对象，否则返回null
		if (index >= 0 && element != null) {
			int j = 0;
			Node<E> p = this.head.next;
			while( p != null && j < index){
				j++;
				p = p.next;
			}
			if (p != null) {
				E old = (E) p.data;
				p.data = element;
				return old; //若操作成功返回原对象
			}
		}
		return null;
	}

    @Override
    public boolean add(E element) {  //在单链表最后添加对象，O(1)
        if(element == null)
            return false;
        this.rear.next = new Node<E>(element); //尾插入
        this.rear = this.rear.next; //移动尾指针
        this.n++;
        return true;
    }

	@Override
	public boolean add(int index, E element) { // 在指定位置插入非空的指定对象
		if( element == null) // 若操作成功返回true，O(n)
			return false;

		if(index >= this.n)
		    return this.add(element);  //插入在最后
		else {
		    int j = 0;
		    Node<E> p = this.head;
		    while(p.next != null && j < index) { //若index<=0，插入在头结点之后
                // 上一行代码中的 p.next!=null 可以去掉， 前面已经排除了尾插入的情况（个人认为）
		        j++;
		        p = p.next;
            }
            p.next = new Node<E>(element, p.next); //插入在p结点之后，包括头插入、中间插入
            this.n++;
            return true;
        }
	}


	@Override
	public E remove(int index) { //移去 index 位置的对象，O(n)
        E old = null;    // 若操作成功返回被移去对象，否则返回null
        if (index >= 0) {  // 头删除、中间/尾删除
            int j = 0;
            Node<E> p = this.head;
            while (p.next != null && j < index) { // 定位到待删除结点的前驱结点
                j++;
                p = p.next;
            }
            if(p.next != null) {
                old = (E) p.next.data;
                if(p.next == this.rear) //删除的对象如果是最后一个结点， 需要移动尾指针 指向p结点
                    this.rear = p;
                p.next = p.next.next; //删除p的后继结点
                this.n--;
            }
        }
		return old;
	}

	@Override
	public void clear() {  // 清空单链表，O(1)
		this.head.next = null;
		this.rear = this.head;
		this.n = 0;
	}

    @Override
    public String toString() { //返回显示单链表所有元素值对应的字符串
        String str = "(";   //单链表遍历算法，O(n)
        Node<E> p = this.head.next;
        while (p != null) {
            str += p.data.toString();
            p = p.next;
            if (p != null)
                str += ", ";
        }
        return str + ")";
    }
}
