package org.snakeGame;

import org.snakeGame.exceptions.InvalidDirectionException;
import org.snakeGame.models.Board;
import org.snakeGame.models.Direction;

public class Main {
    public static void main(String[] args) throws InvalidDirectionException {
        System.out.println("Hello world!");
        Board snakeBoard = new Board(10, 10, Direction.RIGHT) ;
        snakeBoard.setSnakeHead(1,2);

        snakeBoard.update(Direction.RIGHT);
        snakeBoard.update(Direction.RIGHT);
        snakeBoard.update(Direction.DOWN);
        snakeBoard.update(Direction.DOWN);
        snakeBoard.update(Direction.RIGHT);
        snakeBoard.update(Direction.RIGHT);
        snakeBoard.update(Direction.UP);

    }
}