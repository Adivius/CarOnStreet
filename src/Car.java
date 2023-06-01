import java.awt.*;

public class Car implements Vehicle {

    Background background;
    Pen pen;

    int speed;
    int x;
    int y;

    public Car(Background background, int x, int y, int speed) {
        this.background = background;
        this.pen = new Pen(background);
        this.x = x;
        this.y = y;
        this.speed = speed;
        pen.moveTo(x, y);
        update();
    }

    @Override
    public void draw() {
        pen.rotateBy(90);
        pen.moveBy(30);
        pen.drawRectangle(50, 20);
        pen.moveBy(-30);
        pen.rotateBy(-90);


        pen.drawCircle(10);
        pen.moveBy(50);
        pen.drawCircle(10);
        pen.moveBy(-50);
    }

    @Override
    public void update() {
        pen.setColorToBackground();
        draw();
        x += speed;
        pen.moveTo(x, y);
        pen.setColor(Color.BLACK);
        draw();
        background.redraw();
    }
}