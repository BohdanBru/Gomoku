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

package root.model;

public class Cell {


    private final int row;
    public final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(Object i, Object j) {
        this.row = (int) i;
        this.col = (int) j;
    }


    public int getRow() {
        return row;

    }

    public int getCol() {
        return col;
    }


    @Override
    public String toString() {
        return "model.Cell{" +
                "row=" + row +
                ", cal=" + col +
                '}';
    }
}
