package core;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ConditionOfSatement {
    public static String get(Document document, String elemet) {
        Element span = document.getElementById(elemet);
        return span != null ? span.text() : "";
    }
}
