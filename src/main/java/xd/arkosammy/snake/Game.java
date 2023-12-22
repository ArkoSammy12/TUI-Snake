package xd.arkosammy.snake;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class Game {

    private static Game game;
    private final Snake SNAKE_HEAD;
    private Element apple;
    private final Random random;
    private final GameScreen gameScreen;
    private int score;

    public static Game getInstance() throws IOException {
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public Game() throws IOException {

        this.gameScreen = GameScreen.getInstance();
        this.random = new Random();

        int initX = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getColumns() - 1);
        int initY = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getRows() - 1);

        this.SNAKE_HEAD = new Snake(new int[]{initX, initY}, Snake.Direction.RIGHT, null, null);


        int appleX = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getColumns() - 1);
        int appleY = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getRows() - 1);

        apple = new Element(appleX, appleY, Element.Type.APPLE);

    }

    public int getScore(){
        return this.score;
    }

    public GameScreen getScreen(){
        return this.gameScreen;
    }

    public void startLoop() throws InterruptedException, IOException {

        loop: while(true){

            this.gameScreen.clearElements();
            SnakeController.checkInput();
            this.gameScreen.submitElement(apple);
            SNAKE_HEAD.updatePositions();
            SNAKE_HEAD.updateDirections();
            List<Element> snakeNodes = SNAKE_HEAD.getSnakeNodes(new ArrayList<>());
            this.gameScreen.submitAllElements(snakeNodes);
            Snake.CollisionType collisionType = SNAKE_HEAD.checkCollision(this);
            switch(collisionType){
                case APPLE -> onAppleEaten();
                case WALL -> {
                    this.onGameLost();
                    break loop;}
                case NONE -> {}
            }
            this.gameScreen.refreshDisplay();
            this.gameScreen.display();
        }

    }

    public void onSnakeAttemptMove(Snake.Direction moveDirection){
        if(this.SNAKE_HEAD.getDirection().getOpposite() == moveDirection){
            return;
        }
        this.SNAKE_HEAD.setDirection(moveDirection);
    }

    private void onAppleEaten() {
        this.score++;
        this.SNAKE_HEAD.addTail();
        int appleX, appleY;
        do {
            appleX = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getColumns() - 1);
            appleY = this.random.nextInt(0, this.gameScreen.getTerminalScreen().getTerminalSize().getRows() - 1);
        } while (isSnakeOccupying(appleX, appleY));

        this.apple = new Element(appleX, appleY, Element.Type.APPLE);
    }

    private boolean isSnakeOccupying(int x, int y) {
        for (Element element : this.SNAKE_HEAD.getSnakeNodes(new ArrayList<>())) {
            if (element.x() == x && element.y() == y) {
                return true;
            }
        }
        return false;
    }


    private void onGameLost() throws IOException {
        this.gameScreen.getTerminalScreen().clear();
        TextGraphics lostText = this.gameScreen.getTerminalScreen().newTextGraphics();
        lostText.putString(0,0, "You lost");
        this.gameScreen.getTerminalScreen().refresh();
    }

}