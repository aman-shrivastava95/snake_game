package org.snakeGame.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cell {
    @NonNull
    private CellState state;
    @NonNull
    private int pos_x ;
    @NonNull
    private int pos_y ;
}
