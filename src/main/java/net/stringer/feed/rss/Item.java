package net.stringer.feed.rss;

/**
 *
 * RSS item element.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public class Item {
    // *** Required item elements ***
    /**
     * The title of the item.
     */
    private final String title;
    /**
     * The URL of the item.
     */
    private final String link;
    /**
     * The item synopsis.
     */
    private final String description;

    // *** Optional item elements ***

    /**
     * Indicates when the item was published.
     */
    private final String pubDate;

    /**
     * Item builder.
     */
    public static class ItemBuilder {
        private String title;
        private String link;
        private String description;
        private String pubDate;

        public ItemBuilder(String title, String link, String description) {
            this.title = title;
            this.link = link;
            this.description = description;
        }

        public ItemBuilder pubDate(String pubDate) {
            this.pubDate = pubDate;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    private Item(ItemBuilder builder) {
        this.title = builder.title;
        this.link = builder.link;
        this.description = builder.description;
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

    public String getPubDate() {
        return pubDate;
    }
}
