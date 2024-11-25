package prob04;

import java.util.Arrays;

public class MyStack03<T> {
	private int top;
	private T[] buffer;

	public MyStack03(int capacity) {
		/* 구현하기 */
		this.top = -1;
		this.buffer=(T[])new Object[capacity];
	}

	public void push(T s) {
		/* 구현하기 */
		this.top++;
		if(top==buffer.length) {
			resize();	
		}
		this.buffer[top]=s;
	}

	public T pop() throws MyStackException {
		/* 구현하기 */
		if(top==-1) {
			throw new MyStackException();
		}
		T target = buffer[top];
		this.top--;
		
		return target;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		if(top==-1) {
			return true;
		} else {
			return false;
		}
	}

	private void resize() {
		/* 구현하기 - 배열 크기 top 넘어가면 기존 사이즈 2배 */
		this.buffer=Arrays.copyOf(this.buffer, this.buffer.length*2);
	}	
}
