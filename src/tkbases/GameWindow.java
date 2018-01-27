package tkbases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {
    private GameCanvas gameCanvas;
    private long lastTime = 0;

    private int screenWidth = 800;
    private int screenHeight = 800;

    public GameWindow() {
        this.setupGameCanvas();
        this.setupWindow();
        this.hideCursor();
    }

    private void setupWindow() {
        this.setSize(screenWidth, screenHeight);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    private void setupGameCanvas() {
        this.gameCanvas = new GameCanvas(screenWidth, screenHeight);
        this.setContentPane(this.gameCanvas);
        this.gameCanvas.setVisible(true);
    }

    private void hideCursor() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        this.getContentPane().setCursor(cursor);
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 27_000_000_000L) {
                this.gameCanvas.run();
                this.gameCanvas.render();
                this.lastTime = currentTime;
            }

        }
    }
}
