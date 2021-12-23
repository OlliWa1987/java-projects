public abstract class GameItem {

    Point position;

    public GameItem(int x, int y) {

        this.position = new Point(x, y);

    }

    public Point getPosition() {
        return position;

    }

    public abstract void paint(AudGraphics g);

    }
