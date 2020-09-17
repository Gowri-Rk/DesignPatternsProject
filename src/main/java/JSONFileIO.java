import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileIO implements FileIO {
    public List<CreditCard> Reader(String inputFile) {
        JSONParser jsonParser = new JSONParser();
        List<CreditCard> creditCards = new ArrayList<CreditCard>();

        try (FileReader reader = new FileReader(inputFile))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray creditCardList = (JSONArray) obj;

            for (int i=0; i < creditCardList.size(); i++ ){
                CreditCard cc = parseCreditCardObject((JSONObject) creditCardList.get(i));
                creditCards.add(cc);
            }

//            creditCards.add(creditCardList.forEach( cc -> parseCreditCardObject( (JSONObject) cc ) ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return creditCards;
    }

    public void Writer(List<CreditCard> creditCards, String outputFile) {

        JSONArray ccList = new JSONArray();

        for (int i=0; i < creditCards.size(); i++){
            CreditCard card = creditCards.get(i);
            JSONObject cc = new JSONObject();
            cc.put("CardNumber", card.CreditCardNumber);
            cc.put("CardType", card.CardType);
            cc.put("Error", card.Error);
            ccList.add(cc);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(outputFile)) {

            file.write(ccList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CreditCard parseCreditCardObject(JSONObject creditCard)
    {
        CreditCard ccObj = new CreditCard();

        ccObj.CreditCardNumber = String.valueOf(creditCard.get("CardNumber"));
        ccObj.ExpirationDate = String.valueOf(creditCard.get("ExpirationDate"));
        ccObj.NameOfCardHolder = String.valueOf(creditCard.get("NameOfCardholder"));

        return ccObj;
    }
}

