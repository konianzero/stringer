package net.stringer.view.console;

import net.stringer.feed.rss.Channel;

import static net.stringer.util.Util.formatDateTime;

/**
 *
 * Show RSS feeds in terminal.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public class ConsoleView {
    public ConsoleView(Channel channel) {
        System.out.println("Channel: " + channel.getTitle() +
                "\nlink: " + channel.getLink() +
                "\ndescription: " + channel.getDescription() +
                "\nlastBuildDate: " + formatDateTime(channel.getLastBuildDate()) +
                "\npubDate: " + formatDateTime(channel.getPubDate())
        );
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++\n");
        channel.getItems().forEach(item -> {
            System.out.println("Item: " + item.getTitle() +
                    "\nlink: " + item.getLink().replaceAll("[\\s]{2,}", " ") +
                    "\ndescription: " + item.getDescription().replaceAll("[\\s]{2,}", " ") +
                    "\npubDate: " + formatDateTime(item.getPubDate())
            );
            System.out.println("\n_______________________________________________\n");
        });
    }
}
