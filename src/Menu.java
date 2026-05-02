import java.util.*;
  public class Menu {
    private Scanner sc = new Scanner (System.in);
    private CourseManager manager;
    private FileHandler fileHandler;


    
    public Menu(CourseManager manager, FileHandler fileHandler) {
        this.manager = manager;
        this.fileHandler = fileHandler;





      
}
public void start() {
        while (true) {
            System.out.println("\n=== Checklist Monitoring Application ===");
            System.out.println("<1> Show subjects per term");
            System.out.println("<2> Show subjects with grades");
            System.out.println("<3> Enter grades");
            System.out.println("<4> Edit a course");
            System.out.println("<5> Calculate GPA");   
            System.out.println("<6> Sort by grades"); 
            System.out.println("<7> Quit");

            System.out.print("Choice: ");
          
            try {
              int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        manager.showSubjectsPerTerm();
                        break;
                    case 2:
                        manager.showSubjectsWithGrades();
                        break;
                    case 3:
                        handleEnterGrade();
                        break;
                    case 4:
                        handleEditCourse();
                        break;
                    case 5:
                        manager.calculateGPA();
                        break;
                    case 6:
                        manager.sortByGrade();
                        break;
                    case 7:
                        fileHandler.saveCourses(manager.getCourses());
                        System.out.println("Thank you.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }






    
    
     private void handleEnterGrade() {
        String code;
        while (true) {
            System.out.print("Course code: ");
            code = sc.nextLine();
            if (manager.getCourseByCode(code) != null) {
                break;
            }
            System.out.println("Error: code not found.");
        }

        while (true) {
            System.out.print("Grade: ");
            String grade = sc.nextLine();

            if (!grade.trim().isEmpty()) {
                manager.enterGrade(code, grade);
                break;
            }
            System.out.println("Error: grade cannot be empty.");
        }
    }




    

    private void handleEditCourse() {
        String code;
      
        while (true) {
            System.out.print("Course code to edit: ");
            code = sc.nextLine();
            if (manager.getCourseByCode(code) != null) {
                break;
            }
            System.out.println("Error: code not found.");
        }

        String title;
        while (true) {
            System.out.print("New title: ");
            title = sc.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            }
            System.out.println("Error: Title cannot be empty.");
        }

        double units;
        while (true) {
            try {
                System.out.print("New units: ");
                units = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number. ");
            }
        }

        manager.editCourse(code, title, units);
    }
}
