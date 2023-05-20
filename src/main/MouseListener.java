package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    GamePanel gamePanel;

    public boolean mouseClick = false;

    public MouseListener(GamePanel gamePanel){

        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        mouseClick = true;
    }
}
