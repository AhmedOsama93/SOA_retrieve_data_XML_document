package XML_DOC;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLParsingDemo {
    void printFile(Document document){
        List<Book> books = new ArrayList<Book>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                // Get the value of the ID attribute.
                String id = node.getAttributes().getNamedItem("ID").getNodeValue();

                // Get the value of all sub-elements.
                String author = elem.getElementsByTagName("Author")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String title = elem.getElementsByTagName("Title").item(0)
                        .getChildNodes().item(0).getNodeValue();

                String genre = elem.getElementsByTagName("Genre")
                        .item(0).getChildNodes().item(0).getNodeValue();

                Double price = Double.parseDouble(elem.getElementsByTagName("Price")
                        .item(0).getChildNodes().item(0).getNodeValue());

                String publish_Date = elem.getElementsByTagName("Publish_Date")
                        .item(0).getChildNodes().item(0).getNodeValue();

                String description = elem.getElementsByTagName("Description")
                        .item(0).getChildNodes().item(0).getNodeValue();
//String id, String author, String title, String genre,
//                double price, Date publish_Date, String description
                books.add(new Book(id, author, title, genre,price, publish_Date, description));
            }
        }
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }
    void addToFile(Document document,Transformer transf){
        try {
            Element root = document.getDocumentElement();
            Node book1 = createBook(document,"dsfa","sdkla","lsdj","dsfa",389.3,"sdlkfj","lksdjf");
            root.appendChild(book1);

            DOMSource source = new DOMSource(document);
            File myFile = new File("src/XML_DOC/Catalogue.xml");
            StreamResult file = new StreamResult(myFile);
            transf.transform(source, file);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        try {
            //Get Document Builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Load the input XML document, parse it and return an instance of the
            // Document class.

            if(!new File("src/XML_DOC/Catalogue.xml").exists()){
                System.out.println("here we will create a new file");
            }

            Document document = builder.parse(new File("src/XML_DOC/Catalogue.xml"));
            XMLParsingDemo demo=new XMLParsingDemo();
//------------------------------------------------------for saving file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = null;
            transf = transformerFactory.newTransformer();
            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes"); //this line makes extra indentation each time
//------------------------------------------------------


//-------------------------------------------------------our functions()
            demo.printFile(document);
            demo.addToFile(document,transf);
//-------------------------------------------------------

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Node createBook(Document doc, String id, String Author,
                                   String Title, String Genre,double Price, String Publish_Date,String Description) {

        Element book = doc.createElement("Book");

        book.setAttribute("ID", id);
        book.appendChild(createBookElement(doc, "Author", Author));
        book.appendChild(createBookElement(doc, "Title", Title));
        book.appendChild(createBookElement(doc, "Genre", Genre));
        book.appendChild(createBookElement(doc, "Price", Double.toString(Price)));
        book.appendChild(createBookElement(doc, "Publish_Date", Publish_Date));
        book.appendChild(createBookElement(doc, "Description", Description));

        return book;
    }

    private static Node createBookElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }


}
