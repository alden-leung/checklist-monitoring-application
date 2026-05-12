import java.io.*;
import java.util.*;

// Handles reading and writing course data from/to file
public class FileHandler {

    private final String fileName = "courses.txt";

    // Load courses from file
    public ArrayList<Course> loadCourses() {
        ArrayList<Course> list = new ArrayList<>();

        try {
            File file = new File(fileName);

            // If file does not exist, create default data
            if (!file.exists()) createDefaultFile();

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                list.add(new Course(
                        d[0],
                        d[1],
                        Double.parseDouble(d[2]),
                        d[3],
                        d[4],
                        d[5]
                ));
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error loading file.");
        }

        return list;
    }

    // Save updated courses back to file
    public void saveCourses(ArrayList<Course> courses) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            for (Course c : courses) {
                bw.write(c.toFileString());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    // Create default file with initial data
    private void createDefaultFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            bw.write("IT111,Intro to Computing,2.0,1st Year,1st Sem,Not yet taken\n");
            bw.write("IT112,Programming 1,2.0,1st Year,1st Sem,Not yet taken\n");
            bw.write("IT121,Info Systems,3.0,1st Year,2nd Sem,Not yet taken\n");

            bw.close();
        } catch (Exception e) {
            System.out.println("Error creating default file.");
        }
    }
}