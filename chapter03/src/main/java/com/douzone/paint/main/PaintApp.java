package com.douzone.paint.main;

import com.douzone.paint.i.Drawable;
import com.douzone.paint.point.*;
import com.douzone.paint.shape.*;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point(10, 20);
		point1.show();
		point1.show(false);
		
		Point point2 = new Point(100, 200);
		// point2.show();
		//drawPoint(point2);
		draw(point2);

		Point point3 = new ColorPoint(50, 100, "red");
		// point3.show();
		// point3.show(true);
		//drawPoint(point3);
		draw(point3);
		
		Rect rect = new Rect();
		// drawRect(rect);
		// drawShape(rect);
		draw(rect);
		
		Triangle triangle = new Triangle();
		// drawTriangle(triangle);
		// drawShape(triangle);
		draw(triangle);
		
		Circle circle = new Circle();
		// drawShape(circle);
		draw(circle);
		
		//error: class는 hierachy 상위와 하위만 
		//		instanceof 연산자를 사용할 수 있다.
		//System.out.println(circle instanceof Rect);
		
		Shape c= new Circle();
		System.out.println(c instanceof Circle);
		System.out.println(c instanceof Rect);
		System.out.println(c instanceof Shape);
		System.out.println(c instanceof Object);
		
		//인터페이스는 hierachy와 상관없이 
		//instanceof연산자를 사용할 수 있다.
		System.out.println(c instanceof Drawable);
		System.out.println(c instanceof Runnable);
	}

	public static void draw(Drawable drawable) {
		drawable.draw();
	}

//	public static void drawColorPoint(ColorPoint pt) {
//		pt.show();
//	}
//	
//	public static void drawPoint(Point pt) {
//		pt.show();
//	}
//
//	public static void drawShape(Shape shape) {
//		shape.draw();
//	}
//
//	public static void drawRect(Rect rect) {
//		rect.draw();
//	}
//
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
	
}