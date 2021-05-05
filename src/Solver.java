import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solver {
    public static ArrayList<String> readFile(String chars) {
        String file = "resources/LimitedDict.txt";
        String curLine;
        ArrayList<String> validWords = new ArrayList<String>();
        Map<Character, Integer> charMap = new HashMap<>();
        //map frequency to character
        for (int i = 0; i < chars.length(); ++i){
            if (charMap.containsKey(chars.charAt(i))){
                charMap.put(chars.charAt(i), charMap.get(chars.charAt(i)) + 1);
            }else{
                charMap.put(chars.charAt(i), 1);
            }
        }
        //attempt to read file
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((curLine = bufferedReader.readLine()) != null){
                Boolean add = true;
                //the current line has more characters than letters provided so skip
                if (curLine.length() > chars.length()){ add = false;}
                //loop through chars and check if curLine
                for (int i = 0; i < curLine.length() && add; ++i){
                    char tempChar = curLine.charAt(i);
                    //character is not a valid character, not contained in input
                    if (!chars.contains(String.valueOf(tempChar))){
                        add = false;
                        //System.out.println("rejecting(1): " + curLine);

                        //System.out.println(curLine.charAt(i) + " is not in " + chars + ". BREAKING");
                        break;
                    }
                    //The character is contained in input
                    else{
                        //make sure there are not more of the same character than there are in the input
                        int freq = charMap.get(tempChar);
                        int c = 0;
                        for (int k = 0; k < curLine.length(); k++){
                            if (curLine.charAt(k) == tempChar){
                                c++;
                            }
                            //the line contains more characters than options in input
                            if (c > freq){
                                //System.out.println("rejecting(2): " + curLine);
                                add = false;
                                break;
                            }
                        }
                    }
                }

                if (add){
                    validWords.add(curLine);
                }
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return validWords;

    }
    public static void main(String[] args){

        System.out.println("Please type in all character options from Anagrams: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        ArrayList<String> validWords = new ArrayList<>();
        validWords = readFile(line);
//        Comparator<String> strLenComparator = new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return Integer.compare(o1.length(), o2.length());
//            }
//        };
//        validWords.sort(strLenComparator);
        int c = 0;
        for (String s : validWords){
            if (c == 10){
                System.out.println(s);
                c = 0;
            }else{
                System.out.print(s + " ");
            }
            c++;
        }
    }




}

