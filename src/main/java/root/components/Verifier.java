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

package root.components;

import root.model.Cell;
import root.model.Player;

public class Verifier {

    public boolean isWin(GameTable gameTable, Player player) {
        int countDig = 0;
        int countDig1 = 0;
        if (checkRowAndCol(gameTable, player)) {
            return true;
        } else if (checkMainDiagonalRightHalf(gameTable, player)) {
            return true;
        } else if (checkMainDiagonalLeftHalf(gameTable, player)) {
            return true;
        } else if (checkSecondDiagonalRightHalf(gameTable, player)) {
            return true;
        } else if (checkSecondDiagonalLeftHalf(gameTable, player)) {
            return true;
        } else return false;
    }

    private boolean checkSecondDiagonalRightHalf(GameTable gameTable, Player player) {

        return checkByLambda(gameTable, player, (k, j) -> new Cell(j + k, 14 - k));
    }


    private boolean checkSecondDiagonalLeftHalf(GameTable gameTable, Player player) {
        return checkByLambda(gameTable, player, (k, j) -> new Cell(k, 14 - k - j));
    }

    private boolean checkMainDiagonalRightHalf(GameTable gameTable, Player player) {

        return checkByLambda(gameTable, player, (k, j) -> new Cell(k, j + k));
    }

    private boolean checkMainDiagonalLeftHalf(GameTable gameTable, Player player) {

        return checkByLambda(gameTable, player, (k, j) -> new Cell((15 - j) + k, k));
    }


    private boolean checkRowAndCol(GameTable gameTable, Player player) {

        if (checkByLambda(gameTable, player, Cell::new)) {
            return true;
        } else if (checkByLambda(gameTable, player, (k, j) -> new Cell(j, k))) {
            return true;
        } else
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

    private boolean checkByLambda(GameTable gameTable,
                                  Player player,
                                  Lumbda lumbda) {
        int count = 0;
        for (int j = 0; j < 15; j++) {
            for (int k = 0; k < 15; k++) {
                Cell c = lumbda.convertor(k, j);
                if (gameTable.isCellInTable(c)) {
                    if (gameTable.getSign(c) == player.getSign()) {
                        count++;
                        if (count >= 5) return true;
                    } else count = 0;

                } else break;
            }


        }
        return false;
    }

    @FunctionalInterface
    private interface Lumbda {
        Cell convertor(int k, int j);
    }
}
