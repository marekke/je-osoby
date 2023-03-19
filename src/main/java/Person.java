import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String name;
    protected String lastName;
    protected String pesel;

    public Person(String name, String lastName, String pesel) {
        this.name = name;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public String toString() {
        return pesel + " " + name + " " + lastName;
    }
}
