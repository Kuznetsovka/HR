package org.example.lesson1;

public class Circle extends Shape {
    public Circle(int width) {
        this.width = width;
    }

    @Override
    public double Area() {
        return Math.PI * Math.pow(width,2)/4;
    }
}
