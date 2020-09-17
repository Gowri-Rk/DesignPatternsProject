import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardSolver {
    //Main Class

    public  static  void  main(String[] args) throws  Exception{
        System.out.println(args.length);
        if(args.length >= 2) {
            File file = new File(args[0]);
            String inputFile = args[0];
            String outputFile = args[1];

            String extension = FilenameUtils.getExtension(inputFile);


            FileIO fileio;

            if (extension.equals("csv")) {
                fileio = new CSVFileIO();
            }
            else if (extension.equals("json")) {
                fileio = new JSONFileIO();
            }
            else if (extension.equals("xml")){
                fileio = new XMLFileIO();
            }
            else
            {
                System.out.println("Invalid input file entered");
                return;
            }

            try {
                List<CreditCard> creditCards = fileio.Reader(inputFile);
                for (int i = 0; i < creditCards.size(); i++) {
                    creditCards.get(i).Verify();
                }
                fileio.Writer(creditCards, outputFile);
            }
            catch (Exception e)
            {
                System.out.println("Program exited with Exception");
                System.out.println(e);
            }
        }
        else System.out.println("Please enter input and output files");
    }

//    private static FileType getFileType(String extension) {
//        if (extension.equals("csv")){
//            return FileType.csv;
//        }
//        else if(extension.equals("json")){
//            return FileType.json;
//        }
//        else if (extension.equals("xml")){
//            return FileType.xml;
//        }
//    }

}
