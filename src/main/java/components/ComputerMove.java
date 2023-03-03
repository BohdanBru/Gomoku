/*
 * Copyright 2023 Bohdan Brukhovets
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package components;

import model.Cell;
import model.Sign;

import java.util.Random;

public class ComputerMove implements Move {
    int count = 0;
    Cell[] lastMove = new Cell[100];
    boolean first = true;
    boolean second = true;
    boolean third = true;
    boolean fourth = true;


    public ComputerMove() {

    }

    public final void step(GameTable gameTable, Sign sign, GameWindow gameWindow) {
        int x;
        int y;


        while (true) {
            if (tryToInterrupt(gameTable, sign, gameTable)) {
                return;
            } else if (stepByRandom(gameTable, sign, gameTable)) {
                return;
            } else if (nextStep(gameTable, sign, gameWindow)) {
                return;


            }
        }


    }

    private boolean stepByRandom(GameTable gameTable, Sign sign, GameTable gameTable1) {

        if (count == 0) {
            while (true) {
                int x = new Random().nextInt(15);
                int y = new Random().nextInt(15);

                Cell cell = new Cell(x, y);
                if (gameTable.isEmpty(cell)) {
                    gameTable.setSign(cell, sign);
                    lastMove[count++] = cell;

                    return true;
                }
            }
        }

        return false;
    }

    private boolean tryToInterrupt(GameTable gameTable, Sign sign, GameTable gameTable1) {
        int[] array = new int[30];
        Cell[] cell = new Cell[30];
        for (int i = 0; i < 15; i++) {
            int countHorizontal = 0;
            int countVertical = 0;
            Cell cellHorizontalToWrite = null;
            Cell cellVerticalToWrite = null;

            for (int j = 0; j < 15; j++) {
                Cell cellHorizontal = new Cell(i, j);
                Cell cellVertical = new Cell(j, i);
                if (gameTable.getSign(cellHorizontal) == Sign.X) {
                    cellHorizontalToWrite = cellHorizontal;
                    countHorizontal++;
                }
                if (gameTable.getSign(cellVertical) == Sign.X) {
                    cellVerticalToWrite = cellVertical;
                    countVertical++;
                }

            }
            array[i] = countHorizontal;
            array[i + 15] = countVertical;
            cell[i] = cellHorizontalToWrite;
            cell[i + 15] = cellVerticalToWrite;

        }
        int max = -1;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        if (index < 15) {
            Cell setCell = new Cell(cell[index].getRow(), cell[index].getCol() + 1);
            if (gameTable.isCellInTable(setCell)) {
                gameTable.setSign(setCell, sign);
                return true;
            }

        }
        if (index > 15) {
            Cell setCell = new Cell(cell[index].getRow() + 1, cell[index].getCol());
            if (gameTable.isCellInTable(setCell)) {
                gameTable.setSign(setCell, sign);
                return true;

            }

        }
        return false;
    }


    private boolean nextStep(GameTable gameTable, Sign sign, GameWindow gameWindow) {

        int row = lastMove[count - 1].getRow();
        int col = lastMove[count - 1].getCol();


        while (first) {
            if (gameTable.isCellInTable(new Cell(row, col + 1))) {
                gameTable.setSign(new Cell(row, col + 1), sign);
                lastMove[count++] = new Cell(row, col + 1);

                return true;
            }
            first = false;
        }
        while (second) {
            if (gameTable.isCellInTable(new Cell(row + 1, col))) {
                gameTable.setSign(new Cell(row + 1, col), sign);
                lastMove[count++] = new Cell(row + 1, col);
                return true;

            }
            second = false;

        }
        while (third) {
            if (gameTable.isCellInTable(new Cell(row - 1, col))) {
                gameTable.setSign(new Cell(row - 1, col), sign);
                lastMove[count++] = new Cell(row - 1, col);
                first = second = false;
                return true;
            }
            third = false;

        }
        while (fourth) {
            if (gameTable.isCellInTable(new Cell(row, col - 1))) {
                gameTable.setSign(new Cell(row, col - 1), sign);
                lastMove[count++] = new Cell(row, col - 1);
                first = second = false;
                return true;
            }
            fourth = false;

        }
        first = second = third = fourth = true;
        count = 0;
        return false;
    }


}



