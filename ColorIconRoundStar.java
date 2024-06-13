import java.awt.*;
import javax.swing.Icon;
public class ColorIconRoundStar implements Icon{

    private int size;
    private Paint color;

    private Color starColor;

    public ColorIconRoundStar(int size, Paint color, Color test){
        this.size = size;
        this.color = color;
        starColor = test;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        Paint  op = g2d.getPaint();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(color);
        x -= 20;
        y -= 20;

        g2d.fillOval(x,y, size, size);
        g2d.setPaint(op);
        Star star1 = new Star();
        star1.draw(g, x + 20, y + 20, 18.0, starColor);

    }


    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}

