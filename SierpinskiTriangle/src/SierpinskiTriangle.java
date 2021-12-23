import java.awt.*;
import java.util.Random;


public class SierpinskiTriangle extends SierpinskiTriangleAbstract {

    public static final int MIN_DEPTH = 0;
    public static final int MAX_DEPTH = 7;

    @Override
    protected void drawTriangleRec(int ax, int ay, int bx, int by, int cx, int cy, int depth, Color color) {

        int widthTri = bx - ax;
        int heightTri = ay - cy;

        if ((widthTri < 2) || (depth == 0)) {

            int xABC[] = {ax, bx, cx};
            int yABC[] = {ay, by, cy};
            g.setColor(color);
            g.fillPolygon(xABC, yABC, 3);

        } else {

            int fx = (ax + bx) / 2;
            int dx = (ax + fx) / 2;
            int dy = (ay + cy) / 2;
            int ex = (fx + bx) / 2;
            int ey = (ay + cy) / 2;
            g.setColor(color.WHITE);
            int xDEF[] = {dx, ex, fx};
            int yDEF[] = {dy, ey, ay};
            g.fillPolygon(xDEF, yDEF, 3);
            g.setColor(color.BLACK);

            drawTriangleRec(ax, ay, fx, ay, dx, dy, depth - 1, color);
            drawTriangleRec(fx, ay, bx, by, ex, ey, depth - 1, color);
            drawTriangleRec(dx, dy, ex, ey, cx, cy, depth - 1, color);

        }
    }

    @Override
    protected void handleInput(int keyCode) {

        if (keyCode == 38) {
            if (depth == MAX_DEPTH) {
                this.depth = 7;
            } else {
                this.depth += 1;
            }
        }

        if (keyCode == 40) {
            if (depth == MIN_DEPTH) {
                this.depth = 0;
            } else {
                this.depth -= 1;
            }
        }

        if (keyCode == 32) {
            toggleRandomColor();
        }

        paint(getGraphics());

    }

    @Override
    protected void drawTriangle() {
        super.drawTriangle();
        if(!useRandomColor) {                       // Toggle aus
            this.color = color.BLACK;
        } else {
            Random random = new Random();
            float red = random.nextFloat();
            float green = random.nextFloat();
            float blue = random.nextFloat();
            Color randomColor = new Color(red, green, blue);
            this.color = randomColor;
        }
    }

    @Override
    protected void toggleRandomColor() {

        if (!useRandomColor) {                           //Boolean Toogle funktioniert getestet mit Stdout! Allerdings ist manchmal doppelt Space druecken notwendig
            this.useRandomColor = true;
        } else {
            this.useRandomColor = false;
        }
    }

    public static void main(String[] args) {

        new SierpinskiTriangle();


    }
}
