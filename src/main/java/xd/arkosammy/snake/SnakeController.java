package xd.arkosammy.snake;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SnakeController extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e){

        Snake.Direction moveDirection = switch(e.getKeyChar()){

            case 'w' -> Snake.Direction.UP;
            case 's' -> Snake.Direction.DOWN;
            case 'a' -> Snake.Direction.LEFT;
            case 'd' -> Snake.Direction.RIGHT;
            default -> null;

        };

        if(moveDirection != null){
            try {
                Game.getInstance().onSnakeAttemptMove(moveDirection);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public static void checkInput() throws IOException {

        GameScreen gameScreen = GameScreen.getInstance();

        Screen screen = gameScreen.getTerminalScreen();
        KeyStroke keyStroke = screen.pollInput();

        if(keyStroke != null){

            Snake.Direction moveDirection = switch(keyStroke.getCharacter()){

                case 'w' -> Snake.Direction.DOWN;
                case 's' -> Snake.Direction.UP;
                case 'a' -> Snake.Direction.LEFT;
                case 'd' -> Snake.Direction.RIGHT;
                default -> null;

            };

            if(moveDirection != null){
                try {
                    Game.getInstance().onSnakeAttemptMove(moveDirection);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }

}