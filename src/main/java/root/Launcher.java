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

package root;

import root.components.Game;
import root.components.Verifier;
import root.config.CommandLineArgumentParser;
import root.model.Player;

public class Launcher {
    public static void main(String[] args) {
        CommandLineArgumentParser config = new CommandLineArgumentParser(args);

        Player player1 = config.parser().getPlayer();
        Player player2 = config.parser().getPlayer1();
        Game game = new Game(
                player1,
                player2,
                new Verifier());
        game.game();

    }
}
