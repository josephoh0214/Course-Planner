import java.util.*;
import java.io.*;

public class courseTracker {
	public static void main(String[] args) throws FileNotFoundException {
		intro();
		Scanner console = new Scanner(System.in);
		System.out.println("What is your current or intended major?");
		String major = console.nextLine();
		System.out.println("");
        preReq(major);
        System.out.println("Are you currently in this major? (Y/N)");
        String ans = console.nextLine();
		System.out.println("");
        if (ans.equalsIgnoreCase("y")) {
			gradReq();
 		} else {
			System.out.println("Good luck on your application!");
        }
		System.out.println("");
		infoDegreeOps(console);
	}
	
	public static void intro () {
		System.out.println("This program will inform you of what courses are remaining to take");
		System.out.println("in order to acquire a bachelor's degree in your interest of major.");
		System.out.println("");
	}
   
	public static void preReq(String major) throws FileNotFoundException {
		Scanner dataset = new Scanner(new File("src/main/resources/informatics_prereq.csv"));
    	dataset.useDelimiter(",");
    	String department = dataset.next();
    	if (major.equalsIgnoreCase(department)) {
    		System.out.println("Pre-requisite courses for " + major + ":");
		}
		int i = 1;
		while (dataset.hasNext()) {
			System.out.println(i + ". " + dataset.next());
			i++;
		}
   }
   
   public static void gradReq() throws FileNotFoundException {
	    System.out.println("Core courses of graduation requirements:");
		Scanner dataset = new Scanner(new File("src/main/resources/informatics_gradreq.csv"));
		dataset.useDelimiter(",");
		Set<String> coreCourses = new TreeSet<>();
		while (dataset.hasNextLine()) {
			coreCourses.add(dataset.nextLine());
		}
		int i = 1;
		for (String courses : coreCourses) {
			System.out.println(i + ". " + courses);
			i++;
		}
	}

	public static void infoDegreeOps(Scanner console) throws FileNotFoundException {
		Scanner dataset = new Scanner(new File("src/main/resources/informatics_degree.csv"));
		dataset.useDelimiter(",");
		System.out.println("Degree Options:");
		System.out.println("(1) Biomedical & Health Informatics");
		System.out.println("(2) Data Science ");
		System.out.println("(3) Human-Computer Interaction");
		System.out.println("(4) Information Architecture");
		System.out.println("(5) Information Assurance and Cybersecurity");
		System.out.println("(6) Student-Designed Concentration");
		System.out.println("");
		System.out.print("Choose a number among degree options above: ");
		String number = console.nextLine();
		System.out.println("");
		if (number.equals("6")) {
			System.out.println("Students may choose to develop their own concentration, with approval");
			System.out.println("from the academic adviser. Student-designed concentrations are created");
			System.out.println("out of a list of approved courses and also result in the");
			System.out.println("Bachelor of Science degree.");
		} else {
			while (!dataset.nextLine().equals(number)) {
				dataset.nextLine();
			}
			String required = dataset.nextLine();
			System.out.println(required);
		}
	}
}
