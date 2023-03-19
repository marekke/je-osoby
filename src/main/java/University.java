import java.io.Serializable;
import java.util.*;

public class University implements Serializable {
    private final Map<String, Student> students = new TreeMap<>();
    private final Map<String, Instructor> instructors = new TreeMap<>();

    public void addStudent(Student student) {
        if (students.containsKey(student.getPesel())) {
            return;
        }

        students.put(student.getPesel(), student);
    }

    public void addInstructor(Instructor instructor) {
        if (instructors.containsKey(instructor.getPesel())) {
            return;
        }

        instructors.put(instructor.getPesel(), instructor);
    }

    public Optional<Student> getStudentByPesel(String pesel) {
        if (students.containsKey(pesel)) {
            return Optional.of(students.get(pesel));
        }

        return Optional.empty();
    }

    public Optional<Instructor> getInstructorByPesel(String pesel) {
        if (instructors.containsKey(pesel)) {
            return Optional.of(instructors.get(pesel));
        }

        return Optional.empty();
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Instructor> getInstructors() {
        return new ArrayList<>(instructors.values());
    }

    public boolean removePersonByPesel(String pesel) {
        return students.remove(pesel) != null || instructors.remove(pesel) != null;
    }

    public boolean checkIfPersonWithPeselExists(String pesel) {
        return students.containsKey(pesel) || instructors.containsKey(pesel);
    }
}
