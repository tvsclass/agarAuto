import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.System.exit;

public class AgarMain extends JPanel implements ActionListener {

    //========================================================//
    //                       НАСТРОЙКИ                        // **перенесены в меню
    // Скорость шага (ms)
    // Чем меньше, тем быстрее. От 1 до бесконечности
    private int speed = 1;

    // Размер поля (px)
    private final int SIZE = 600;

    // АДСКИЙ РЕЖИМ. НЕ СОВЕТУЮ. НЕ НАДО. Я ПРЕДУПРЕДИЛ.
    // позваляет запустить несколько дополнительных таймеров. От 1 до бесконечности.
    public static int HELL_MODE = 0;

    //========================================================//




    private int reboot;
    private Image sphere;
    private boolean hellmode;
    private Image apple;
    private int appleX;
    private int appleY;
    private Timer timer;
    private int sphx;
    private int sphy;
    private int from = 0;
    private boolean Apple1Eaten = true;
    private boolean Apple2Eaten = true;
    private boolean inited = false;
    private boolean debug = false;
    private int score;
    private int apple1X;
    private int apple1Y;
    private int apple2X;
    private int apple2Y;
    private int target;
    private int objtestx;
    private int objtesty;
    private int steps_to_1;
    private int steps_to_2;
    private int updated_apples;
    private boolean enter;
    private int moveApple1 = 0;
    private int moveApple2 = 0;
    private int direction1;
    private int direction2;
    private int apple1speed;
    private int apple2speed;





    public AgarMain(){
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new FieldKeyListener());
        System.out.println("loading images");
        loadImages();
        System.out.println("initializing");
        Init(1);
    }

    public void setHELL_MODE(int value){
        HELL_MODE=value;
        return;
    }
    public int getHELL_MODE(){
        return HELL_MODE;
    }



    public void loadImages(){
        System.out.println("loading images...");
        ImageIcon sph = new ImageIcon("sphere.png");
        ImageIcon apl = new ImageIcon("apple.png");
        apple = apl.getImage();
        sphere = sph.getImage();
    }
    public void Init(int a){
        System.out.println("initializing...");
        sphy=100;
        sphx=100;
        updated_apples=0;
        if(a!=9){
            timer = new Timer(speed,this);
            score=0;
            timer.start();

            if(HELL_MODE>0){
                int i = HELL_MODE;
                while (i!=0){
                    hellmode=true;
                    timer = new Timer(speed, this);
                    timer.start();
                    i-=1;
                }
            }
            else{
                hellmode=false;
            }
        }


        reboot=0;


    }




    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(sphere,sphx,sphy,this);
        g.drawImage(apple,apple1X,apple1Y,this);
        g.drawImage(apple,apple2X,apple2Y,this);
        g.setColor(Color.white);
        g.drawString("Сьедено " + "яблок: " + score,10,15);
        g.drawString("Ошибок за такт: " + updated_apples,15,60);
        g.drawString("Иду к яблоку на " + appleX + "x" + appleY,10,35);
        g.drawString("Целевое Яблоко",appleX - 25,appleY + 50);
        if(hellmode){
            g.setColor(Color.red);
            g.drawString("АДСКИЙ РЕЖИМ",300,15);
        }
        inited=true;
    }





    public void findApple() {

        if (true) {

            objtestx = sphx;
            objtesty = sphy;
            steps_to_1 = 0;
            steps_to_2 = 0;


            do {

                if (objtestx > apple1X) {
                    steps_to_1++;
                    objtestx--;
                    if (objtesty > apple1Y) {
                        objtesty--;
                    } else {
                        objtesty++;
                    }
                } else if (objtestx < apple1X) {
                    steps_to_1++;
                    objtestx++;
                    if (objtesty > apple1Y) {
                        objtesty--;
                    } else {
                        objtesty++;
                    }
                } else if (objtesty > apple1Y) {
                    steps_to_1++;
                    objtesty--;
                } else if (objtesty < apple1Y) {
                    steps_to_1++;
                    objtesty++;
                }
                //System.out.println("loop");
            } while (objtestx != apple1X && objtesty != apple1Y);

            objtestx = sphx;
            objtesty = sphy;


            do {
                if (objtestx > apple2X) {
                    steps_to_2++;
                    objtestx--;
                    if (objtesty > apple2Y) {
                        objtesty--;
                    } else {
                        objtesty++;
                    }
                } else if (objtestx < apple2X) {
                    steps_to_2++;
                    objtestx++;
                    if (objtesty > apple2Y) {
                        objtesty--;
                    } else {
                        objtesty++;
                    }
                } else if (objtesty > apple2Y) {
                    steps_to_2++;
                    objtesty--;
                } else if (objtesty < apple2Y) {
                    steps_to_2++;
                    objtesty++;
                }
                //System.out.println("loop2 ");
            } while (objtestx != apple2X && objtesty != apple2Y);


            if (steps_to_1 == steps_to_2) {
                System.out.println("Расстояние одинаковое. Пробую алгоритм 1 ");
                updated_apples++;

                appleX = apple1X;
                appleY = apple1Y;
                target = 1;


                if (updated_apples > 5) {
                    System.out.println("Счётчик превысил 5. Пробую алгоритм 2 ");
                    sphx = apple1X;
                    sphy = apple1Y;
                    updated_apples = 0;
                    reboot++;
                    if (reboot == 3) {
                        System.out.println("Системная ошибка. Перезагрузка... ");
                        Init(9);
                        paintApple1();
                        paintApple2();
                    }
                }


            } else {


                if (steps_to_1 > steps_to_2) {
                    appleX = apple2X;
                    appleY = apple2Y;
                    target = 2;
                } else {
                    appleX = apple1X;
                    appleY = apple1Y;
                    target = 1;

                }
            }


            //System.out.println("Шагов до 1 " + steps_to_1);
            //System.out.println("Шагов до 2 " + steps_to_2);
            steps_to_2 = 0;
            steps_to_1 = 0;


            //       if((apple1X + apple1Y) - (sphx + sphy) < (apple2X + apple2Y) - (sphx + sphy)){
            //         appleX=apple1X;
            //       appleY=apple1Y;
            //     target=1;
            //}
            //else {
            //  appleX=apple2X;
            //appleY=apple2Y;
            //target=2;
            //}


            if (sphx > appleX) {
                sphx--;
                if (sphy > appleY) {
                    sphy--;
                } else {
                    sphy++;
                }
            } else if (sphx < appleX) {
                sphx++;
                if (sphy > appleY) {
                    sphy--;
                } else {
                    sphy++;
                }
            } else if (sphy > appleY) {
                sphy--;
            } else if (sphy < appleY) {
                sphy++;
            } else {
                System.out.println("Съел яблоко на " + sphx + "x" + sphy);
                score++;
                if (target == 1) {
                    Apple1Eaten = true;
                } else {
                    Apple2Eaten = true;
                }
            }
        }
    }



    public void paintApple1(){
        apple1X = from + (int) (Math.random() * SIZE); // Генерация x
        apple1Y = from + (int) (Math.random() * SIZE); // Генерация y
        direction1 = 0 + (int) (Math.random() * 10); // Генерация направления
        apple1speed = 5 + (int) (Math.random() * 15); // Генерация скорости

        System.out.println("System: яблоко 1 на " + apple1X + "x" + apple1Y);
        updated_apples=0;
        reboot=0;

    }
    public void paintApple2(){
        apple2X = from + (int) (Math.random() * SIZE); // Генерация x
        apple2Y = from + (int) (Math.random() * SIZE); // Генерация y
        direction2 = 0 + (int) (Math.random() * 10); // Генерация направления
        apple2speed = 5 + (int) (Math.random() * 15); // Генерация скорости

        System.out.println("System: яблоко 2 на " + apple2X + "x" + apple2Y);
        updated_apples=0;
        reboot=0;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {


            if (Apple1Eaten) {
                paintApple1();
                Apple1Eaten = false;
            }

            if (Apple2Eaten) {
                paintApple2();
                Apple2Eaten = false;
            }

            findApple();
            if (debug) {
                System.out.println(sphx + "x" + sphy);
            }


            if (inited) {
                repaint();
            }

            if (moveApple1 < apple1speed){
                moveApple1++;
            }else{

                if (direction1 == 0){
                    apple1X++;
                }
                else if (direction1 == 1){
                    apple1X++;
                    apple1Y++;
                }
                else if (direction1 == 2){
                    apple1Y++;
                }
                else if (direction1 == 3){
                    apple1X++;
                    apple1Y++;
                }
                else if (direction1 == 4){
                    apple1Y++;
                }
                else if (direction1 == 5){
                    apple1X--;
                    apple1Y--;
                }
                else if (direction1 == 6){
                    apple1X--;
                }
                else if (direction1 == 7){
                    apple1X--;
                    apple1Y++;
                }
                else if (direction1 == 9){
                    apple1X++;
                    apple1Y--;
                }

                if (apple1X > SIZE){
                    direction1=7;
                }
                if (apple1X < 0){
                    direction1=9;
                }
                if (apple1Y < 0){
                    direction1=4;
                }
                if (apple1Y > SIZE){
                    direction1=5;
                }
                moveApple1=0;


            }

        if (moveApple2 < apple2speed){
            moveApple2++;
        }else{

            if (direction2 == 0){
                apple2X++;
            }
            else if (direction2 == 1){
                apple2X++;
                apple2Y++;
            }
            else if (direction2 == 2){
                apple2Y++;
            }
            else if (direction2 == 3){
                apple2X++;
                apple2Y++;
            }
            else if (direction2 == 4){
                apple2Y++;
            }
            else if (direction2 == 5){
                apple2X--;
                apple2Y--;
            }
            else if (direction2 == 6){
                apple2X--;
            }
            else if (direction2 == 7){
                apple2X--;
                apple2Y++;
            }
            else if (direction2 == 9){
                apple2X++;
                apple2Y--;
            }

            if (apple2X > SIZE){
                direction2=7;
            }
            if (apple2X < 0){
                direction2=9;
            }
            if (apple2Y < 0){
                direction2=4;
            }
            if (apple2Y > SIZE){
                direction2=5;
            }
            moveApple2=0;


        }

    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_ESCAPE){
                enter = true;
                System.out.println("pressed escape");
                Init(9);
                score=0;
            }
            if(key == KeyEvent.VK_P){
                hellmode = true;
                System.out.println("pressed escape");
                int sc = score;
                Init(1);
                score=sc;
            }

            if(key == KeyEvent.VK_E){
                exit(0);
            }

        }
    }
}
