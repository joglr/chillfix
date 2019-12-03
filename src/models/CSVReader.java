package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {
    private List<String[]> dataList;
    private BufferedReader bufferedReader;
    private String separator;

    private CSVReader(String path, String separator) {

        this.separator = separator;

        try {
            String currentLine;
            bufferedReader = new BufferedReader(new FileReader(path));
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] movie = currentLine.split(separator);
                System.out.println(currentLine);
                // TODO: Put data into dataList
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
    public CSVReader(String path) {
        this(path, ";");
    }

    public List<String[]> getDataList() {
         return dataList;
         }


         }
