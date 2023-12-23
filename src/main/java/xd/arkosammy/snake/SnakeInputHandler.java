package xd.arkosammy.snake;

import java.io.IOException;

public class SnakeInputHandler implements Runnable{

    private final GameScreen gameScreen;
    public SnakeInputHandler(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    @Override
    public void run(){

        while(true){
            try {
                Game.checkInput(this.gameScreen);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
