import java.awt.Color;
import java.awt.Graphics;

public class Star {
    private Color color;
    private final int STAR_POINTS = 10;
    private int[] polygonX = new int[STAR_POINTS];
    private int[] polygonY = new int[STAR_POINTS];

    public Star() {
        color = Color.WHITE;
    }

    public void draw(Graphics g, int centerX, int centerY, double radius, Color starColor) {
        /*
         * To produce a polygon that looks like a star, a little trig...
         *
         * If you draw a regular 5-pointed star with center (0,0) on a graph,
         * the points will be at angles 18, 90, 162, 234, and 306 degrees.
         * The y-coord of the first point will be the radius of the star times
         * sin18.  The star can be thought of having 5 inner points at angles
         * 54, 126, 198, 270, and 342 degrees.  The y-coord of the point at 54
         * degrees is the same as the one at 18 degrees and is Rsin54 where R
         * is the radius of inner points.  Since Rsin54 = rsin18, and since
         * r, sin18, and sin54 are known, we have R = rsin18/sin54.  We can use
         * this to do some nice computations for coordinates of points around
         * the polygon; five on the outside and five on the inside.
         */
        double innerRadius = radius*Math.sin(Math.toRadians(18)/Math.sin(Math.toRadians(54)));

        // Note that (i-18)/36 will be 0, 2, 4, 6 8
        for (int i = 18; i < 360; i += 72) {
            polygonX[(i-18)/36] = centerX + (int) (radius * Math.cos(Math.toRadians(i)));
            polygonY[(i-18)/36] = centerY - (int) (radius * Math.sin(Math.toRadians(i)));
        }

        // Here (i-18)/36 will be 1, 3, 5, 7, 9
        for (int i = 54; i < 360; i += 72) {
            polygonX[(i-18)/36] = centerX + (int) (innerRadius * Math.cos(Math.toRadians(i)));
            polygonY[(i-18)/36] = centerY - (int) (innerRadius * Math.sin(Math.toRadians(i)));
        }

        Color c = g.getColor();
        g.setColor(starColor);
        g.fillPolygon(polygonX, polygonY, STAR_POINTS);
        g.setColor(c);
    }

}
