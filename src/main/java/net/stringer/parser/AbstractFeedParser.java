package net.stringer.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

/**
 *
 * Abstract feed parser.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public abstract class AbstractFeedParser implements FeedParser {

    private final Logger log = LoggerFactory.getLogger(AbstractFeedParser.class);

    protected final XMLEventReader xmlEventReader;

    protected AbstractFeedParser(InputStream inputStream) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
        } catch (XMLStreamException xmlse) {
            log.error("Unexpected error while creating XML event reader");
            throw new RuntimeException(xmlse);
        }
    }

    protected void closeEventReader() {
        try {
            xmlEventReader.close();
        } catch (XMLStreamException xmlse) {
            throw new RuntimeException(xmlse);
        }
    }
}
