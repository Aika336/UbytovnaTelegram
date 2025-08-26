package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class OutputStreamDocument {
    private Login user;
    public OutputStreamDocument(Login user) {
        this.user = user;
    }

    public Document getDocument(String dataFrom) throws IOException {
        return Jsoup.connect(dataFrom)
                .cookies(user.getCookies()).get();
    }
}
