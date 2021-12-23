public class Brick extends GameItem {

    AudColor colorWall = new AudColor(90,90,90);

    public Brick(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(colorWall);
        g.fillRect(getPosition().getX(), getPosition().getY(), SnakeGame.SQUARE_SIZE,SnakeGame.SQUARE_SIZE);
    }
}
