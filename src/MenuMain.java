import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MenuMain extends JPanel{

    public boolean enter = false;
    public String inf = "Нажмите Enter для запуска";

    public MenuMain(){
        setBackground(Color.BLACK);
        addKeyListener(new MenuKeyListener());
        setFocusable(true);

    }


    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawString(inf,400,100);

    }


    class MenuKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_ENTER){
                enter = true;
                System.out.println("pressed enter");
                MainWindow mw = new MainWindow();
                inf = "Запущено";




            }

        }
    }

}
