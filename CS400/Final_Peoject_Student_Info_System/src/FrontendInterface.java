// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>

import java.util.Scanner;

public class FrontendInterface{
    private BackendInterface stuService = new BackendInterface();
    private Scanner scnr = new Scanner(System.in);
    
    public void menu() {
        System.out.println("Welcome to student info manage system!");
		System.out.println("------------------------------------------");

		while (true) {
			System.out.println("Choose from addStu, delStu, updateStu, or print");
			System.out.println("Try help for command introduction, or hit anything else to quit.");
			System.out.println("Enter your command:");
			Scanner scnr = new Scanner(System.in);
            scnr.useDelimiter("\n");
            String command = scnr.nextLine();

			switch (command) {
				case "addStu":
                    addStu();
					break;
				case "updateStu":
                    updateStu();
					break;
				case "delStu":
					delStu();
					break;
				case "help":
					commandIntro();
					break;
                case "print":
                    print();
                    break;
				default:
					System.out.println("Exit succussefully.");
					return;
			}
		}
    }

    public void commandIntro(){
        System.out.println("-----------------start------------------------");
        System.out.println("addStu: add a student info the database.");
        System.out.println("delStu: delete a student from the database");
        System.out.println("updateStu: update a student's info already in database");
        System.out.println("print: print out all students' key info currently in database");
        System.out.println("help: re-print this help guide.");
        System.out.println("Hit anything else to quit");
        System.out.println("-----------------end------------------------");
    }

    
    public void print(){
        stuService.print();
    } 

    public void addStu(){
        scnr.useDelimiter("\n");

        System.out.println("Enter Campus ID of the student");
        String id = scnr.next();
        if(stuService.queryById(id)){
            System.out.println("ERROR! STUDENT ALREADY IN THE SYSTEM");
            System.out.println("Try \"updateStu\" for update this student's info");
        }else {
            System.out.println("Enter name of the student");
            String name = scnr.next();
            System.out.println("Enter student's class");
            String classes = scnr.next();
            System.out.println("Enter age of the student");
            int age = scnr.nextInt();
            System.out.println("Enter Home Address of the student");
            String address = scnr.next();
            System.out.println("Enter student's contact number");
            String contact = scnr.next();
            Student student = new Student(id, name, classes, age, address, contact);
            stuService.add(student);
        }
    }

    public void delStu(){
        scnr.useDelimiter("\n");
        System.out.println("Enter Campus ID of the student");
        String id = scnr.next();
        stuService.delete(id);
    }

    public void updateStu(){
        scnr.useDelimiter("\n");
        System.out.println("Enter Campus ID of the student");
        String id = scnr.next();
        stuService.update(id);
    }
}