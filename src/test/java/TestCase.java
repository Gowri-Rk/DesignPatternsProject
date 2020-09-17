import org.junit.Assert;
import org.junit.Test;

public class TestCase {
    @Test

    public void testCreditCard_shouldBeSetToMasterCard(){
        //Arrange
        CreditCard cc = new CreditCard();
        cc.CreditCardNumber = "5410000000000000";
        cc.ExpirationDate = "3/20/2030";
        cc.NameOfCardHolder = "Alice";

        //Act
        cc.Verify();

        //Assert
        Assert.assertEquals(CreditCardType.MasterCard, cc.CardType);
    }

    @Test
    public void testCreditCard_shouldBeSetToVisa(){
        //Arrange
        CreditCard cc = new CreditCard();
        cc.CreditCardNumber = "4120000000000";
        cc.ExpirationDate = "4/20/2030";
        cc.NameOfCardHolder = "Bob";

        //Act
        cc.Verify();

        //Assert
        Assert.assertEquals(CreditCardType.Visa, cc.CardType);
    }

    @Test
    public  void  testCreditCard_shouldBeSetToAmEx(){
        //Arrange
        CreditCard cc = new CreditCard();
        cc.CreditCardNumber = "341000000000000";
        cc.ExpirationDate = "5/20/2030";
        cc.NameOfCardHolder = "Eve";

        //Act
        cc.Verify();

        //Assert
        Assert.assertEquals(CreditCardType.AmericanExpress, cc.CardType);
    }

    @Test
    public void testCreditCard_shouldBeSetToInvalid(){
        //Arrange
        CreditCard cc = new CreditCard();
        cc.CreditCardNumber = "6010000000000000";
        cc.ExpirationDate = "6/20/2030";
        cc.NameOfCardHolder = "Richard";

        //Act
        cc.Verify();

        //Assert
        Assert.assertEquals(CreditCardType.Invalid, cc.CardType);

    }

}
