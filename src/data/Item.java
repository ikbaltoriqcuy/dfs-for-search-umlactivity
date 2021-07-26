package data;

public class Item {
    public String abjad;
    public boolean visited;
    public String id;
    public String name;
    public boolean isMainPath;

    public Item(String abjad, String id, String name, boolean isMainPath) {
        this.abjad = abjad;
        this.id = id;
        this.name = name;
        this.isMainPath = isMainPath;
    }

    @Override
    public String toString() {
        return abjad + ") "  + name ;
    }
}
