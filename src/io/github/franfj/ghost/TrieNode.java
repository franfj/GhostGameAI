package io.github.franfj.ghost;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private String value;
    private boolean leaf;
    private int depth;
    private double heuristicValue;

    private Map<String, TrieNode> children;

    public TrieNode(String value, boolean leaf, int depth) {
        this.children = new HashMap<>();

        this.value = value;
        this.leaf = leaf;
        this.depth = depth;

        this.heuristicValue = 0;
    }

    public double calculateHeuristicValue() {
        if (this.value.contains("abalienate")) {
            int a = 0;
        }
        if (this.isLeaf()) {
            if (this.depth % 2 == 0) {
                this.heuristicValue = -1;
            } else {
                this.heuristicValue = 1;
            }

            // prune Trie
            this.children.clear();

        } else {
            double result = 0.0;
            double pathsToGo = 0.0;
//            if (this.depth % 2 == 0) {
//                result = -1;
//            } else {
//                result = 1;
//            }

            for (Map.Entry<String, TrieNode> entry : this.children.entrySet()) {
                TrieNode value = entry.getValue();
                double childHeuristicValue = value.calculateHeuristicValue();

                if (childHeuristicValue > 0) {
                    result += childHeuristicValue;
                }
                pathsToGo++;

//                if (this.depth % 2 == 0 && childHeuristicValue > result) {
//                    result = childHeuristicValue;
//
//                } else if (this.depth % 2 != 0 && childHeuristicValue < result) {
//                    result = childHeuristicValue;
//                }
            }

            this.heuristicValue = result / pathsToGo;
        }

        return this.heuristicValue;
    }

    public TrieNode getBestMove(String playerMove) {
        TrieNode currentNode = this.getChildren().get(this.value + playerMove);
        if (currentNode == null || currentNode.isLeaf()) {
            System.out.println("Player lose");

        } else {
            double bestMoveHeuristicValue = 0.0;
            TrieNode bestMove = null;

            for (Map.Entry<String, TrieNode> entry : currentNode.children.entrySet()) {
                TrieNode move = entry.getValue();
                if (move.getHeuristicValue() > bestMoveHeuristicValue) {
                    bestMoveHeuristicValue = move.getHeuristicValue();
                    bestMove = entry.getValue();
                }
            }
            return bestMove;

        }
        return currentNode;
    }

    public void addChild(String childValue, TrieNode child) {
        this.children.put(childValue, child);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(double heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public Map<String, TrieNode> getChildren() {
        return children;
    }
}
