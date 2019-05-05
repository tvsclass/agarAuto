import javax.swing.*;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("AutoAgar v1: running");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,900);
        setLocation(400,400);
        add(new AgarMain());
        setVisible(true);

    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();

    }
}
