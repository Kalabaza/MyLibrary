package mx.iteso.msc.rodriguez.roberto.mylibrary;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class Book {
    private int idBook;
    private String name;
    private int author;
    private int editorial;
    private int publishYear;
    private String country;
    private String language;

    public Book()
    {
        name = "";
        author = -1;
        editorial = -1;
        publishYear = -1;
        country = "";
        language = "";
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getEditorial() {
        return editorial;
    }

    public void setEditorial(int editorial) {
        this.editorial = editorial;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
