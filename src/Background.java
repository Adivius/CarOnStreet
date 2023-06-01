import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Background extends EventScreen {

    Car car = new Car(this, 100, 500, 1);
    public boolean running = true;
    Boolean driving = false;

    public Background(int width, int height, String title) {
        super(width, height, title);

        Button b1 = new Button("Start");
        b1.setBounds(100, 100, 100, 40);
        b1.setBackground(Color.PINK);
        b1.addActionListener(e -> this.driving = true);

        Button b2 = new Button("Stop");
        b2.setBounds(220, 100, 100, 40);
        b2.setBackground(Color.PINK);
        b2.addActionListener(e -> this.driving = false);

        Button b3 = new Button("Increase");
        b3.setBounds(340, 100, 100, 40);
        b3.setBackground(Color.PINK);
        b3.addActionListener(e -> this.car.speed++);

        Button b4 = new Button("Decrease");
        b4.setBounds(460, 100, 100, 40);
        b4.setBackground(Color.PINK);
        b4.addActionListener(e -> this.car.speed--);

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);

        new RunThread().start();
        handleThread();

    }

    @Override
    public void editMouseClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            this.setVisible(false);
        }
    }

    @Override
    public void editMouseDragged(MouseEvent e) {
        this.car.x = e.getX();
        this.car.y = e.getY();
        this.car.update();
    }

    public class RunThread extends Thread {
        @Override
        public void run() {
            while (running) {
                Clock.delay(10);
                if (!driving) {
                    continue;
                }
                car.update();
            }
        }
    }

    void handleThread() {
        try {
            Runtime.getRuntime().exec("java -jar ICat.jar localhost 9005 cmd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
