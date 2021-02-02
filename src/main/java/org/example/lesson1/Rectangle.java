package org.example.lesson1;

public class Rectangle extends Shape {
    public Rectangle(int width,int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width*height;
    }
}
