import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class InputParser {
    public static void main(String[] args) throws IOException {

        // Constants that store the location of your folders
        String MAJOR_FOLDER = "/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-majors";
        String DEGREES_FOLDER = "/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-degrees";
        String CLASSES_FOLDER = "/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-classes";
        String CMSC_CLASSES_FOLDER = "/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-classes/cmsc-classes-washed";
        String MATH_CLASSES_FOLDER = "/Users/ellio/Documents/School Work/CMSC 436 - Data Visualization/Data/washed-classes/math-classes-washed";

        // Create a new file for each folder we have to search
        File majorFolder = new File(MAJOR_FOLDER);
        File degreesFolder = new File(DEGREES_FOLDER);
        File classesFolder = new File(CLASSES_FOLDER);
        File cmscClassesFolder = new File(CMSC_CLASSES_FOLDER);
        File mathClassesFolder = new File(MATH_CLASSES_FOLDER);

        // Store the list of files in each folder
        File [] listOfMajorFiles = majorFolder.listFiles();
        File [] listOfDegreeFiles = degreesFolder.listFiles();
        File [] listOfClassesFiles = classesFolder.listFiles();
        File [] listOfCmscClasses = cmscClassesFolder.listFiles();
        File [] listOfMathClasses = mathClassesFolder.listFiles();

        // Store the order of fields for major data, class data, and degree data
        ArrayList<String> majorFields = new ArrayList<String>();
        ArrayList<String> classesFields = new ArrayList<String>();
        ArrayList<String> degreeFields = new ArrayList<String>();

        /*
           This is the data structure that will differentiate which semester
           a students major data belongs to. The key is the semester number, the value
           is the studentMajorMap for that semester.
         */
        HashMap<String, HashMap<String, ArrayList>> semesterMajorMap = new HashMap<String, HashMap<String, ArrayList>>();

        /*
           This is the data structure that will differentiate which semester
           a students CMSC class data belongs to. The key is the semester number, the value
           is the studentCmscClassMap for that semester.
         */
        HashMap<String, HashMap<String, ArrayList>> semesterCmscClassMap = new HashMap<String, HashMap<String, ArrayList>>();

        /*
           This is the data structure that will differentiate which semester
           a students MATH class data belongs to. The key is the semester number, the value
           is the studentMathClassMap for that semester.
         */
        HashMap<String, HashMap<String, ArrayList>> semesterMathClassMap = new HashMap<String, HashMap<String, ArrayList>>();

        BufferedReader br;
        String line;
        int index;


        // Iterate through the list of Major files
        for (File file : listOfMajorFiles){

            /*
               This is the data structure that will hold each students major data.
               It is a hash map where the key is the students campus ID and the
               value is an array of that students major data.
            */
            HashMap<String, ArrayList> studentMajorMap = new HashMap<String, ArrayList>();

            // If it's the fields file, store the order of the text fields
            if (file.getName().equals("00Fields")){
                br = new BufferedReader(new FileReader(file));

                // Read one line at a time
                while ((line = br.readLine()) != null){

                    // Find the index of the space to only add the field text
                    index = line.indexOf(" ");
                    majorFields.add(line.substring(index+1));
                }
            }

            // If it is not the fields file, read in data
            else{
                br = new BufferedReader(new FileReader(file));

                // Read one line at a time
                while ((line = br.readLine()) != null){

                    // Skip any blank lines
                    if (line.equals("")){
                        continue;
                    }

                    // Use the students ID as the "key" and the entire data line as an array as the "value" for the hash map
                    ArrayList<String> lineArray = new ArrayList<String>(Arrays.asList(line.split(",")));
                    studentMajorMap.put(lineArray.get(1), lineArray);
                }
            }

            // After each semester file, add new studentMajorMap to the semesterMajorMap
            semesterMajorMap.put(file.getName().substring(0,4), studentMajorMap);
        }


        // Iterate through the list of Class files to get the fields
        for (File file : listOfClassesFiles){

            // If it's the fields file, store the order of the text fields
            if (file.getName().equals("00Fields")){
                br = new BufferedReader(new FileReader(file));

                // Read one line at a time
                while ((line = br.readLine()) != null){

                    // Skip blank line at the end
                    if (line.equals("")){
                        continue;
                    }

                    // Find the index of the space to only add the field text
                    index = line.indexOf(" ");
                    String temp = line.substring(index+1);

                    // There will be two spaces in this field
                    index = temp.indexOf(" ");
                    classesFields.add(temp.substring(index+1));
                }
            }
        }


        // Iterate through the list of CMSC Class files
        for (File file : listOfCmscClasses){

            /*
               This is the data structure that will hold each students CMSC class data.
               It is a hash map where the key is the students campus ID and the
               value is an array of that students CMSC class data.
            */
            HashMap<String, ArrayList> studentCmscClassMap = new HashMap<String, ArrayList>();

            br = new BufferedReader(new FileReader(file));

            // Read one line at a time
            while ((line = br.readLine()) != null) {

                // Skip any blank lines
                if (line.equals("")) {
                    continue;
                }

                // Use the students ID as the "key" and the entire data line as an array as the "value" for the hash map
                ArrayList<String> lineArray = new ArrayList<String>(Arrays.asList(line.split(",")));
                studentCmscClassMap.put(lineArray.get(1), lineArray);
            }

            // After each semester file, add new studentCmscClassMap to the semesterCmscClassMap
            semesterCmscClassMap.put(file.getName().substring(0,4), studentCmscClassMap);
        }


        // Iterate through the list of MATH Class files
        for (File file : listOfMathClasses){

            /*
               This is the data structure that will hold each students MATH class data.
               It is a hash map where the key is the students campus ID and the
               value is an array of that students MATH class data.
            */
            HashMap<String, ArrayList> studentMathClassMap = new HashMap<String, ArrayList>();

            br = new BufferedReader(new FileReader(file));

            // Read one line at a time
            while ((line = br.readLine()) != null) {

                // Skip any blank lines
                if (line.equals("")) {
                    continue;
                }

                // Use the students ID as the "key" and the entire data line as an array as the "value" for the hash map
                ArrayList<String> lineArray = new ArrayList<String>(Arrays.asList(line.split(",")));
                studentMathClassMap.put(lineArray.get(1), lineArray);
            }

            // After each semester file, add new studentMathClassMap to the semesterMathClassMap
            semesterMathClassMap.put(file.getName().substring(0,4), studentMathClassMap);
        }


        // Iterate through the list of Degree files
        for (File file : listOfDegreeFiles) {

            // If it's the fields file, store the order of the text fields
            if (file.getName().equals("00Fields")) {
                br = new BufferedReader(new FileReader(file));

                // Read one line at a time
                while ((line = br.readLine()) != null) {

                    // Skip blank line at the end
                    if (line.equals("")) {
                        continue;
                    }

                    // Find the index of the space to only add the field text
                    index = line.indexOf(" ");
                    String temp = line.substring(index + 1);

                    // There will be two spaces in this field
                    index = temp.indexOf(" ");
                    degreeFields.add(temp.substring(index + 1));
                }
            }
        }


        // Print out major data
        System.out.println("Student Major Data:");
        for (String semester : semesterMajorMap.keySet()){
            System.out.println("Semester: " + semester);
            for (Object student : semesterMajorMap.get(semester).keySet()){
                System.out.println("Student: " + student);
                System.out.println(semesterMajorMap.get(semester).get(student));
            }
            System.out.println();
        }

        // Print out CMSC class data
        System.out.println("Student CMSC Class Data:");
        for (String semester : semesterCmscClassMap.keySet()){
            System.out.println("Semester: " + semester);
            for (Object student : semesterCmscClassMap.get(semester).keySet()){
                System.out.println("Student: " + student);
                System.out.println(semesterCmscClassMap.get(semester).get(student));
            }
            System.out.println();
        }

        // Print out MATH class data
        System.out.println("Student MATH Class Data:");
        for (String semester : semesterMathClassMap.keySet()){
            System.out.println("Semester: " + semester);
            for (Object student : semesterMathClassMap.get(semester).keySet()){
                System.out.println("Student: " + student);
                System.out.println(semesterMathClassMap.get(semester).get(student));
            }
            System.out.println();
        }
    }
}
