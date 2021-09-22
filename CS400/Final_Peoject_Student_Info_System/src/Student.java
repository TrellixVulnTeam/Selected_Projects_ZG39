// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class create student object.
 * @author Shi Kaiwen
 */

public class Student {
    private String id;
    private String name;
    private String classes;
    private int age;
    private String address;
    private String contact;

    public Student() {
    }

    public Student(String id, String name, String classes, int age, String address, String contact) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.age = age;
        this.address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return " CAMPUS ID: " + id  +
                "\n NAME: " + name +
                "\n CLASSES: " + classes +
                "\n AGE: " + age +
                "\n HOME ADDRESS: " + address +
                "\n CONTACT: " + contact + '\n' ;
    }
}
