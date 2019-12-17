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
            //BufferedReader anvendes til at indlæse en enkelt linje ad gangen, istedet for hele filen
            //bufferedReader bliver oprettet og sat til path af typen FileReader
            bufferedReader = new BufferedReader(new FileReader(path));

            //while-løkke der kører indtil currentline er null.
            while ((currentLine = bufferedReader.readLine()) != null) {
                //Skaber et String array (entry), der opdeles af karakteren ";",
                // hvilket så er den udleverede dataliste med film og serier, som bliver opdelt i hhv. årstal, genre osv.
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
