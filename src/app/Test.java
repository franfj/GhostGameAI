package app;

import io.github.franfj.ghost.game.GhostGame;

import java.io.File;

class Test {

    public static void main(String[] args) {

        GhostGame ghostGame = new GhostGame(new File("resources/ghostGameDict.txt"));
        ghostGame.startNewGame();


    }


}
