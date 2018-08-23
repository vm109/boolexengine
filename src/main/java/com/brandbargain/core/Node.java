package com.brandbargain.core;

import java.util.List;

public class Node {
    private String data;
    private List<Node> adjacentChilds;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Node> getAdjacentChilds() {
        return adjacentChilds;
    }

    public void setAdjacentChilds(List<Node> adjacentChilds) {
        this.adjacentChilds = adjacentChilds;
    }
}
