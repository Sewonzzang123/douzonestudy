package prob5;

public class MyStack {
	private String[] stack;
	private int top;

	public MyStack(int size) {
		top = -1;
		stack = new String[size];
	}

	public void push(String data) {
		if (stack.length - 1 == top) {
			resize();
		}
		top++;
		this.stack[top] = data;
	}

	public String pop() throws MyStackException{
		if (top == -1) {
			throw new MyStackException();
		} else {
			top--;
			return stack[(top + 1)];
		}
	}

	private void resize() {
		String[] resizeStack = new String[stack.length * 2];
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