package XML_DOC;
import java.io.File;
import java.util.*;
import javax.print.attribute.Attribute;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;


public class XMLParsingDemo {
    static Map<String, Boolean>perviousIds = new HashMap<String, Boolean>();
    List<Book>parsingFile(Document document) {
        List<Book> books = new ArrayList<Book>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                // Get the value of the ID attribute.
                String id = node.getAttributes().getNamedItem("ID").getNodeValue();
                perviousIds.put(id, true);
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
                books.add(new Book(id, author, title, genre, price, publish_Date, description));
            }
        }
        return books;
    }
    void printFile(Document document){
        List<Book>books=parsingFile(document);
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }


    void addToFile(Document document,Book newBook,Transformer transf){
        try {
            removeWhitespace(document);
            Element root = document.getDocumentElement();
            Node book1 = createBook(document,newBook.Id,newBook.Author,newBook.Title,newBook.Genre,newBook.Price,newBook.Publish_Date,newBook.Description);
            root.appendChild(book1);

            DOMSource source = new DOMSource(document);
            File myFile = new File("src/XML_DOC/Catalogue.xml");
            StreamResult file = new StreamResult(myFile);
            transf.transform(source, file);
        }
        catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    void deleteBook(Document document,String id,Transformer transf){
        NodeList books = document.getElementsByTagName("Book");
        removeWhitespace(document);
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element)books.item(i);
            String bookId = book.getAttribute("ID");
            if (id.equals(bookId)) {
                book.getParentNode().removeChild(book);
            }
        }
        DOMSource source = new DOMSource(document);
        File myFile = new File("src/XML_DOC/Catalogue.xml");
        StreamResult file = new StreamResult(myFile);
        try {
            transf.transform(source, file);
        }
        catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    void findBookByTitle(Document document,String title){
        List<Book>books=parsingFile(document);
        for (Book book : books) {
            if(book.getTitle().equals(title)){
                System.out.println(book.toString());
            }
        }
    }
    void findBookByAuthor(Document document,String author){
        List<Book>books=parsingFile(document);
        for (Book book : books) {
            if(book.getAuthor().equals(author)){
                System.out.println(book.toString());
            }
        }
    }

    public static void main(String[] args) {
        try {
            //Get Document Builder
// ------------------------------------------------------parse or create document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document;
            if(new File("src/XML_DOC/Catalogue.xml").exists()){//check if the file exists
                 document = builder.parse(new File("src/XML_DOC/Catalogue.xml"));//parse it
            }
            else {
                document = builder.newDocument();//create new document
                Element root = document.createElement( "Catalogue");
                document.appendChild(root);
            }
//------------------------------------------------------instance of the class
            XMLParsingDemo demo=new XMLParsingDemo();
//------------------------------------------------------for saving file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transf = null;
            transf = transformerFactory.newTransformer();
            transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");

//-------------------------------------------------------our functions()
            System.out.println("Hello :)");
            while (true){
                System.out.println("1:print all records");
                System.out.println("2:add new record");
                System.out.println("3:delete a record");
                System.out.println("4:search");
                System.out.println("5:exit");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                scanner.nextLine();
                if(input==1){
                    demo.printFile(document);
                }
                else if (input==2){
                    Book newBook=new Book();
                    System.out.println("Enter the book Id");
                    String id=scanner.nextLine();
                    if(perviousIds.get(id)!=null && perviousIds.get(id)==true){
                        System.out.println("This id was added before.\n");
                        continue;
                    }
                    System.out.println("Enter the book Author");
                    String author=scanner.nextLine();
                    System.out.println("Enter the book Title");
                    String title=scanner.nextLine();
                    System.out.println("Enter the book Genre");
                    String genre=scanner.nextLine();
                    System.out.println("Enter the book Price");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter the book Publish Date");
                    String date = scanner.nextLine();
                    System.out.println("Enter the book Description");
                    String description = scanner.nextLine();

                    //***********************************************
                    newBook.setId(id);
                    newBook.setAuthor(author);
                    newBook.setTitle(title);
                    newBook.setGenre(genre);
                    newBook.setPrice(price);
                    newBook.setPublish_Date(date);
                    newBook.setDescription(description);

                    demo.addToFile(document,newBook,transf);
                }
                else if (input==3){
                    System.out.println("Enter Id to be deleted:");
                    String deleteId = scanner.nextLine();
                    demo.deleteBook(document,deleteId,transf);
                }
                else if (input==4){
                    System.out.println("1: Search by title");
                    System.out.println("2: Search by Author");
                    Scanner scanner1 = new Scanner(System.in);
                    int wayForSearch=scanner1.nextInt();
                    scanner1.nextLine();
                    if (wayForSearch==1){
                        System.out.println("Enter the book Title");
                        String tit = scanner1.nextLine();
                        demo.findBookByTitle(document,tit);
                    }
                    else if (wayForSearch==2){
                        System.out.println("Enter the book Author");
                        String aut = scanner.nextLine();
                        demo.findBookByAuthor(document,aut);
                    }
                }
                else if (input==5){
                    break;
                }
            }
//-------------------------------------------------------
        }
        catch (Exception e) {
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
    public static void removeWhitespace(Document document) {
        Set<Node> toRemove = new HashSet<Node>();
        DocumentTraversal t = (DocumentTraversal) document;
        NodeIterator it = t.createNodeIterator(document,
                NodeFilter.SHOW_TEXT, null, true);

        for (Node n = it.nextNode(); n != null; n = it.nextNode()) {
            if (n.getNodeValue().trim().isEmpty()) {
                toRemove.add(n);
            }
        }
        for (Node n : toRemove) {
            n.getParentNode().removeChild(n);
        }
    }
    private static Node createBookElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


}
