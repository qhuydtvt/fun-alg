package tkbases;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    private GameCanvas gameCanvas;
    private long lastTime = 0;

    private static final int screenWidth = 800;
    private static final int screenHeight = 800;

    public GameWindow() {
        this(new GameCanvas(screenWidth, screenHeight));
    }

    public GameWindow(GameCanvas gameCanvas) {
        this.setupGameCanvas(gameCanvas);
        this.setupWindow();
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

    private void setupGameCanvas(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        this.setContentPane(this.gameCanvas);
        this.gameCanvas.setVisible(true);
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 27_000_000L) {
                this.gameCanvas.run();
                this.gameCanvas.render();
                this.lastTime = currentTime;
            }
        }
    }
}
