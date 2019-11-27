import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
function to change uppercase to lowercase ( parameters : single name or all list )
question 3 -> hashmap  < string,  count> , change capital letters to lowercase
 */

public class Script {
    ArrayList<String> allNamesList;

    public Script() {
        allNamesList = new ArrayList<String>();
    }

    /**
     * Function to read names from txt file
     * pathToFile - enter file path on pc
     */
    public void readNamesFromFile() {
        String pathToFile = "C:\\Users\\Raanan\\Downloads\\semester7\\Quality\\src\\rotem.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            //go over all lines and add them to array named allNamesList
            while ((line = br.readLine()) != null) {
                allNamesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * // TODO: 27/11/2019  Function to count all ....todo this  Itzik
     * @param word
     */
    public void CountSpecificString(String word) {
        int NumberOfAppearances = 0;
        for (int i = 0; i < allNamesList.size(); i++) {
            System.out.println(allNamesList.get(i));
            if (getNumberOfAppearances(word, allNamesList.get(i)) > 0) // no duplicates required.
                NumberOfAppearances++;
        }
        System.out.println(NumberOfAppearances);
    }


    // TODO: 27/11/2019 not sure what this function does, is there a better way to do last index?  Itzik
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

    /**
     * // TODO: 27/11/2019 check this text here Itzik
     * Count for each string in text file, how many times it appeared in text
     * @param length - length of string we are counting
     * @return
     */
    public HashMap<String, Integer> CountAllStrings(int length) {
        HashMap<String, Integer> stringAndCount = new HashMap<>();
        for (int i = 0; i < allNamesList.size(); i++) {
            String currentName = allNamesList.get(i);
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
        s.CountMaxString(3);
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