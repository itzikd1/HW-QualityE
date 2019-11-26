import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/*
function to change uppercase to lowercase ( parameters : single name or all list )
question 3 -> hashmap  < string,  count> , change capital letters to lowercase



 */
public class Script {
    ArrayList<String> names;

    public Script() {
        names = new ArrayList<String>();
    }

    public void readNamesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Raanan\\Downloads\\semester7\\Quality\\src\\rotem.txt"))) {
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

    public void CountSpecificString(String s) {

        int answer = 0;

        for (int i = 0; i < names.size(); i++) {

            System.out.println(names.get(i));
            if (getNumberOfAppearances(s, names.get(i)) > 0) // no duplicates required.
                answer++;
        }

        System.out.println(answer);
    }

    /**
     * @param substring
     * @param name
     * @return number of times substring appear in name
     */
    private int getNumberOfAppearances(String substring, String name) {
        int lastIndex = 0;
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            while (lastIndex != -1) {
                lastIndex = name.indexOf(substring, lastIndex);
                if (lastIndex != -1) {
                    count++;
                    lastIndex += substring.length();
                }
            }

        }
        return (count);
    }

    public HashMap<String, Integer> CountAllStrings(int length) {
        HashMap<String, Integer> stringAndCount = new HashMap<>(); // for each substring of length length, count occurences.
        for (int i = 0; i < names.size(); i++) { // iterate all names
            String currentName = names.get(i);
            for (int startIndex = 0; startIndex + length <= currentName.length(); startIndex++) {
                String currentSubstring = "";
                for (int j = 0; j < length; j++) { // create the substring that has to be added to answer list.
                    currentSubstring += currentName.charAt(startIndex + j);
                }
                if (stringAndCount.containsKey(currentSubstring)) {
                    int oldValue = stringAndCount.get(currentSubstring);
                    stringAndCount.replace(currentSubstring, oldValue + 1);
                } else {
                    stringAndCount.put(currentSubstring, 1);
                }

            }
        }


        //print
        Iterator it = stringAndCount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + ":" + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException

        }
        return stringAndCount;
    }


    public static void main(String[] args) {
        Script s = new Script();
        s.readNamesFromFile();
//        s.CountSpecificString("afdsaaaaaaaaaaaafA");
//        s.CountAllStrings(11);
        s.CountMaxString(5);
    }

    private void CountMaxString(int i) {
        HashMap<String, Integer> stringsAndCount = CountAllStrings(i);// we got list of substrings of length 2, and occurences.
        LinkedList<String> mostFrequentSubstrings = new LinkedList<>();
        //find the
        int maxValue = 0;
        for (Map.Entry<String, Integer> entry : stringsAndCount.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue(); // the substrings that appeared highest times, appeared maxValue times.
            }
        }
        for (Map.Entry<String, Integer> entry : stringsAndCount.entrySet()) {
            if (entry.getValue() == maxValue) {
                mostFrequentSubstrings.add(entry.getKey());
            }


        }

        System.out.println(mostFrequentSubstrings);
    }
}