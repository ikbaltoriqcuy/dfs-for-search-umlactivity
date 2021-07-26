package query;

import data.Edge;
import data.Item;
import data.UmlActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class queryXMI {

    private static ArrayList<Item> data = new ArrayList<>();
    private static ArrayList<Edge> edges = new ArrayList<>();
    public static ArrayList<UmlActivity> umlActivity= new ArrayList<>();
    public static ArrayList<String> mainPath = new ArrayList<>();


    public static void query() {
        //Pegawai
        mainPath.add("Pilih Selanjutnya");
        mainPath.add("Anggota Keluarga");

        //Perici
        mainPath.add("Menyetujui rincian SPPD");
        mainPath.add("Memilih menu pembatalan");
        mainPath.add("Menyetujui revisi");

        //Approval
        mainPath.add("Memilih Setuju");
       try {

            File fXmlFile = new File("src/xmi/file.xmi");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getChildNodes();

            //parent
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    //get package element
                    data = new ArrayList<>();
                    edges = new ArrayList<>();
                    NodeList nListActivity = nNode.getChildNodes();
                    if (nNode.getNodeName() == "packagedElement") {
                        int codeAsciiAbjad = 64;
                        Element umlActivityElement = (Element) nNode;
                        for (int n = 0; n < nListActivity.getLength(); n++) {
                            Node item = nListActivity.item(n);

                            //get node
                            if (item.getNodeType() == Node.ELEMENT_NODE) {
                                Element itemElement = (Element) item;

                                if (item.getNodeName() == "node") {
                                    String abjad = "";

                                    ++codeAsciiAbjad;
                                    abjad = String.valueOf( (char) codeAsciiAbjad);
                                    boolean isMainPath = false;
                                    if (mainPath.contains(itemElement.getAttribute("name"))) isMainPath = true;
                                    data.add(
                                            new Item(
                                                    abjad,
                                                    itemElement.getAttribute("xmi:id"),
                                                    itemElement.getAttribute("name"),
                                                    isMainPath
                                            )
                                    );

                                }

                                if (item.getNodeName() == "edge") {
                                    edges.add(
                                            new Edge (
                                                    findItemWithId(itemElement.getAttribute("source")),
                                                    findItemWithId(itemElement.getAttribute("target"))
                                            )
                                    );
                                }
                            }
                        }

                        umlActivity.add(
                                new UmlActivity(
                                        umlActivityElement.getAttribute("name"),
                                        data,
                                        edges
                                )
                        );
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Item findItemWithId(String id) {
        Item item = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id.equals(id)) {
                item = data.get(i);
                break;
            }
        }
        return item;
    }

    private static Node returnNodeByValue(NodeList nodeList, String attributeName, String attributeValue) {
        for (int temp = 0; temp < nodeList.getLength(); temp++) {

            Node nNode = nodeList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) nNode;
                String returnAttribute = eElement.getAttribute(attributeName);
                if(returnAttribute.equals(attributeValue)){
                    return nNode;
                }
            }

        }
        return null;
    }
}
