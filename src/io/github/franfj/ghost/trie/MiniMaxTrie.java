package io.github.franfj.ghost.trie;

public class MiniMaxTrie {

    private static final int MIN_WORD_LENGTH = 3;

    MiniMaxTrieNode rootNode;

    public MiniMaxTrie() {
        this.rootNode = new MiniMaxTrieNode("", false, 0);
    }

    public void addWord(String word) {
        if (word.length() < MIN_WORD_LENGTH) {
            return;
        }

        char[] wordArray = word.toCharArray();

        int currentDepth = 0;
        StringBuilder currentValue = new StringBuilder();
        MiniMaxTrieNode currentNode = rootNode;

        for (char wordChar : wordArray) {
            currentDepth++;

            // in each loop iteration we form the MiniMaxTrie values i.e. for the word "now"
            // we add "n" in the first layer, "no" in the second, and "now" in the third one
            currentValue.append(wordChar);
            String subWord = currentValue.toString();

            // if the current sub word is not in the map, include it
            if (!currentNode.getChildren().containsKey(subWord)) {
                MiniMaxTrieNode auxNode;
                // if the current sub word is as long as the genuine word it is a leaf node
                if (subWord.length() == word.length()) {
                    auxNode = new MiniMaxTrieNode(subWord, true, currentDepth);

                } else {
                    auxNode = new MiniMaxTrieNode(subWord, false, currentDepth);
                }

                currentNode.addChild(subWord, auxNode);
                currentNode = auxNode;

                // if the current sub word is in the map, keep going deeper
            } else {
                currentNode = currentNode.getChildren().get(subWord);
            }
        }
    }

    public void calculateHeuristicValue() {
        rootNode.calculateHeuristicValue();
    }

    public MiniMaxTrieNode getRootNode() {
        return rootNode;
    }

}
