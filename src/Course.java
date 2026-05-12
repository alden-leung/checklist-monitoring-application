// This class represents a single course in the curriculum
public class Course {

    // Course attributes
    String code;
    String title;
    double units;
    String year;
    String term;
    String grade; // stored as String to allow "Not yet taken"

    // Constructor to initialize course object
    public Course(String code, String title, double units, String year, String term, String grade) {
        this.code = code;
        this.title = title;
        this.units = units;
        this.year = year;
        this.term = term;
        this.grade = grade;
    }

    // Convert object into string format for saving into file
    public String toFileString() {
        return code + "," + title + "," + units + "," + year + "," + term + "," + grade;
    }

    // Convert grade to numeric (for GPA), return -1 if not valid
    public double getNumericGrade() {
        try {
            return Double.parseDouble(grade);
        } catch (Exception e) {
            return -1; // Not yet taken
        }
    }
}