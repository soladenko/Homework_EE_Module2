package Model;

/**
 * Created by SO on 15.06.2017.
 */
public class Customer {

    private int id;
    private String Name;
    private String Country;

    public Customer() {
    }

    public Customer(String name, String country) {
        Name = name;
        Country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Country='" + Country + '\'' +
                '}';
    }
}
