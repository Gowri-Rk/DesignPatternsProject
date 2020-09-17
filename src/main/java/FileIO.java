import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

//Interface for methods that read and write files in various formats
public interface FileIO {
    public List<CreditCard> Reader(String inputFile) throws IOException, ParserConfigurationException, SAXException;
    public void Writer(List<CreditCard> creditCards, String outputFile) throws IOException;
}
