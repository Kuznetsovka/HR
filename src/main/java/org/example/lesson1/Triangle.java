package org.example.lesson1;

public class Triangle extends Shape{
    public Triangle(int width,int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double Area() {
        return 1/2*width*height;
    }
}
