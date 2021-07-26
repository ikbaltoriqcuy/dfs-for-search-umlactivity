package data;

import java.util.ArrayList;

public class UmlActivity {
    public String name;
    public ArrayList<Item> items;
    public ArrayList<Edge> edge;

    public UmlActivity(String name, ArrayList<Item> items, ArrayList<Edge> edge) {
        this.name = name;
        this.items = items;
        this.edge = edge;
    }
}
