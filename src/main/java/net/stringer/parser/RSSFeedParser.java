package net.stringer.parser;

import net.stringer.feed.rss.Channel;
import net.stringer.feed.rss.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 *
 * RSS feed parser.
 * Parse RSS from {@code inputStream}.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public class RSSFeedParser extends AbstractFeedParser {

    private final Logger log = LoggerFactory.getLogger(RSSFeedParser.class);

    private static final String CHANNEL = "channel";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String ITEM = "item";
    private static final String PUB_DATE = "pubDate";
    private static final String LAST_PUB_DATE = "lastBuildDate";

    public RSSFeedParser(InputStream inputStream) {
        super(inputStream);
    }

    public Channel parse() {
        XMLEvent event = null;
        Channel channel = null;
        boolean isFeedHeader = true;

        String description = "";
        String title = "";
        String link = "";
        String lastBuildDate = "";
        String pubDate = "";

        while (xmlEventReader.hasNext()) {
            try {
                event = xmlEventReader.nextEvent();
                if (event.isStartElement()) {
                    switch (event.asStartElement().getName().getLocalPart()) {
                        case CHANNEL:
                            break;
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                Channel.ChannelBuilder channelBuilder = new Channel.ChannelBuilder(title, link, description);
                                channelBuilder.pubDate(pubDate);
                                channelBuilder.lastBuildDate(lastBuildDate);
                                channel = channelBuilder.build();
                            }
                            break;
                        case TITLE:
                            title = getCharacterData(xmlEventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(xmlEventReader);
                            break;
                        case LINK:
                            link = getCharacterData(xmlEventReader);
                            break;
                        case PUB_DATE:
                            pubDate = getCharacterData(xmlEventReader);
                            break;
                        case LAST_PUB_DATE:
                            lastBuildDate = getCharacterData(xmlEventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                        Item.ItemBuilder itemBuilder = new Item.ItemBuilder(title, link, description);
                        itemBuilder.pubDate(pubDate);
                        channel.addItem(itemBuilder.build());
                    }
                }

            } catch (XMLStreamException xmlse) {
                log.error("Unexpected error while parsing {}", event);
                throw new RuntimeException(xmlse);
            }
        }
        closeEventReader();
        return channel;
    }

    private String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event.isCharacters()) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}
