package XML_DOC;

import java.util.Date;

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
//<Catalogue>
//<Book ID =”BK101”>
//<Author>Gambardella, Matthew</Author>
//<Title>XML Developer’s Guide</Title>
//<Genre>Computer</Genre>
//<Price>44.95</Price>
//<Publish_Date>2000-11-01</ Publish_Date >
//
//<Description>A guide for developers on how to create applications using XML.</ Description>
//</Book>
//</Catalogue>
