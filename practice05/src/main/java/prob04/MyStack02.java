package prob04;

import java.util.Arrays;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		/* 구현하기 */
		this.top = -1;
		this.buffer=new Object[capacity];
	}

	public void push(Object s) {
		/* 구현하기 */
		this.top++;
		if(top==buffer.length) {
			resize();	
		}
		this.buffer[top]=s;
	}

	public Object pop() throws MyStackException {
		/* 구현하기 */
		if(top==-1) {
			throw new MyStackException();
		}
		Object target = buffer[top];
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
