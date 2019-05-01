import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgarMain extends JPanel implements ActionListener {

    //========================================================//
    //                       НАСТРОЙКИ                        //
    // Скорость шага (ms)
    // Чем меньше, тем быстрее. От 1 до бесконечности
    private int speed = 1;

    // Размер поля (px)
    private final int SIZE = 600;

    //========================================================//




    private int reboot;
    private Image sphere;
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




    public AgarMain(){
        setBackground(Color.BLACK);
        System.out.println("loading images");
        loadImages();
        System.out.println("initializing");
        Init(1);
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
        score=0;
        }
        timer = new Timer(speed,this);
        timer.start();
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
        g.drawString("Спасено от бага " + "яблок: " + updated_apples,15,60);
        g.drawString("Иду к яблоку на " + appleX + "x" + appleY,10,35);
        g.drawString("Целевое Яблоко",appleX - 25,appleY + 50);
        inited=true;
    }


    public void findApple(){

        objtestx=sphx;
        objtesty=sphy;
        steps_to_1=0;
        steps_to_2=0;


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
        }while (objtestx!=apple1X && objtesty!=apple1Y);

        objtestx=sphx;
        objtesty=sphy;


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
        }while (objtestx!=apple2X && objtesty!=apple2Y);


        if(steps_to_1==steps_to_2) {
            System.out.println("Расстояние одинаковое. Спасаю 1 яблоко ");
            updated_apples++;

            if(updated_apples>30){
                System.out.println("Счётчик превысил 30. Пробую алгоритм 2 ");
                sphx=apple1X;
                sphy=apple1Y;
                updated_apples=0;
                reboot++;
                if(reboot==30){
                    System.out.println("Системная ошибка. Перезагрузка... ");
                    Init(9);
                    paintApple1();
                    paintApple2();
                }
            }

            if (sphx < apple1X){
                sphx+=20;
                if(sphy < apple1Y){
                    sphy+=20;
                }
                else{
                    sphy+=20;
                }
            }
            else{
                sphx-=20;
                if(sphy < apple1Y){
                    sphy+=20;
                }
                else{
                    sphy+=20;
                }
            }

        }
        else {


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
        steps_to_2=0;
        steps_to_1=0;



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


        if(sphx > appleX){
            sphx--;
            if(sphy > appleY){
                sphy--;
            }
            else{
                sphy++;
            }
        }
        else if(sphx < appleX){
            sphx++;
            if(sphy > appleY){
                sphy--;
            }
            else{
                sphy++;
            }
        }
        else if(sphy > appleY){
            sphy--;
        }
        else if(sphy < appleY){
            sphy++;
        }
        else{
            System.out.println("Съел яблоко на " + sphx + "x" + sphy);
            score++;
            if(target==1){
            Apple1Eaten=true;
            }
            else{
                Apple2Eaten=true;
            }
        }
    }



    public void paintApple1(){
        apple1X = from + (int) (Math.random() * SIZE); // Генерация x
        apple1Y = from + (int) (Math.random() * SIZE); // Генерация y

        System.out.println("System: яблоко 1 на " + apple1X + "x" + apple1Y);

    }
    public void paintApple2(){
        apple2X = from + (int) (Math.random() * SIZE); // Генерация x
        apple2Y = from + (int) (Math.random() * SIZE); // Генерация y

        System.out.println("System: яблоко 2 на " + apple2X + "x" + apple2Y);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (Apple1Eaten){
            paintApple1();
            Apple1Eaten = false;
        }

        if (Apple2Eaten){
            paintApple2();
            Apple2Eaten = false;
        }

        findApple();
        if(debug){
            System.out.println(sphx + "x" + sphy);
        }




        if (inited){
            repaint();
        }
    }
}
