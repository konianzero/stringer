package net.stringer.feed.rss;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static net.stringer.util.Util.toZonedDateTime;

/**
 *
 * RSS channel element.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public class Channel {
    // *** Required channel elements ***
    /**
     * The name of the channel.
     */
    private final String title;
    /**
     * The URL to the HTML website corresponding to the channel.
     */
    private final String link;
    /**
     * Phrase or sentence describing the channel.
     */
    private final String description;

    // *** Optional channel elements ***
    /**
     * The last time the content of the channel changed.
     */
    private final ZonedDateTime lastBuildDate;
    /**
     * The publication date for the content in the channel.
     */
    private final ZonedDateTime pubDate;

    private final List<Item> items = new ArrayList<>();

    /**
     * Channel builder.
     */
    public static class ChannelBuilder {
        private String title;
        private String link;
        private String description;
        private ZonedDateTime lastBuildDate;
        private ZonedDateTime pubDate;

        public ChannelBuilder(String title, String link, String description) {
            this.title = title;
            this.link = link;
            this.description = description;
        }

        public ChannelBuilder lastBuildDate(String lastBuildDate) {
            this.lastBuildDate = toZonedDateTime(lastBuildDate);
            return this;
        }

        public ChannelBuilder pubDate(String pubDate) {
            this.pubDate = toZonedDateTime(pubDate);
            return this;
        }

        public Channel build() {
            return new Channel(this);
        }
    }

    private Channel(ChannelBuilder builder) {
        this.title = builder.title;
        this.link = builder.link;
        this.description = builder.description;
        this.lastBuildDate = builder.lastBuildDate;
        this.pubDate = builder.pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getLastBuildDate() {
        return lastBuildDate;
    }

    public ZonedDateTime getPubDate() {
        return pubDate;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
