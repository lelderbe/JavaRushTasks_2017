package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 22.05.2017.
 */
public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

    public Model() {
        resetGameTiles();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    list.add(gameTiles[i][j]);
                }
            }
        }

        return list;
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        Tile tile = list.get((int) (Math.random() * list.size()));

        tile.value = Math.random() < 0.9 ? 2 : 4;
        if (maxTile < tile.value) {
            maxTile = tile.value;
        }
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();

        score = 0;
    }

    private void compressTiles(Tile[] tiles) {
        for (int k = 0; k < FIELD_WIDTH - 1; k++) {
            for (int i = 0; i < FIELD_WIDTH - 1; i++) {
                if (tiles[i].value == 0) {
                    int tmp = tiles[i + 1].value;
                    tiles[i + 1].value = tiles[i].value;
                    tiles[i].value = tmp;
                }
            }
        }

    }


    private void mergeTiles(Tile[] tiles) {
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value) { //merge
                tiles[i].value = tiles[i].value * 2;
                tiles[i + 1].value = 0;

                score = score + tiles[i].value;
                if (maxTile < tiles[i].value) {
                    maxTile = tiles[i].value;
                }
            }
        }

        compressTiles(tiles);
    }


//    public static void main(String[] args) {
//        Model model = new Model();
//        System.out.println(Arrays.deepToString(model.gameTiles));
//        model.compressTiles(model.gameTiles[0]);
//        model.compressTiles(model.gameTiles[1]);
//        model.compressTiles(model.gameTiles[2]);
//        model.compressTiles(model.gameTiles[3]);
//        System.out.println(Arrays.deepToString(model.gameTiles));
//        model.mergeTiles(model.gameTiles[0]);
//        model.mergeTiles(model.gameTiles[1]);
//        model.mergeTiles(model.gameTiles[2]);
//        model.mergeTiles(model.gameTiles[3]);
//        System.out.println(Arrays.deepToString(model.gameTiles));
//
//    }
}
