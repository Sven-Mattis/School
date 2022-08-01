package Controller;

import View.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        View mainFrame;
        mainFrame = new View("Test",0,0, (int) (Math.floor(Toolkit.getDefaultToolkit().getScreenSize().width*.7/20)*20), (int) (Math.floor(Toolkit.getDefaultToolkit().getScreenSize().height*.7/20)*20));

        new Graph(mainFrame);
    }
}
