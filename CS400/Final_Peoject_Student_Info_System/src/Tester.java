// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>
import org.junit.Test;

/**
 * All 5 Junit Test
 */
public class Tester {
    /**
     * Added test for add student
     */
    @Test
    public void addStu(){
        BackendInterface studentService=new BackendInterface();
        Student student=new Student("123434","dfvfd","cdfvf",12,"ferbrrtb","evrvgf");
        studentService.add(student);
    }


    @Test
    public void addStu2(){
        BackendInterface studentService=new BackendInterface();
        Student student = new Student("12584454","dfvfd","cdfvf",12,"ferbrrtb","evrvgf");
        studentService.add(student);
    }
    
    @Test
    public void addStu3(){
        BackendInterface studentService=new BackendInterface();
        Student student = new Student("1234554","dfvfd","cdfvf",12,"ferbrrtb","evrvgf");
        studentService.add(student);
    }

    /**
     * list student list in database
     */
    @Test
    public void printTest(){
        BackendInterface studentService=new BackendInterface();
        studentService.print();
    }
    /**
     * query with ID test
     */
    @Test
    public void query(){
        BackendInterface studentService=new BackendInterface();
        boolean b = studentService.queryById("201400015");
        System.out.println(b);

    }

}
