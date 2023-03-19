public class Student extends Person{
    private int indeksNumber;
    private String fieldOfStudy;


    public Student(String name, String lastName, String pesel, int indeksNumber, String fieldOfStudy) {
        super(name, lastName, pesel);

        this.indeksNumber = indeksNumber;
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getIndeksNumber() {
        return indeksNumber;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    @Override
    public String toString() {
        return super.toString() + " " + indeksNumber + " " + fieldOfStudy;
    }
}
