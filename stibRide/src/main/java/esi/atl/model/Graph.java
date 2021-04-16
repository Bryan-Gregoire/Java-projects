package esi.atl.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

}
