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
        int temp = 25;
        //vertical grid line
        while(temp<frameWidth){
            temp+=45;
            g.drawLine(temp  ,0,temp ,frameHeight);
        }

        temp = 25;
        //horizontal grid line
        while(temp < frameHeight){
            temp += 45;
            g.drawLine(0,temp ,frameWidth,temp );
        }
    }

    public static void main(String[] args) {
        DrawGridOnJPanel test = new DrawGridOnJPanel();
    }
}
