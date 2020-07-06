package com.javautils;

import java.util.LinkedList;

/**
 * ѭ���б����������õ�����ʱ���Զ�����������Ԫ��
 * 
 * @author ganxiangyong
 * @date 2015��4��28�� ����5:00:19
 */
public class CircularList<T> {

	private LinkedList<T> list = new LinkedList<T>();
	private static int COLLECTION_SIZE = 10; // Ĭ������Ϊ10

	public CircularList(int size) {
		if (size > 0) {
			COLLECTION_SIZE = size;
		}
	}

	/**
	 * ��ָ��������ӵ������б�ͷ���������������ָ����������ô��ɾ�������б�β��Ԫ��
	 * 
	 * @param t
	 *            Ҫ��ӵ�Ԫ��
	 */
	public synchronized void add(T t) {
		if (list.size() >= COLLECTION_SIZE) {
			list.pollLast();
		}

		list.offerFirst(t);
	}

	/**
	 * ��������ɾ����Ԫ��
	 * 
	 * @param t
	 *            Ҫɾ����Ԫ��
	 */
	public synchronized void remove(T t) {
		list.remove(t);
	}

	/**
	 * ��ȡ�����е�����Ԫ��
	 * 
	 * @return
	 */
	public LinkedList<T> getList() {
		return list;
	}

}
