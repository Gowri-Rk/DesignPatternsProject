import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.opencsv.*;

public class CSVFileIO implements FileIO {

    public List<CreditCard> Reader(String inputFile) throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        CSVReader reader = new CSVReader(fileReader, ',', '"', 1);
        List<CreditCard> creditCards = new ArrayList<CreditCard>();

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                CreditCard creditCard = new CreditCard();
                String csvLine = Arrays.toString(nextLine);
                String[] values = csvLine.split(",");

                System.out.println(nextLine[0]);
                String cardNumber;
                if (nextLine[0].startsWith("\"")) {
                    cardNumber = nextLine[0].replaceAll("^\"|\"$", "");
                }
                else {
                    cardNumber = nextLine[0];
                }
                creditCard.CreditCardNumber = cardNumber;
                creditCard.ExpirationDate = nextLine[1];
                creditCard.NameOfCardHolder = nextLine[2];

                creditCards.add(creditCard);

            }
        }

        return creditCards;
    }

    public void Writer(List<CreditCard> creditCards, String outputFile) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
        List<String> header = new ArrayList<String>();
        header.add("CardNumber");
        header.add("CardType");
        header.add("Error");

        String[] nextLine = new String[header.size()];
        header.toArray(nextLine);

        writer.writeNext(nextLine);

        for(int i = 0; i < creditCards.size(); i++){
            CreditCard creditCard = creditCards.get(i);
            List<String> newline = new ArrayList<String>();
            newline.add(creditCard.CreditCardNumber);
            newline.add(creditCard.CardType.toString());
            newline.add(creditCard.Error);

            String[] csvline = new String[newline.size()];
            newline.toArray(csvline);

            writer.writeNext(csvline);
        }
        writer.close();


    }
}
