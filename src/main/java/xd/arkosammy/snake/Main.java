package xd.arkosammy.snake;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Game game = Game.getInstance();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new SnakeInputHandler(game.getScreen()));
        game.startLoop();
        executorService.shutdown();

    }

}