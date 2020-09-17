import java.util.Date;

public class CreditCard {
    public String CreditCardNumber;
    public String ExpirationDate;
    public String NameOfCardHolder;
    public CreditCardType CardType;
    public String Error;

    public CreditCard(){
        this.Error = "None";
    }
    public Boolean Verify(){
//        int cardNumber = Integer.parseInt(this.CreditCardNumber);

        String card = this.CreditCardNumber;
        //Regular Expressions for checking type of credit card
        String regVisa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String regMaster = "^5[1-5][0-9]{14}$";
            String regExpress = "^3[47][0-9]{13}$";
        String regDiscover = "^6(?:011|5[0-9]{2})[0-9]{12}$";

        // verification of length of credit card number
        int cardLength = this.CreditCardNumber.length();
        if (cardLength > 19){
            this.Error = "InvalidCardNumber";
            this.CardType = CreditCardType.Invalid;
        }
        else {
            if (cardLength == 16 && card.startsWith("6011")){
                this.CardType = CreditCardType.Discover;
            }
            else if (cardLength == 16 && card.matches(regMaster)){
                this.CardType = CreditCardType.MasterCard;
            }
            else if((cardLength == 13 || cardLength == 14) && card.startsWith("4")){
                this.CardType = CreditCardType.Visa;
            }
            else if(cardLength == 15 && card.matches(regExpress)){
                this.CardType = CreditCardType.AmericanExpress;
            }
            else {
                this.Error = "InvalidCardNumber";
                this.CardType = CreditCardType.Invalid;
            }
        }
        return  true;
    }

}

