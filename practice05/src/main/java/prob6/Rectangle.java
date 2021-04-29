package prob6;

public class Rectangle extends Shape implements Resizable{
	private double width;
	private double height;
	
	public Rectangle(double w, double h) {
		width = w;
		height = h;
	}
	
	@Override
	public double getArea() {		
		return width*height;
	}

	@Override
	public double getPerimeter() {
		return width*2+height*2;
	}

	public void resize(double s) {
		width = width*s;
		height = height*s;
	}


}
