// --== CS400 File Header Information ==--
// Name: Shi Kaiwen
// Email: kshi42@wisc.edu
// Notes to Grader: <optional extra notes>

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class read the application data and store them into system.
 * @author Shi Kaiwen
 */
public class StudentInfoReader {
    public static HashTableMap<String,Student> readTxtFile(){
        HashTableMap<String,Student>  stuList = new HashTableMap<String,Student>();
        try {
            FileReader fileReader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String len;
            int count = 0;
            while((len = bufferedReader.readLine()) != null){
                String[] split = len.split(",");
                Student student = new Student(split[0],split[1],split[2],Integer.parseInt(split[3]),split[4],split[5]);
                count++;
                stuList.put("student" + count,student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stuList;
    }

}
