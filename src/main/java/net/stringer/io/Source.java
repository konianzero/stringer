package net.stringer.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 *
 * RSS source.
 *
 * @version 0.1.0
 * @author @see <a href="https://github.com/konianzero">konianzero</a>
 * @since 0.1.0
 */
public class Source {
    private final Logger log = LoggerFactory.getLogger(Source.class);
    private final InputStream inputStream;

    public InputStream inputStream() {
        return inputStream;
    }

    public Source(String argument) {
        try {
            inputStream = new FileInputStream(argument);
        } catch (FileNotFoundException fne) {
            log.error("File {} not found", argument);
            throw new RuntimeException(fne);
        }
    }

    public void closeInputSteam() {
        try {
            inputStream.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
