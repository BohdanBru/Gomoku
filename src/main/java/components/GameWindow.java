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

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameWindow extends JFrame {
    private static final int GAME_TABLE_SIZE = 15;
    private static final int FONT_SIZE = 25;
    private static final int CELL_SIZE = 40;
    private final JLabel[][] cells = new JLabel[GAME_TABLE_SIZE][GAME_TABLE_SIZE];
    private Cell clickedCell;

    public GameWindow() {
        super("Gomoku");
        setSystemLockAndFell();
        setLayout(new GridLayout(GAME_TABLE_SIZE, GAME_TABLE_SIZE));
        createGameTable();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        displayInTheMidlOfTheScreen();

    }

    private void setSystemLockAndFell() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final ClassNotFoundException | UnsupportedLookAndFeelException |
                IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }

    private void createGameTable() {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                final JLabel label = new JLabel();
                cells[i][j] = label;
                label.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(label);
                final Cell cell = new Cell(i, j);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(final MouseEvent unused) {
                        synchronized (GameWindow.this) {
                            clickedCell = cell;
                            GameWindow.this.notifyAll();
                        }
                    }

                });
            }
        }
    }

    private void displayInTheMidlOfTheScreen() {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setVisible(true);
    }


    public void printInstructions() {
        JOptionPane.showMessageDialog(this, "Гра \"Гомоку\" - це гра в крестики - нолики на полі 15 на 15 і ціль гри - зібрати 5 крестиків чи ноликів підряд (в рядок, чи в столбець, чи в діагоналі );\n" +
                "The game \"Homoku\" is a game of crosses - zeroes on a field 15 by 15 and the goal of the game is to collect 5 crosses or zeros in a row (in a row, or in a column, or diagonally)", "Info", JOptionPane.INFORMATION_MESSAGE);
    }


    public void printInfoMessage(final String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }


    public void printErrorMessage(final String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public void printTableRules() {
        // do nothing
    }


    public void printTable(final GameTable gameTable) {
        for (int i = 0; i < GAME_TABLE_SIZE; i++) {
            for (int j = 0; j < GAME_TABLE_SIZE; j++) {
                cells[i][j].setText(String.valueOf(gameTable.getSign(new Cell(i, j))));
            }
        }
    }


    public Cell getUserInput() {
        synchronized (this) {
            try {
                wait();
            } catch (final InterruptedException exception) {
                exception.printStackTrace();
                System.exit(2);
            }
        }
        return clickedCell;
    }

    public void gameOver() {
        System.exit(0);
    }


}
