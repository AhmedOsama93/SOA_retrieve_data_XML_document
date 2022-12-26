package XML_DOC;

public class Book {
    String Id;
    String Author;
    String Title;
    String Genre;
    double Price;
    String Publish_Date;
    String Description;

    public Book() {

    }

    public Book(String id, String author, String title, String genre,
                double price, String publish_Date, String description) {
        Id = id;
        Author = author;
        Title = title;
        Genre = genre;
        Price = price;
        Publish_Date = publish_Date;
        Description = description;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setPublish_Date(String publish_Date) {
        Publish_Date = publish_Date;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public String getGenre() {
        return Genre;
    }

    public double getPrice() {
        return Price;
    }

    public String getPublish_Date() {
        return Publish_Date;
    }

    public String getDescription() {
        return Description;
    }
    public String getAttributeByIndex(int attributeIndex){
        return switch (attributeIndex) {
            case 0 -> Id;
            case 1 -> Author;
            case 2 -> Title;
            case 3 -> Genre;
            case 4 -> Double.toString(Price);
            case 5 -> Publish_Date;
            case 6 -> Description;
            default -> "";
        };
    }
    @Override
    public String toString() {
        return "Book{" +
                "Id='" + Id + '\'' +
                ", Author='" + Author + '\'' +
                ", Title='" + Title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Price=" + Price +
                ", Publish_Date=" + Publish_Date +
                ", Description='" + Description + '\'' +
                '}';
    }
}

