package mx.iteso.msc.rodriguez.roberto.mylibrary;

/**
 * Created by Roberto Rodriguez on 4/3/2016.
 */
public class Author {
    private int idAuthor;
    private String name;
    private String country;
    private String extra;

    public Author(String name, String country, String extra) {
        this.name = name;
        this.country = country;
        this.extra = extra;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
