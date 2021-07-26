package data;

public class Edge {
    public Item source;
    public Item target;

    public Edge(Item source, Item target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Edge (" + source.name + " => " + target.name + ')';
    }
}
