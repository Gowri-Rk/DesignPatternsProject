public enum CreditCardType {
    Discover ("Discover"),
    MasterCard ("MasterCard"),
    Visa ("Visa"),
    AmericanExpress ("AmericanExpress"),
    Invalid ("Invalid");

    private final String type;

    CreditCardType(String creditCardType) {
        type = creditCardType;
    }

    public String toString() {
        return this.type;
    }
}
