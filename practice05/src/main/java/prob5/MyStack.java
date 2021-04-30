package prob5;

public class MyStack<T>{
	private T[] stack;
	private int top;

	@SuppressWarnings("unchecked")
	public MyStack(int size) {
		top = -1;
		stack = (T[])(new Object[size]);
	}

	public void push(T data) {
		if (stack.length - 1 == top) {
			resize();
		}
		top++;
		stack[top] = data;
	}

	public T pop() throws MyStackException{
		if (top == -1) {
			throw new MyStackException();
		} else {
			top--;
			T result = stack[(top + 1)];
			stack[(top + 1)] = null;
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		T[] resizeStack = (T[])(new Object[stack.length * 2]);
		for (int i = 0; i < stack.length; i++) {
			resizeStack[i] = stack[i];
		}
		stack = resizeStack;
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

//	@Override
//	public String toString() {
//		return "MyStack [data=" + Arrays.toString(stack) + ", top=" + top + "]";
//	}

}