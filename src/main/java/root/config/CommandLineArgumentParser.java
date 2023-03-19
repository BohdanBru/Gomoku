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

package root.config;

import root.components.ComputerMove;
import root.components.UserMove;
import root.model.Player;
import root.model.Players;
import root.model.Sign;

public class CommandLineArgumentParser {
    private String[] arg;

    public CommandLineArgumentParser(String[] arg) {
        this.arg = arg;
    }

    public CommandLineArgument parser() {
        Player player = null;
        Player player1 = null;
        for (String s : arg) {
            if (s.equalsIgnoreCase("computer")) {
                if (player == null) {
                    player = new Player(Sign.O, Players.Computer, new ComputerMove());
                } else if (player1 == null) {
                    player1 = new Player(Sign.X, Players.Computer, new ComputerMove());
                }
            }
            if (s.equalsIgnoreCase("user")) {
                if (player == null) {
                    player = new Player(Sign.X, Players.User, new UserMove());
                } else if (player1 == null) {
                    player1 = new Player(Sign.O, Players.User, new UserMove());
                }
            }
        }
        if (player == null) {
            player = new Player(Sign.X, Players.User, new UserMove());
        }
        if (player1 == null) {
            player1 = new Player(Sign.O, Players.Computer, new ComputerMove());
        }


        return new CommandLineArgument(player, player1);
    }

    public static class CommandLineArgument {
        Player player;
        Player player1;

        public CommandLineArgument(Player player, Player player1) {
            this.player = player;
            this.player1 = player1;
        }

        public Player getPlayer() {
            return player;
        }

        public Player getPlayer1() {
            return player1;
        }
    }

}
