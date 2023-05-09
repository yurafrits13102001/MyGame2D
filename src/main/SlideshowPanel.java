package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SlideshowPanel extends JPanel implements Runnable {

    public GamePanel gamePanel;


    private ArrayList<Image> images; // масив зображень
    private int currentIndex = 0; // індекс поточного зображення
    private boolean playing = true; // флаг, що вказує на відтворення слайд-шоу
    private int delay = 2000; // затримка між зміною зображень (у мілісекундах)

    public SlideshowPanel(Image[] images, int i) {
        this.images = new ArrayList<>();
        for (Image image : images) {
            this.images.add(image);
        }
    }

    @Override
    public void run() {

        while (playing) {
            repaint(); // оновлення зображення на панелі
            try {
                Thread.sleep(delay); // затримка
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            currentIndex++; // перехід до наступного зображення
            if (currentIndex >= images.size()) {
                currentIndex = 0; // перехід до першого зображення, якщо поточне - останнє
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image currentImage = images.get(currentIndex);
        g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void start() {
        playing = true;
        new Thread(this).start(); // створення нового потоку для відтворення слайд-шоу
    }

    public void stop() {
        playing = false;
    }
}
