public class Instructor extends Person {
    private String title;
    private String faculty;

    public Instructor(String name, String lastName, String pesel, String title, String faculty) {
        super(name, lastName, pesel);

        this.title = title;
        this.faculty = faculty;
    }

    public String getTitle() {
        return title;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return super.toString() + " " + title + " " + faculty;
    }
}
