//Oliver Wagener    | 23045887
//Leon Winkelmann   | 22967380

import java.util.Random;

public class SnakeGame extends AudGameWindow {

    private int score = 0;
    public static final int SQUARE_SIZE = 16;
    public static final int STEP_TIME = 100; //ms
    public static long lastSnakeUpdate;
    public static final int GROW_AMOUNT = 1;

    //-----------------------------------------------------------------
    int minX = SQUARE_SIZE;
    int minY = SQUARE_SIZE;
    int maxX = ((getGameAreaWidth() - 2 * SQUARE_SIZE) / SQUARE_SIZE);
    int maxY = ((getGameAreaHeight() - 2 * SQUARE_SIZE) / SQUARE_SIZE);

    Random r = new Random();

    int randomX = (r.nextInt(maxX - minX + 1) + minX);
    int randomY = (r.nextInt(maxY - minY + 1) + minY);

    Apple apple = new Apple(randomX * SQUARE_SIZE, randomY * SQUARE_SIZE);
    //-----------------------------------------------------------------

    AudColor background = new AudColor(43,43,43);

    Snake snakeObject;

    int width   = getGameAreaWidth() / SQUARE_SIZE;
    int height  = getGameAreaHeight() / SQUARE_SIZE;

    private Brick[] wall;


    public SnakeGame() {

        setTitle("AuD-Snake - Score: " + score);
        snakeObject = new Snake((width / 2) * SQUARE_SIZE, (height / 2) * SQUARE_SIZE);
        //snakeObject = new Snake(24 * SQUARE_SIZE,17 * SQUARE_SIZE);
        lastSnakeUpdate  = System.currentTimeMillis();

        wall = new Brick[2 * (width + height)];
        for (int i = 0; i < height; i++) {

            int y = i * SQUARE_SIZE;
            Brick brickBuilderWallLeft = new Brick(0, y);
            Brick brickBuilderWallRight = new Brick(width * SQUARE_SIZE - SQUARE_SIZE, y);
            wall[i] = brickBuilderWallLeft;
            wall[i + height] = brickBuilderWallRight;

        }

        for (int i = 0; i < width; i++) {

            int x = i * SQUARE_SIZE;
            Brick brickBuilderWallUp = new Brick(x, 0);
            Brick brickBuilderWallDown = new Brick(x, height * SQUARE_SIZE - SQUARE_SIZE);
            wall[i + 2 * height] = brickBuilderWallUp;
            wall[i + width + 2 * height] = brickBuilderWallDown;

        }

        //createNewApple();

    }

    @Override
    public void updateGame(long time) {
        long t = (time - lastSnakeUpdate) / STEP_TIME;

        for (int i = 0; i < t; i++) {
            snakeObject.step();
            lastSnakeUpdate += STEP_TIME;
            checkCollisions();

            /*if (snakeObject.collidesWith(apfel)){
                createNewApple();

            }*/
        }
    }

    @Override
    public void paintGame(AudGraphics g) {
        g.setColor(background);
        g.fillRect(0,0,getGameAreaWidth(),getGameAreaHeight());
        snakeObject.paint(g);
        apple.paint(g);

        for (int i = 0; i < wall.length; i++) {
            wall[i].paint(g);
        }
    }


    @Override
    public void handleInput(int keyCode) {

        switch (keyCode){
            case KeyEvent.VK_DOWN:
                snakeObject.setNextDirection(Snake.Direction.DOWN);
                break;

            case KeyEvent.VK_LEFT:
                snakeObject.setNextDirection(Snake.Direction.LEFT);
                break;

            case KeyEvent.VK_RIGHT:
                snakeObject.setNextDirection(Snake.Direction.RIGHT);
                break;

            case KeyEvent.VK_UP:
                snakeObject.setNextDirection(Snake.Direction.UP);
                break;
        }

    }

    private void createNewApple(){

        Apple randomapple;



        while (snakeObject.collidesWith(apple)){

            int minX = SQUARE_SIZE;
            int minY = SQUARE_SIZE;
            int maxX = ((getGameAreaWidth() - 2 * SQUARE_SIZE) / SQUARE_SIZE);
            int maxY = ((getGameAreaHeight() - 2 * SQUARE_SIZE) / SQUARE_SIZE);

            Random r = new Random();

            int randomX = (r.nextInt(maxX - minX + 1) + minX);
            int randomY = (r.nextInt(maxY - minY + 1) + minY);

            randomapple = new Apple(randomX * SQUARE_SIZE, randomY * SQUARE_SIZE);

            if (snakeObject.collidesWith(randomapple)){
                continue;
            } else {
                this.apple = randomapple;
                setTitle("AuD-Snake - Score: " + score);
            }
        }
        
    }

    private void checkCollisions() {
        //System.out.println("S[X_" + Snake.points[0].getY() + "_" + Snake.points[0].getX() + "_Y]");
        //System.out.println("W[X_" + wall[0].getPosition().getX() + "_" + wall[0].getPosition().getY() + "_Y]");

        if (snakeObject.collidesWithSelf()){
            stop();
            showDialog("You died! Score: " + score);
        }

        //Collision Wall
        for (int i = 0; i < wall.length; i++) {
            if (wall[i].getPosition().getX() == Snake.points[0].getX()){
                if (wall[i].getPosition().getY() == Snake.points[0].getY()){
                    stop();
                    showDialog("You died! Score: " + score);
                }
            }
        }

        if (snakeObject.collidesWith(apple)){
            createNewApple();
            snakeObject.grow(GROW_AMOUNT);
            score += apple.getValue();
            setTitle("AuD-Snake - Score: " + score);

        }
    }


    public static void main(String[] args) {

        SnakeGame snake = new SnakeGame();
        snake.start();

    }
}

