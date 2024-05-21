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
        //-28
        int temp = -30;
        //vertical grid line
        while(temp<frameWidth){
            temp+=60;
            g.drawLine(temp  ,0,temp ,frameHeight);
        }
        //-27
        temp = -29;
        //horizontal grid line
        while(temp < frameHeight){
            temp += 58;
            g.drawLine(0,temp ,frameWidth,temp );
        }
        //x240 //y233
        //center
        g.fillOval(446,431, 8, 8);
        //bottomleft
        g.fillOval(206, 663, 8, 8);
        //topleft
        g.fillOval(206, 199, 8, 8);
        //topright
        g.fillOval(686, 199, 8, 8);
        //bottomright
        g.fillOval(686, 663, 8, 8);
    }

    public static void main(String[] args) {

    }
}
