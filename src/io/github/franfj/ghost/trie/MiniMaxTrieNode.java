package io.github.franfj.ghost.trie;

import java.util.HashMap;
import java.util.Map;

public class MiniMaxTrieNode {

    private String value;
    private boolean leaf;
    private int depth;
    private double heuristicValue;

    private Map<String, MiniMaxTrieNode> children;

    public MiniMaxTrieNode(String value, boolean leaf, int depth) {
        this.children = new HashMap<>();

        this.value = value;
        this.leaf = leaf;
        this.depth = depth;

        this.heuristicValue = 0;
    }

    public double calculateHeuristicValue() {
        if (this.isLeaf()) {
            if (this.depth % 2 == 0) {
                this.heuristicValue = -1;
            } else {
                this.heuristicValue = 1;
            }

            // prune MiniMaxTrie
            this.children.clear();

        } else {
            double result = 0.0;
            double pathsToGo = 0.0;

            for (Map.Entry<String, MiniMaxTrieNode> entry : this.children.entrySet()) {
                MiniMaxTrieNode value = entry.getValue();
                double childHeuristicValue = value.calculateHeuristicValue();

                if (childHeuristicValue > 0) {
                    result += childHeuristicValue;
                }
                pathsToGo++;
            }

            this.heuristicValue = result / pathsToGo;
        }

        return this.heuristicValue;
    }

    public MiniMaxTrieNode getBestCPUMove(String playerMove) {
        MiniMaxTrieNode currentNode = this.getChildren().get(this.value + playerMove);
        if (currentNode == null || currentNode.isLeaf()) {
            System.out.println("Player lose");

        } else {
            double bestMoveHeuristicValue = 0.0;
            MiniMaxTrieNode bestMove = null;

            for (Map.Entry<String, MiniMaxTrieNode> entry : currentNode.children.entrySet()) {
                MiniMaxTrieNode move = entry.getValue();
                if (move.getHeuristicValue() > bestMoveHeuristicValue) {
                    bestMoveHeuristicValue = move.getHeuristicValue();
                    bestMove = entry.getValue();
                }
            }
            return bestMove;

        }
        return currentNode;
    }

    public void addChild(String childValue, MiniMaxTrieNode child) {
        this.children.put(childValue, child);
    }

    public boolean isLeaf() {
        return leaf;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }

    public Map<String, MiniMaxTrieNode> getChildren() {
        return children;
    }
}
