package generators;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLLoader {
  public static Document loadXML(final String fileName) {

    final File file = new File(fileName);
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      // Build default non-validating DOM-parser
      final DocumentBuilder builder = factory.newDocumentBuilder();
      final Document document = builder.parse(file);
      return document;
    } catch (final SAXParseException spe) {
      // Error generated by the parser
      System.out.println("\n** Parsing error" + ", line " + spe.getLineNumber() + ", uri " + spe.getSystemId());
      System.out.println("   " + spe.getMessage());

      // Use the contained exception, if any
      Exception x = spe;

      if (spe.getException() != null) {
        x = spe.getException();
      }

      x.printStackTrace();
    } catch (final SAXException sxe) {
      // Error generated during parsing)
      Exception x = sxe;

      if (sxe.getException() != null) {
        x = sxe.getException();
      }

      x.printStackTrace();
    } catch (final ParserConfigurationException pce) {
      // Parser with specified options can't be built
      pce.printStackTrace();
    } catch (final IOException ioe) {
      // I/O error
      ioe.printStackTrace();
    }
    return null;
  }
}
