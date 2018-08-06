package io.github.franfj.ghost.game;

import io.github.franfj.ghost.trie.MiniMaxTrie;
import io.github.franfj.ghost.trie.MiniMaxTrieNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GhostGame {

    MiniMaxTrie gameTrie;
    MiniMaxTrieNode currentNodeSituation;

    public GhostGame(File dictionary) {
        this.gameTrie = new MiniMaxTrie();
        this.readDictionaryFile(dictionary);
        this.gameTrie.calculateHeuristicValue();
        this.currentNodeSituation = gameTrie.getRootNode();
    }

    public void startNewGame() {
        this.currentNodeSituation = gameTrie.getRootNode();
    }

    public MiniMaxTrieNode move(String playerMove) {
        this.currentNodeSituation = currentNodeSituation.getBestCPUMove(playerMove);

        // restart game in case of losing
//        if(this.currentNodeSituation == null) {
//            this.startNewGame();
//        }

        return this.currentNodeSituation;
    }

    private void readDictionaryFile(File dictionary) {
        try {
            FileReader fileReader = new FileReader(dictionary);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            generateTrie(bufferedReader);

            fileReader.close();

        } catch (IOException e) {
            // TODO: Log and throw this exception
        }
    }

    private void generateTrie(BufferedReader bufferedReader) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            gameTrie.addWord(line.trim());
        }
    }

}
