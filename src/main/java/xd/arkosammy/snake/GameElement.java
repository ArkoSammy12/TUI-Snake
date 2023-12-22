package xd.arkosammy.snake;

import com.googlecode.lanterna.TextColor;

public record GameElement(int x, int y, Type type) {

    enum Type {

        APPLE('O', TextColor.ANSI.RED_BRIGHT),
        SNAKE_HEAD('S', TextColor.ANSI.GREEN_BRIGHT),
        SNAKE_BODY('V', TextColor.ANSI.GREEN),
        WALL('#', TextColor.ANSI.WHITE);

        private final char graphic;
        private final TextColor color;

        Type(char graphic, TextColor color){
            this.graphic = graphic;
            this.color = color;
        }

        public char getGraphic(){
            return this.graphic;
        }
        public TextColor getColor(){
            return this.color;
        }

    }

}