package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVReader {
    private List<String[]> dataList;
    private BufferedReader bufferedReader;

    public CSVReader(String path) {

        dataList = new ArrayList<>();

        try {
            String currentLine;
            //BufferedReader anvendes til at indlæse en enkelt linje ad gangen, istedet for hele filen
            //bufferedReader bliver oprettet og sat til path af typen FileReader
            bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)), StandardCharsets.UTF_8));

            //while-løkke der kører indtil currentline er null.
            while ((currentLine = bufferedReader.readLine()) != null) {
                //Skaber et String array (entry), der opdeles af karakteren ";",
                // hvilket så er den udleverede dataliste med film og serier, som bliver opdelt i hhv. årstal, genre osv.
                String[] entry = currentLine.split(";");
                dataList.add(entry);
            }

        } catch (NullPointerException e) {
            new ErrorHandler("Kunne ikke indlæse datafilen " + path).show();
        } catch (IOException e) {
            new ErrorHandler("Kunne ikke indlæse datafilen " + path).show();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    new ErrorHandler("Der skete en kritisk fejl, beklager").show();
                }
            }
        }

    }

    public List<String[]> getDataList() {
        return dataList;
    }
}
