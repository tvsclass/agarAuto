import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.System.exit;


public class MenuMain extends JPanel{

    public boolean enter = false;
    public String inf = "Нажмите Enter для запуска";
    public boolean isRunning = false;
    public int ch = 1;
    public boolean inSettings = false;

    public MenuMain(){
        setBackground(Color.BLACK);
        addKeyListener(new MenuKeyListener());
        setFocusable(true);


    }


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        if(!inSettings) {
            if (ch != 1) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.red);
            }
            g.drawString(inf, 380, 70);
            if (ch != 2) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.red);
            }
            g.drawString("настройки", 260, 120);

            if (ch != 3) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.red);
            }
            g.drawString("выйти", 600, 120);
        }
        else{
            if(ch == 3){
                ch=1;
            }

            if (ch != 1) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.red);
            }
            g.drawString("Адский режим. Текущее значение: " + AgarMain.HELL_MODE, 380, 70);
            if (ch != 2) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.red);
            }
            g.drawString("назад", 260, 120);

        }

    }


    class MenuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_ENTER && !isRunning && !inSettings){
                System.out.println("pressed enter");
                if(ch == 1){
                enter = true;

                MainWindow mw = new MainWindow();
                //===================================================================//
                inf = "Запущено";
                //===================================================================//
                }
                if(ch == 2){
                    System.out.println("настройки");
                    inSettings=true;
                    repaint();
                    ch=1;
                }
                if(ch == 3){
                    exit(0);
                }

            }

            if(key == KeyEvent.VK_ENTER && !isRunning && inSettings){
                if(ch == 2){
                    inSettings=false;
                    repaint();
                }
            }

            if(key == KeyEvent.VK_P && !isRunning && inSettings){
                AgarMain.HELL_MODE+=1;
                repaint();
            }

            if(key == KeyEvent.VK_M && !isRunning && inSettings){
                AgarMain.HELL_MODE-=1;
                repaint();
            }

            if(key == KeyEvent.VK_RIGHT && !isRunning){
                if(ch != 3){
                    ch++;

                }
                else{
                    ch=1;
                }
                repaint();
            }
            if(key == KeyEvent.VK_LEFT && !isRunning){
                if(ch != 1){
                    ch--;

                }
                else{
                    ch=3;
                }
                repaint();
            }
            if(key == KeyEvent.VK_UP && !isRunning){
                if(ch == 1){
                    ch=2;

                }
                else{
                    ch=1;
                }
                repaint();
            }
            if(key == KeyEvent.VK_DOWN && !isRunning){
                if(ch == 1){
                    ch=2;

                }
                else{
                    ch=1;
                }
                repaint();
            }

        }
    }

}
