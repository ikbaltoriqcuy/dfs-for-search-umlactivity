package dfs;

import csv.CSV;
import data.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DFSUtil {
    private HashMap<String,LinkedList<Item>> adjLists;
    public ArrayList<ArrayList<String>> result = new ArrayList<>();
    public String lastVertex = "";
    public int step = 0;
    public CSV csv;


    // Graph creation
    public DFSUtil(int vertices, int umlIndex) {
        adjLists = new HashMap<String,LinkedList<Item>>();
        csv = new CSV(umlIndex);
        for (char i = 'A'; i < (char) 65+vertices ; i++)
            adjLists.put( String.valueOf(i) , new LinkedList<Item>());
    }

    // Add edges
    public void addEdge(String id, Item dest) {
        adjLists.get(id).add(dest);
    }

    // DFS algorithm
    public void RecursiveDFS(String vertex, ArrayList<String> listAllPath) {

        Item item = null;
        ArrayList<String> branchPath = new ArrayList<String>();

        for (int i = 0; i < adjLists.get(vertex).size(); i++)
        {

            item = adjLists.get(vertex).get(i);
            if (listAllPath.contains(lastVertex) ) {
                System.out.println( "\n" + "path " + (++step) + " : ");
                csv.addRow( "\npath " + step + " : " + "\n");
                try {
                    listAllPath = cutList(listAllPath,adjLists.get(vertex).get(i-1).abjad);
                    printBeforePath(listAllPath);
                } catch (Exception e) {}
            }
            adjLists.get(vertex).get(i).visited = true;
            if (adjLists.get(vertex).size() > 1 &&
                    listAllPath.contains(item.abjad) &&
                    !item.isMainPath) continue;
            if (!adjLists.get(vertex).get(i).name.trim().toLowerCase().equals("pilih")) listAllPath.add(item.abjad);

            result.add(listAllPath);
            System.out.println(String.join(" => " , listAllPath));
            csv.addRow(String.join(" => " , listAllPath) +  "\n");
            RecursiveDFS(item.abjad, listAllPath);
        }

        if (adjLists.get(vertex).size() == 0) {
            lastVertex = vertex;
        }

    }

    public void printBeforePath(ArrayList<String> path) {
        for (int i = 1; i < path.size(); i++) {
            for (int j = 0; j < i ; j++) {
                if (j == 0) {
                    if (i != 1) {
                        System.out.print(path.get(j));
                        csv.addRow(path.get(j));
                    }
                } else  {
                    System.out.print(" => " + path.get(j) );
                    csv.addRow(" => " + path.get(j) );
                }
            }
            System.out.println();
            csv.addRow("\n");
        }
    }

    public ArrayList<String> cutList(ArrayList<String> path,String abjad) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            temp.add(path.get(i));
            if (path.get(i).equals(abjad)) {
                temp.remove(temp.size() - 1);
                break;
            }
        }

        return temp;
    }

    public void printResult(String abjad, ArrayList<String> path ) {
        if (abjad == "A") path.add(abjad);
        System.out.println( "\n" + "path " + (++step) + " : ");
        csv.addRow("\n" + "path " + step + " : " + "\n");
        RecursiveDFS(abjad, path);
    }

//    void IterativeDFS(String vertex) {
//        System.out.print(vertex + " => ");
//        Item item = null;
//
//        for (int i = 0; i < adjLists.get(vertex).size(); i++)  //iterate through the linked list and then propagate to the next few nodes
//        {
//            item = adjLists.get(vertex).get(i);
//            if (!item.visited)                    //only propagate to next nodes which haven't been explored
//            {
//                adjLists.get(vertex).get(i).visited = true;
//                RecursiveDFS(item.abjad);
//                break;
//            }
//        }
//    }
}
