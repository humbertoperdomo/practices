package mx.naui.spring;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Drawing drawing = new Drawing();
        Shape triangle = new Triangle();
        drawing.setShape(triangle);
        drawing.draw();
        Shape circle = new Circle();
        drawing.setShape(circle);
        drawing.draw();
    }
}
