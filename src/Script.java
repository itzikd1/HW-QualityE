import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/*
function to change uppercase to lowercase ( parameters : single name or all list )
question 3 -> hashmap  < string,  count> , change capital letters to lowercase



 */
public class Script {
    ArrayList<String> names;
    public Script(){
        names= new ArrayList<String>();
    }

    public void readNamesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Raanan\\Downloads\\semester7\\Quality\\src\\a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CountSpecificString (String s){
        int count = 0;
        

        System.out.println(count);
    }
    public void CountAllStrings (int length){
        //hashmap <String, int>    like <aa,2>,<ab,3>


        //sout map
    }



    public static void main(String[] args) {
        Script s = new Script();
        s.readNamesFromFile();

    }
}
