package io.github.franfj.ghost;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    private String value;
    private boolean leaf;
    private int depth;
    private int heuristicValue;

    private Map<String, TrieNode> children;

    public TrieNode(String value, boolean leaf, int depth) {
        this.children = new HashMap<>();

        this.value = value;
        this.leaf = leaf;
        this.depth = depth;

        this.heuristicValue = 0;
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

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public Map<String, TrieNode> getChildren() {
        return children;
    }
}
