package csv;

import query.queryXMI;

import java.io.FileWriter;

public class CSV {

    FileWriter csvWriter;
    int i;

    public CSV(int i) {
        this.i = i;
        try {
            csvWriter = new FileWriter(queryXMI.umlActivity.get(i).name + ".csv");
            csvWriter.append("*******************************\n");
            csvWriter.append("                               \n");
            csvWriter.append("   "+queryXMI.umlActivity.get(i).name+"                    \n");
            csvWriter.append("                               \n");
            csvWriter.append("*******************************\n");
            csvWriter.append("\n\n");
            for (int j = 0; j < queryXMI.umlActivity.get(i).items.size(); j++) {
                if (!queryXMI.umlActivity.get(i).items.get(j).name.toLowerCase().equals("pilih"))
                    csvWriter.append(queryXMI.umlActivity.get(i).items.get(j) + "\n");
            }
            csvWriter.append("\n");
            csvWriter.append("Edge : \n");
            for (int j = 0; j < queryXMI.umlActivity.get(i).edge.size(); j++) {
                if (!queryXMI.umlActivity.get(i).edge.get(j).source.name.toLowerCase().equals("pilih") ||
                        !queryXMI.umlActivity.get(i).edge.get(j).target.name.toLowerCase().equals("pilih"))
                csvWriter.append(queryXMI.umlActivity.get(i).edge.get(j).source.abjad + " => " +
                        queryXMI.umlActivity.get(i).edge.get(j).target.abjad + "\n");
            }
            csvWriter.append("\n");
        } catch (Exception e) {}
    }

    public void addRow(String result) {
        try {
            csvWriter.append(result);
        } catch (Exception e) {}
    }

    public void createFileCSV() {
        try {
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {}
    }

}
