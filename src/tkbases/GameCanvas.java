package tkbases;

import tkbases.inputs.InputManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {
    private BufferedImage bufferedImage;
    private Graphics2D graphics2D;

    private int screenWidth;
    private int screenHeight;

    public GameCanvas(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setupBuffered();
        setupMouseEvents();
    }

    private void setupMouseEvents() {
        this.addMouseListener(InputManager.instance);
    }

    private void setupBuffered() {
        this.bufferedImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        this.graphics2D = (Graphics2D) this.bufferedImage.getGraphics();
    }


    public void render() {
        this.graphics2D.setColor(Color.BLACK);
        this.graphics2D.fillRect(0,0, this.getWidth(), this.getHeight());
        GameObject.renderAll(this.graphics2D, Vector2D.ZERO);
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.bufferedImage, 0, 0 ,null);
    }

    public void run() {
        GameObject.runAll();
    }
}
