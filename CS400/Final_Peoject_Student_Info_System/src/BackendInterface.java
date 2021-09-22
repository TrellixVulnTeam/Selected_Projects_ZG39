// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>

import java.util.Scanner;

/**
 * This is a backend of final project
 * @author Shi Kaiwen
 */
public class BackendInterface {
    private HashTableMap<String ,Student> hashTableMap;

    
    public BackendInterface(){
        hashTableMap = StudentInfoReader.readTxtFile();
    }

    /**
     * add students into the hash table map
     * @param student
     */
    public void add(Student student){
        hashTableMap.put("student" + (hashTableMap.size() + 1), student);
        System.out.println("Successfully add student: " + student.getName() + ";with campus ID:" + student.getId());
        System.out.println("------------------------------------------");
    }

    /**
     * update a student already stored in system
     * @param id
     */
    public void update(String id){
        for (int i = 1; i <= hashTableMap.size(); i++) {
            Student s = hashTableMap.get("student" + i);
            if (s.getId().equals(id)) {
                System.out.println("you are trying to update student: " + s.getName() + ";with campus ID:" + s.getId());
                System.out.println("Continue anyway (y/n)?");
            }
        }
        Scanner scnr = new Scanner(System.in);
        scnr.useDelimiter("\n");
        String isFlag = scnr.next();
        if(isFlag.equals("y")) {
            for (int i = 1; i <= hashTableMap.size(); i++) {
                Student s = hashTableMap.get("student" + i);
                if (s.getId().equals(id)) {
                    System.out.println("Enter name of the student:");
                    String name = scnr.next();
                    s.setName(name);
                    System.out.println("Enter student's class:");
                    String classes = scnr.next();
                    s.setClasses(classes);
                    System.out.println("Enter age of the student:");
                    int age = scnr.nextInt();
                    s.setAge(age);
                    System.out.println("Enter Home Address of the student:");
                    String address = scnr.next();
                    s.setAddress(address);
                    System.out.println("Enter student's contact number:");
                    String contact = scnr.next();
                    s.setContact(contact);
                    System.out.println("Successfully updated student: " + s.getName() + ";with campus ID: " + s.getId());
                    System.out.println("------------------------------------------");
                    return;
                }
            }
        } else {
            System.out.println("No changes saved! Student not updated");
            System.out.println("------------------------------------------");
        }
    }

    /**
     * delete a student from the system
     * @param id
     */
    public void delete(String id) {
        for(int i = 1; i <= hashTableMap.size(); i++) {
            Student s = hashTableMap.get("student" + i);
            if(s.getId().equals(id)) {
                System.out.println("you are trying to delete student: "+s.getName()+";with campus ID:"+s.getId());
                System.out.println("Continue anyway (y/n)?");
                Scanner scnr = new Scanner(System.in);
                String isFlag = scnr.next();
                if(isFlag.equals("y")) {
                    hashTableMap.remove("student"+i);
                    System.out.println("Successfully delete student: " + s.getName() +
                        ";with campus ID:" + s.getId());
                    System.out.println("------------------------------------------");
                    return;
                }else{
                    System.out.println("No changes saved! Student not deleted");
                    System.out.println("------------------------------------------");
                    return;
                }
            }
        }
        System.out.println("ERROR! STUDENT NOT IN THE SYSTEM");
        System.out.println("------------------------------------------");
    }

    /**
     * print all students already stored in the system
     */
    public void print() {
        for(int i = 1; i <= hashTableMap.size(); i++) {
            System.out.println(hashTableMap.get("student" + i));
        }
    }

    /**
     * determine if student existed in database by its campus ID
     * @param id
     * @return true when student existed, otherwise false.
     */
    public boolean queryById(String id) {
        for(int i = 1; i <= hashTableMap.size(); i++) {
            if(hashTableMap.get("student" + i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
