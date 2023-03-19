import java.io.*;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class JeOsobyApp {
    private static final String DB_PATH = "./university.db";

    private static University university;
    private static final Scanner scanner = new Scanner(System.in);
    private static String menuText;

    public static void main(String[] args) {
        restoreUniversityData();
        generateMenuText();

        int selectedOption = -1;

        do {
            System.out.print(menuText);

            try {
                selectedOption = scanner.nextInt();
                scanner.nextLine();

                switch (selectedOption) {
                    case 1 -> listPeople();
                    case 2 -> addPerson();
                    case 3 -> updatePerson();
                    case 4 -> removePerson();
                    case 5 -> showPerson();
                    default -> System.out.println("\nWybierz opcję z przedziału 0 - 5 \n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPodano błędny znak.\n");
                scanner.nextLine();
                continue;
            }

            System.out.println("\n\n");
        } while (selectedOption != 0);

        saveUniversityData();
    }

    private static void saveUniversityData()
    {
        try {
            FileOutputStream fos = new FileOutputStream(DB_PATH);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(university);

            os.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd zapisania bazy.");
            System.exit(1);
        }
    }

    private static void restoreUniversityData()
    {
        try {
            FileInputStream fis = new FileInputStream(DB_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            university = (University) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            university = new University();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Wystąpił błąd odtworzenia bazy.");
            System.exit(1);
        }

    }

    private static void generateMenuText() {
        menuText = "Baza osób \n";
        menuText += "-----------------------\n";
        menuText += "1 - Wyświetl osoby \n";
        menuText += "2 - Dodaj osobę \n";
        menuText += "3 - Edytuj osobę \n";
        menuText += "4 - Usuń osobę \n";
        menuText += "5 - Wyświetl osobę \n";
        menuText += "0 - Exit \n";
        menuText += "Wybierz opcję: ";
    }

    private static void listPeople() {
        System.out.println("\nWykładowcy:");
        university.getInstructors().forEach(System.out::println);

        System.out.println("\nStudenci:");
        university.getStudents().forEach(System.out::println);
    }

    private static void addPerson() {
        System.out.println("Dodaj: 1 - Wykładowcę, 2 - Studenta ");
        int type = scanner.nextInt();
        scanner.nextLine();

        if (type == 1) {
            university.addInstructor(instructorForm());
        } else if (type == 2) {
            university.addStudent(studentForm());
        } else {
            System.out.println("Wybrano błędny typ.");
        }

    }

    private static void updatePerson() {
        System.out.println("Podaj numer PESEL");
        String pesel = scanner.nextLine();

        if (!university.checkIfPersonWithPeselExists(pesel)) {
            System.out.println("Podana osoba nie isnieje.");
            return;
        }

        Optional<Instructor> instructor = university.getInstructorByPesel(pesel);
        if (instructor.isPresent()) {
            Instructor newInstructor = instructorForm();
            university.removePersonByPesel(pesel);
            university.addInstructor(newInstructor);
        } else {
            Student newStudent = studentForm();
            university.removePersonByPesel(pesel);
            university.addStudent(newStudent);
        }
    }

    private static void removePerson() {
        System.out.println("Podaj numer PESEL");
        String pesel = scanner.nextLine();

        if (university.removePersonByPesel(pesel)) {
            System.out.println("Usunięto osobę");
        } else {
            System.out.println("Brak osoby do usunięcia o takim peselu");
        }
    }

    private static void showPerson() {
        System.out.println("Podaj numer PESEL");
        String pesel = scanner.nextLine();

        Optional<Instructor> instructor = university.getInstructorByPesel(pesel);

        if (instructor.isPresent()) {
            System.out.println(instructor.get());
            return;
        }

        Optional<Student> student = university.getStudentByPesel(pesel);
        student.ifPresentOrElse(System.out::println, () -> System.out.println("Brak szukanej osoby"));
    }

    private static Instructor instructorForm() {
        System.out.println("Imię: ");
        String name = scanner.nextLine();

        System.out.println("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.println("PESEL: ");
        String pesel = scanner.nextLine();

        System.out.println("Tytuł: ");
        String title = scanner.nextLine();

        System.out.println("Wydział: ");
        String faculty = scanner.nextLine();

        return new Instructor(name, lastName, pesel, title, faculty);
    }

    private static Student studentForm() {
        System.out.println("Imię: ");
        String name = scanner.nextLine();

        System.out.println("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.println("PESEL: ");
        String pesel = scanner.nextLine();

        System.out.println("Indeks: ");
        int indeks = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Kierunek: ");
        String fieldOfStudy = scanner.nextLine();

        return new Student(name, lastName, pesel, indeks, fieldOfStudy);
    }
}
