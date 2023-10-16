package com.lang.stu.linearlist;

//线性表接口
public interface LList<E> {

	boolean isEmpty(); // 判断线性表是否为空，若空返回true

	int length(); // 返回线性表长度

	E get(int index); // 返回序号为index的对象，index初值为0

	E set(int index, E element); // 设置序号为index的对象为element，返回原对象

	boolean add(int index, E element); // 插入element对象，插入后对象序号为index

	boolean add(E element); // 插入element对象，插入位置没有约定

	E remove(int index); // 移去序号为index的对象，返回被移去对象

	void clear(); // 清空线性表

}
