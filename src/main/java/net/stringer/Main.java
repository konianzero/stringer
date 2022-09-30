package net.stringer;

import net.stringer.io.Source;
import net.stringer.parser.FeedParser;
import net.stringer.parser.RSSFeedParser;
import net.stringer.view.console.ConsoleView;

/**
 *
 * Main class.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.0.1
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Source source = new Source(args[0]);
            FeedParser parser = new RSSFeedParser(source.inputStream());
            new ConsoleView(parser.parse());

            source.closeInputSteam();
        } else {
            System.out.println("No xml file to read!");
        }
    }
}