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

import java.util.Arrays;

public class GameTable {


    private final Sign[][] gameTable = new Sign[15][15];

    public GameTable() {
        for (int i = 0; i < gameTable.length; i++) {
            for (int j = 0; j < gameTable.length; j++) {
                gameTable[i][j] = Sign.EMPTY;

            }

        }

    }

    public boolean isCellInTable(Cell cell) {
        if (cell.getCol() <= 14 &&
                cell.getCol() >= 0 &&
                cell.getRow() <= 14 &&
                cell.getRow() >= 0) {

            return isEmpty(cell);
        }
        return false;
    }

    public boolean isEmpty(Cell cell) {
        return gameTable[cell.getRow()][cell.getCol()] == Sign.EMPTY;
    }

    public Sign getSign(Cell cell) {
        return gameTable[cell.getRow()][cell.getCol()];
    }

    public void setSign(Cell cell, Sign sign) {
        gameTable[cell.getRow()][cell.getCol()] = sign;
    }

    /*public static void main(String[] args) {
        System.out.println(String.valueOf(new GameTable().getSign(new Cell(1,1))));
    }*/

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GameTable{");
        sb.append("gameTable=").append(gameTable == null ? "null" : Arrays.asList(gameTable).toString());
        sb.append('}');
        return sb.toString();
    }


}
