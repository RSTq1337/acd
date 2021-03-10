import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        writingDataInFile(sortingData(readingDataFromFile(args[0])));
    }

    private static ArrayList<ArrayList<String>> readingDataFromFile(String file) {
        ArrayList<ArrayList<String>> listOfLists = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file), StandardCharsets.UTF_8)) {
            String s;
            while((s=reader.readLine())!=null) {

                ArrayList<String> list = new ArrayList<>();
                int k = 0;
                for (int i=0;i<s.length();i++)
                {
                    boolean first = false;
                    if (s.charAt(i)=='\t' && k==0){
                        list.add(s.substring(k,i));
                        k = i;
                        first = true;
                    }
                    if (i==s.length()-1){
                        list.add(s.substring(k+1));
                    }
                    if (s.charAt(i)=='\t' && k!=0 && !first){
                        list.add(s.substring(k+1,i));
                        k = i;
                    }
                }
                listOfLists.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLists;
    }

    private static ArrayList<ArrayList<String>> sortingData(ArrayList<ArrayList<String>> listOfLists){
        int sizeOfLine = listOfLists.get(0).size();
        int sizeOfColumn = listOfLists.size();
        ArrayList<ArrayList<String>> listOfSortedLists = new ArrayList<>();
        for (int q = 0; q<sizeOfColumn; q++){
            ArrayList<String> column = new ArrayList<>();
            for (int w = 0; w<sizeOfLine; w++){
                column.add(listOfLists.get(w).get(q));
            }
            Collections.sort(column);
            listOfSortedLists.add(column);
        }
        return listOfSortedLists;
    }

    private static void writingDataInFile(ArrayList<ArrayList<String>> listOfSortedLists) throws IOException {
        String outFile = "out.txt";
        int sizeOfLine = listOfSortedLists.get(0).size();
        int sizeOfColumn = listOfSortedLists.size();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (int e = 0; e<sizeOfColumn; e++){
                for (int r = 0; r<sizeOfLine; r++){
                    if (r!=sizeOfLine-1) {
                        writer.write(listOfSortedLists.get(r).get(e) + '\t');
                    } else {
                        writer.write(listOfSortedLists.get(r).get(e));
                        writer.newLine();
                    }
                }
            }
        }
        System.out.println("Check "+outFile);
    }

}
