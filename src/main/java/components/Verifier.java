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

public class Verifier {

    public boolean isWin(GameTable gameTable, Player player) {
        int countRow = 0;
        int countCol = 0;
        int countDig = 0;
        int countDig1 = 0;
        //int countDigRevers =0;
        //int countDigRevers1=0;
        for (int i = 0; i < 15; i++) {


            for (int j = 0; j < 15; j++) {
                Cell cellRow = new Cell(i, j);
                if (gameTable.getSign(cellRow) == player.getSign()) {
                    countRow++;
                } else {
                    countRow = 0;
                }
                Cell cellCol = new Cell(j, i);
                if (gameTable.getSign(cellCol) == player.getSign()) {
                    countCol++;
                } else countCol = 0;
                if (countRow >= 5) {
                    return true;

                }
                if (countCol >= 5) {
                    return true;
                }

            }

            Cell cellDiagonal = new Cell(i, i);
            if (gameTable.getSign(cellDiagonal) == player.getSign()) {
                countDig++;

            } else countDig = 0;
            if (countDig >= 5) {
                return true;
            }
        }
        countDig = 0;
        for (int i = 1; i < 15; i++) {
            for (int j = 0; j < 15; j++) {


                Cell cellDig = new Cell(j, i + j);
                if (gameTable.isCellInTable(cellDig)) {
                    if (gameTable.getSign(cellDig) == player.getSign()) {
                        countDig++;
                    } else countDig = 0;
                }
                Cell cellDigBack = new Cell(i + j, j);
                if (gameTable.isCellInTable(cellDigBack)) {
                    if (gameTable.getSign(cellDigBack) == player.getSign()) {
                        countDig1++;
                    } else {
                        countDig1 = 0;
                    }
                }
                if (countDig >= 5 || countDig1 >= 5) {
                    return true;
                }

            }

        }
        countDig = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {


                Cell cellDig = new Cell(i + j, 14 - j);
                if (gameTable.isCellInTable(cellDig)) {
                    if (gameTable.getSign(cellDig) == player.getSign()) {
                        countDig++;
                    } else countDig = 0;
                } else break;
                Cell cellDigBack = new Cell(j, 14 - j);
                if (gameTable.isCellInTable(cellDigBack)) {
                    if (gameTable.getSign(cellDigBack) == player.getSign()) {
                        countDig1++;
                    } else {
                        countDig = 0;
                    }
                }
                if (countDig >= 5 || countDig1 >= 5) {
                    return true;
                }

            }

        }

        return false;
    }

    public boolean isDraw(GameTable gameTable) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    return false;
                }
            }

        }

        return true;
    }
}
