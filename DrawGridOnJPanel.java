import java.awt.Graphics;
import javax.swing.*;

public class DrawGridOnJPanel extends JPanel{
    public DrawGridOnJPanel(){
        setSize(900,900);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        int frameWidth = getSize().width;
        int frameHeight = getSize().height;
        int temp = 0;
        while(temp<frameWidth){
            temp+=60;
            g.drawLine(temp,0,temp,frameHeight);
        }

        temp = 0;

        while(temp < frameHeight){
            temp += 60;
            g.drawLine(0,temp,frameWidth,temp);
        }
    }

    public static void main(String[] args) {
        DrawGridOnJPanel test = new DrawGridOnJPanel();
    }
}
