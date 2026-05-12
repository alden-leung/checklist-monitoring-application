import java.util.*;

// Handles operations related to courses
public class CourseManager {

    private ArrayList<Course> courses;

    public CourseManager(ArrayList<Course> courses) {
        this.courses = courses;
    }

    // Display subjects grouped by year then term
    public void showSubjectsPerTerm() {
        System.out.println("----------");
        System.out.println("COURSE LIST BY YEAR AND TERM");
        System.out.println("----------");

        // Sort by year then term
        courses.sort((a, b) -> {
            int yearCompare = compareYear(a.year, b.year);
            if (yearCompare != 0) return yearCompare;
            return compareTerm(a.term, b.term);
        });

        String currentYearTerm = "";
        for (Course c : courses) {
            String yearTerm = c.year + " - " + c.term;
            if (!yearTerm.equals(currentYearTerm)) {
                if (!currentYearTerm.isEmpty()) {
                    System.out.println();
                }
                System.out.println(yearTerm);
                currentYearTerm = yearTerm;
            }
            System.out.println(c.code + " | " + c.title + " | " + c.units + " units");
        }
    }

    // Display subjects with grades grouped by year then term
    public void showSubjectsWithGrades() {
        System.out.println("-------------------------------------");
        System.out.println("COURSES WITH GRADES BY YEAR AND TERM");
        System.out.println("-------------------------------------");

        // Sort by year then term
        courses.sort((a, b) -> {
            int yearCompare = compareYear(a.year, b.year);
            if (yearCompare != 0) return yearCompare;
            return compareTerm(a.term, b.term);
        });

        String currentYearTerm = "";
        for (Course c : courses) {
            String yearTerm = c.year + " - " + c.term;
            if (!yearTerm.equals(currentYearTerm)) {
                if (!currentYearTerm.isEmpty()) {
                    System.out.println();
                }
                System.out.println(yearTerm);
                currentYearTerm = yearTerm;
            }
            System.out.println(c.code + " | " + c.title + " | Grade: " + c.grade);
        }
    }

    private int compareYear(String year1, String year2) {
        String[] order = {"1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year"};
        int idx1 = java.util.Arrays.asList(order).indexOf(year1);
        int idx2 = java.util.Arrays.asList(order).indexOf(year2);
        return Integer.compare(idx1, idx2);
    }

    private int compareTerm(String term1, String term2) {
        if (term1.equals("1st Sem") && term2.equals("2nd Sem")) return -1;
        if (term1.equals("2nd Sem") && term2.equals("1st Sem")) return 1;
        return 0;
    }

    // Enter grade for a course
    public void enterGrade(String code, String grade) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {

                // Validate numeric grade
                try {
                    double g = Double.parseDouble(grade);
                    if (g < 0 || g > 100) {
                        System.out.println("Invalid grade range.");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid grade input.");
                    return;
                }

                c.grade = grade;
                System.out.println("Grade updated.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    // Edit course details
    public void editCourse(String code, String newTitle, double newUnits) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                c.title = newTitle;
                c.units = newUnits;
                System.out.println("Course updated.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    // Add new course
    public void addCourse(String code, String title, double units, String year, String term) {
        if (courseExists(code)) {
            System.out.println("Course code already exists.");
            return;
        }

        if (!isUnitsWithinLimit(code, units)) {
            System.out.println("Total units exceed 26 limit.");
            return;
        }

        courses.add(new Course(code, title, units, year, term, "Not yet taken"));
        System.out.println("Course added successfully.");
    }

    // ================= EXTRA FEATURES =================

    // Calculate GPA (average grade)
    public void calculateGPA() {
        double total = 0;
        int count = 0;

        for (Course c : courses) {
            double g = c.getNumericGrade();
            if (g >= 0) {
                total += g;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No grades available.");
        } else {
            System.out.printf("GPA: %.4f%n", total / count);
        }
    }

    // Sort courses by grade descending
    public void sortByGrade() {
        courses.sort((a, b) -> Double.compare(b.getNumericGrade(), a.getNumericGrade()));

        System.out.println("\nCourses sorted by grade:");
        for (Course c : courses) {
            System.out.println(c.code + " | " + c.grade);
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean courseExists(String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUnitsWithinLimit(String code, double newUnits) {
        double total = 0;
        for (Course c : courses) {
            if (!c.code.equalsIgnoreCase(code)) {
                total += c.units;
            }
        }
        return (total + newUnits) <= 26;
    }

    public Course getCourseByCode(String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}