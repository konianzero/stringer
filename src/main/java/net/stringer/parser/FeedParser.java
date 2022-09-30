package net.stringer.parser;

import net.stringer.feed.rss.Channel;

/**
 *
 * Interface of feed parser.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public interface FeedParser {
    Channel parse();
}
