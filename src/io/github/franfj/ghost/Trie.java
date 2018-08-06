package io.github.franfj.ghost;

public class Trie {

    TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode("", false, 0);
    }

    public void addWord(String word) {
        char[] wordArray = word.toCharArray();

        int currentDepth = 0;
        StringBuilder currentValue = new StringBuilder();
        TrieNode currentNode = rootNode;

        for (char aWordArray : wordArray) {
            currentDepth++;

            // in each loop iteration we form the Trie values i.e. for the word "now"
            // we add "n" in the first layer, "no" in the second, and "now" in the third one
            currentValue.append(aWordArray);
            String subWord = currentValue.toString();

            // if the current sub word is not in the map, include it
            if (!currentNode.getChildren().containsKey(subWord)) {
                // if the current sub word is as long as the genuine word it is a leaf node
                if (subWord.length() == word.length()) {
                    currentNode.addChild(subWord, new TrieNode(subWord, true, currentDepth));

                } else {
                    currentNode.addChild(subWord, new TrieNode(subWord, false, currentDepth));
                }

                // if the current sub word is in the map, keep going deeper
            } else {
                currentNode = currentNode.getChildren().get(subWord);
            }
        }
    }

}
