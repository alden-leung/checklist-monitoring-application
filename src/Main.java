import java.util.*;

// Main class to run the application
public class Main {

    public static void main(String[] args) {

        // Create file handler
        FileHandler fileHandler = new FileHandler();

        // Load courses from file
        ArrayList<Course> courses = fileHandler.loadCourses();

        // Pass data to manager
        CourseManager manager = new CourseManager(courses);

        // Start menu system
        Menu menu = new Menu(manager, fileHandler);
        menu.start();
    }
}