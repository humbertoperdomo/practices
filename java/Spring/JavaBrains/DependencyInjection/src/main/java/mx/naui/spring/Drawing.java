package mx.naui.spring;

public class Drawing {
    private Shape shape;

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }
}
