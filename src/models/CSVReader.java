package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<String[]> dataList;
    private BufferedReader bufferedReader;

    public CSVReader(String path) {

        dataList = new ArrayList<String[]>();

        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(path));

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] entry = currentLine.split(";");
                dataList.add(entry);
            }

        } catch (FileNotFoundException e) {
            System.out.println("*** could not find file " + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<String[]> getDataList() { return dataList; }
}
