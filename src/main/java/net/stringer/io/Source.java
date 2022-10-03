package net.stringer.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

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
        InputStream stream;
        try {
            stream = getFromURL(new URL(argument).toURI());
        } catch (MalformedURLException | URISyntaxException e) {
            stream = getFromFile(argument);
        }
        inputStream = stream;
    }

    public void closeInputSteam() {
        try {
            inputStream.close();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private InputStream getFromFile(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException fne) {
            log.error("File {} not found", fileName);
            throw new RuntimeException(fne);
        }
    }

    private InputStream getFromURL(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<InputStream> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException ioe) {
            log.error("IOException when executing {}", uri);
            throw new RuntimeException(ioe);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
