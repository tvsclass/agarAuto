import javax.swing.*;

public class MenuWindow extends JFrame {
    public MenuWindow() {
        setTitle("AutoAgar v1: menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900,200);
        setLocation(400,400);
        add(new MenuMain());
        setVisible(true);
    }
    public static void main(String[] args) {
        MenuWindow mew = new MenuWindow();

    }
}
