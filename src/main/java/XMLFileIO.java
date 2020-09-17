import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileIO implements FileIO {
    public List<CreditCard> Reader(String inputFile) throws ParserConfigurationException, IOException, SAXException {

        File xmlfile = new File(inputFile);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dom = db.parse(xmlfile);
        dom.getDocumentElement().normalize();
        Element doc = dom.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("row");
        List<CreditCard> creditCards = new ArrayList<CreditCard>();

        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
            System.out.println("\nNode Name :" + node.getNodeName());
            CreditCard creditCard = new CreditCard();

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element)node;
                creditCard.CreditCardNumber = eElement.getElementsByTagName("CardNumber").item(0).getTextContent();
                creditCard.ExpirationDate = eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
                creditCard.NameOfCardHolder = eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();
                creditCards.add(creditCard);
            }
        }

        return creditCards;
    }

    public void Writer(List<CreditCard> creditCards, String outputFile) {
        Document dom;
        Element e = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            Element rootEle = dom.createElement("root");

            for(int i = 0; i < creditCards.size(); i++){
                Element rowEle = dom.createElement("row");
                CreditCard creditCard = creditCards.get(i);
                e = dom.createElement("CardNumber");
                e.appendChild(dom.createTextNode(creditCard.CreditCardNumber));
                rowEle.appendChild(e);

                e = dom.createElement("CardType");
                e.appendChild(dom.createTextNode(creditCard.CardType.toString()));
                rowEle.appendChild(e);

                e = dom.createElement("Error");
                e.appendChild(dom.createTextNode(creditCard.Error));
                rowEle.appendChild(e);

                rootEle.appendChild(rowEle);

            }


            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(outputFile)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

}
