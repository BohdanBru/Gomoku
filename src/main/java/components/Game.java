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

public class Game {
    //private final Move move;
    private final Player player1;
    private final Player player2;
    private final Verifier verifier;
    private final GameWindow gameWindow = new GameWindow();


    public Game(Player player1, Player player2, Verifier verifier) {

        this.player1 = player1;
        this.player2 = player2;
        this.verifier = verifier;

    }

    public final void game() {
        gameWindow.printInstructions();
        GameTable gameTable = new GameTable();

        gameWindow.printTable(gameTable);
        int count = 0;


        Player[] players = {player1, player2};


        while (true) {

            for (final Player player : players) {
                if (player.getName().equalsIgnoreCase("User")) {
                    gameWindow.printTable(gameTable);
                }
                //move.step(gameTable);
                player.doStep(gameTable, gameWindow);
                gameWindow.printTable(gameTable);
                if (count >= 8) {
                    if (verifier.isWin(gameTable, player)) {
                        gameWindow.printInfoMessage(player.getName() + " Win");
                        gameWindow.gameOver();
                        return;
                    }
                    if (verifier.isDraw(gameTable)) {
                        gameWindow.printInfoMessage("Game Grow");
                        gameWindow.gameOver();
                        return;
                    }
                }

                count++;
            }

        }

    }
}
