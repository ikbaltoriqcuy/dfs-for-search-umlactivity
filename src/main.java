import csv.CSV;
import dfs.DFSUtil;
import query.queryXMI;

import java.util.ArrayList;

public class main {

    public static DFSUtil dfsUtil;

    public static void main(String[] args) {
        queryXMI.query();
        queryActivity();
    }

    static void queryActivity() {
        for (int i = 0; i < queryXMI.umlActivity.size(); i++) {
            System.out.println("*******************************");
            System.out.println("                               ");
            System.out.println("   "+queryXMI.umlActivity.get(i).name+"                    ");
            System.out.println("                               ");
            System.out.println("*******************************");
            System.out.println();

            if (dfsUtil == null) {
                dfsUtil = new DFSUtil(queryXMI.umlActivity.get(i).items.size(),i);
            }

            for (int j = 0; j < queryXMI.umlActivity.get(i).items.size(); j++) {
                if (!queryXMI.umlActivity.get(i).items.get(j).name.toLowerCase().equals("pilih"))
                    System.out.println(queryXMI.umlActivity.get(i).items.get(j) );
            }
            System.out.println();
            System.out.println("Edge :\n");
            for (int j = 0; j < queryXMI.umlActivity.get(i).edge.size(); j++) {
                if (!queryXMI.umlActivity.get(i).edge.get(j).source.name.toLowerCase().equals("pilih") ||
                        !queryXMI.umlActivity.get(i).edge.get(j).target.name.toLowerCase().equals("pilih"))
                    System.out.println(queryXMI.umlActivity.get(i).edge.get(j).source.abjad + " => " +
                    queryXMI.umlActivity.get(i).edge.get(j).target.abjad );
            }
            System.out.println();
            System.out.println("Path Result: \n");
            dfsUtil.csv.addRow("\nPath Result: \n");
            dfsUtil.csv.addRow("\n****************************************************************\n");
            for (int j = 0; j < queryXMI.umlActivity.get(i).edge.size(); j++) {
                dfsUtil.addEdge(
                        queryXMI.umlActivity.get(i).edge.get(j).source.abjad,
                        queryXMI.umlActivity.get(i).edge.get(j).target
                );
            }
            dfsUtil.printResult("A", new ArrayList<>());
            dfsUtil.csv.addRow("\n****************************************************************\n");
            dfsUtil.csv.createFileCSV();
            dfsUtil = null;
        }
    }

}
