package xd.arkosammy.snake;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        Game game = Game.getInstance();
        game.startLoop();

    }

}