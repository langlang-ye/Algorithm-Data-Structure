package com.lang.stu.linearlist;

//队列接口
public interface QQueue<E> {

	// 判断队列是否为空，若空返回true
	boolean isEmpty();

	// 元素 element 入队，若操作成功返回 true
	boolean enqueue(E element);

	// 出队，返回当前队头元素，若队列空返回 null
	E dequeue();

}
