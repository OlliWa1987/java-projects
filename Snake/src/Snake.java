import java.util.Arrays;

public class Snake {

    public static Point[] points;
    private AudColor SnakeColor = new AudColor(0,255,0);

    public enum Direction {

        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    private Direction nextDirection = Direction.RIGHT;
    private Direction lastDirection;

    public Direction getNextDirection () {

        return nextDirection;
    }

    public void setNextDirection(Direction x) {

        if (x == Direction.RIGHT && Direction.LEFT == lastDirection) {
            this.nextDirection = lastDirection;
        } else if ((x == Direction.LEFT && Direction.RIGHT == lastDirection)){
            this.nextDirection = lastDirection;
        } else if ((x == Direction.UP && Direction.DOWN == lastDirection)){
            this.nextDirection = lastDirection;
        } else if ((x == Direction.DOWN && Direction.UP == lastDirection)){
            this.nextDirection = lastDirection;
        } else {
            this.nextDirection = x;
        }
    }

    public void step() {

        System.arraycopy(points, 0, points, 1, points.length - 1);

        switch (getNextDirection()) {
            case UP:
                points[0] = new Point(points[1].getX(), (points[1].getY() - SnakeGame.SQUARE_SIZE));
                this.lastDirection = Snake.Direction.UP;
                break;
            case DOWN:
                points[0] = new Point(points[1].getX(), (points[1].getY() + SnakeGame.SQUARE_SIZE));
                this.lastDirection = Snake.Direction.DOWN;
                break;
            case RIGHT:
                points[0] = new Point((points[1].getX() + SnakeGame.SQUARE_SIZE), points[1].getY());
                this.lastDirection = Snake.Direction.RIGHT;
                break;
            case LEFT:
                points[0] = new Point((points[1].getX() - SnakeGame.SQUARE_SIZE), points[1].getY());
                this.lastDirection = Snake.Direction.LEFT;
                break;

            default:
                break;

        }
    }

    public Snake ( int x, int y, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        points = new Point[length];
        Point PointObject = new Point(x, y);
        points[0] = PointObject;

    }

    public Snake (int x, int y) {
        this(x,y,5);

    }

    public void paint(AudGraphics g) {
        g.setColor(SnakeColor);

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null){
                continue;
            }else {
                g.fillRect(points[i].getX(), points[i].getY(), SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);

            }
        }
    }

    public boolean collidesWith(int x, int y) {

        boolean collides = false;

        for (int i = 0; i < points.length; i++) {

            if (points[i] == null) {
                continue;
            } else if (x == points[0].getX() && y == points[0].getY()) {
                collides = true;
            } else {
                collides = false;
            }
        }

        return collides;
    }

    public boolean collidesWith(GameItem item) {

        int x = item.getPosition().getX();
        int y = item.getPosition().getY();
        boolean collision = collidesWith(x, y);

        return collision;

    }

    public boolean collidesWithSelf() {

        boolean collidationSnake = false;

        for (int i = 1; i < points.length; i++) {

            if (points[i] == null) {
                continue;
            } else if (points[0].getX() == points[i].getX()) {
                if (points[0].getY() == points[i].getY()) {
                return true;
                }
            } else {
                collidationSnake = false;
            }
        }

        return collidationSnake;
    }


    public void grow(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount ist too small!");

        } else {
            points = Arrays.copyOf(points, points.length + amount);
            points[0] = new Point(points[1].getX(), points[1].getY());
        }
    }
}