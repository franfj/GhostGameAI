package app;

import io.github.franfj.ghost.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Test {

    public static void main(String[] args) {

        Trie testTrie = new Trie();

        try {
            File file = new File("resources/ghostGameDict.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                testTrie.addWord(line.trim());
            }
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        testTrie.calculateHeuristicValue();

        System.out.println(testTrie);

    }


}
