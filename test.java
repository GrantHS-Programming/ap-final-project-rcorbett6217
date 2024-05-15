import javax.swing.*;
import java.awt.*;
public class test {
    JFrame frame = new JFrame("test");
    DrawGridOnJPanel testPan = new DrawGridOnJPanel();

    public static void main(String[] args) {
        new test();
    }
    public test (){
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(testPan);
    }
}
