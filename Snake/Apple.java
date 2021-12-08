public class Apple extends GameItem {

    AudColor colorApple = new AudColor(255,0,0);
    private static int nextValue = 0;
    private final int VALUE;

    public Apple(int x, int y) {
        super(x, y);
        VALUE = nextValue;
        nextValue++;

    }

    public int getValue() {

        return VALUE;
    }

    @Override
    public void paint(AudGraphics g) {

        g.setColor(colorApple);
        g.fillOval(position.getX(), position.getY(), SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }
}
