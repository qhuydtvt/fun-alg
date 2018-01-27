import tkbases.GameWindow;

/**
 * Created by huynq on 1/28/18.
 */
public class Program {
    public static void main(String[] args) {
        BotCanvas botCanvas = new BotCanvas(800, 800);
        GameWindow window = new GameWindow(botCanvas);
        window.gameLoop();
    }
}
