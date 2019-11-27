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

    /**
     * constructor
     */
    public Script() {
        allNamesList = new ArrayList<String>();
    }

    /**
     * Function to read names from txt file and insert into allNamesList
     * pathToFile - enter file path on pc
     */
    public void readNamesFromFile() {
        String pathToFile = "names.txt";
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
     * function to count how many times substring appears in allNamesList - without duplicate
     *
     * @param word - inserted by user
     */
    public void CountSpecificString(String word) {
        int NumberOfAppearances = 0;
        for (int i = 0; i < allNamesList.size(); i++) {
            if (getNumberOfAppearances(word, allNamesList.get(i)) > 0)
                NumberOfAppearances++;
        }
        System.out.println(NumberOfAppearances);
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
            //lastIndex equal to -1 if it went over all string.
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
     * Count for each string in size of length, how many times it appeared in text
     *
     * @param length - of string we are counting
     */
    public void CountAllStrings(int length) {
        HashMap<String, Integer> stringAndCount = new HashMap<>();
        //go over allNamesList 1 by 1
        for (int i = 0; i < allNamesList.size(); i++) {
            String currentName = allNamesList.get(i);
            //go over each word by selecting each time substring in the size of "length"
            allSubstringOfWord(length, stringAndCount, currentName);
        }
        Iterator it = stringAndCount.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + ":" + pair.getValue());
        }
    }


    /**
     * Count for each string in size of length, how many times it appeared in text, with option for lowerCase
     *
     * @param length      - size of substring
     * @param toLowerCase - to convert word to lowercase or not
     * @return Hashmap with each substring and how many times it appeared
     */
    public HashMap<String, Integer> CountAllStrings(int length, boolean toLowerCase) {
        HashMap<String, Integer> stringAndCount = new HashMap<>();
        //go over allNamesList 1 by 1
        for (int i = 0; i < allNamesList.size(); i++) {
            String currentName = allNamesList.get(i);
            if (toLowerCase)
                currentName = currentName.toLowerCase();
            //go over each word by selecting each time substring in the size of "length"
            allSubstringOfWord(length, stringAndCount, currentName);
        }
        return stringAndCount;
    }

    /**
     * updates a hashmap of all the substring from allNamesList
     *
     * @param length
     * @param stringAndCount - hashMap with all names and number of appearances
     * @param currentName    - substring we are adding to hashmap
     */
    private void allSubstringOfWord(int length, HashMap<String, Integer> stringAndCount, String currentName) {
        for (int startIndex = 0; startIndex + length <= currentName.length(); startIndex++) {
            String currentSubstring = "";
            currentSubstring = currentName.substring(startIndex, startIndex + length);
            //add to list or update list
            if (stringAndCount.containsKey(currentSubstring)) {
                int oldValue = stringAndCount.get(currentSubstring);
                stringAndCount.replace(currentSubstring, oldValue + 1);
            } else {
                stringAndCount.put(currentSubstring, 1);
            }
        }
    }

    /**
     * print most frequent substrings of length i from allNamesList
     *
     * @param i - substring length
     */
    private void CountMaxString(int i) {
        // we got list of substrings of length i, and occurrences. converted to lowercase.
        HashMap<String, Integer> stringsAndCount = CountAllStrings(i, true);
        LinkedList<String> mostFrequentSubstrings = new LinkedList<>();
        //find the max value of substring
        int maxValue = 0;
        for (Map.Entry<String, Integer> entry : stringsAndCount.entrySet()) {
            if (entry.getValue() > maxValue) {
                // the substrings that appeared highest times, appeared maxValue times.
                maxValue = entry.getValue();
            }
        }
        //find all substring with same count as max value that was found
        for (Map.Entry<String, Integer> entry : stringsAndCount.entrySet()) {
            if (entry.getValue() == maxValue) {
                mostFrequentSubstrings.add(entry.getKey());
            }
        }
        Iterator it = mostFrequentSubstrings.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * return all names that are contained in 'word'
     *
     * @param word
     */
    public void AllIncludesString(String word) {
        // TODO i need to know instructions about Uppercase words, and if string like "assaf" contains "af" = true or not.. unclear from instructions.
        String currentName;
        for (int i = 0; i < allNamesList.size(); i++) {
            currentName = allNamesList.get(i).toLowerCase();
            if (word.contains(currentName))
                System.out.println(currentName);
        }
    }

    public static void main(String[] args) {
        Script s = new Script();
        s.readNamesFromFile();
        try {
            String input = args[1];
            if (args.length >= 2)
                for (int i = 2; i < args.length; i++)
                    input = input + " " + args[i];

            switch (args[0]) {
                case "CountSpecificString":
                    s.CountSpecificString(input);
                    break;
                case "CountAllStrings":
                    s.CountAllStrings(Integer.parseInt(input));
                    break;
                case "CountMaxString":
                    s.CountMaxString(Integer.parseInt(input));
                    break;
                case "AllIncludesString":
                    s.AllIncludesString(input);
                    break;
                default:
                    System.out.println("Error, wrong commands inserted");
            }
        } catch (Exception e) {
            System.out.println("Please insert command: AllIncludesString,CountMaxString,CountAllStrings,CountSpecificString and then input");
            System.out.println("example CountSpecificString ilan");
        }
    }
}