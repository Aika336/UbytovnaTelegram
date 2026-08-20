package core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Map;

public class Login {
    private static final String COOKIE_FILE = "cookies.ser";
    private Map<String, String> cookies;

    public Login(String loginName, String password, String old) throws IOException, ClassNotFoundException {
        try {
            loadCookies();
            if(!areCookiesValid()) {
                System.out.println("Invalid cookies");
                loginAndSave("https://ubytovanie.stuba.sk/new/sk/informacie/", loginName, password, old);
                System.out.println("Now the cookies are available");
            }else {
                System.out.println("Cookies are working!");
            }
        } catch(Exception e) {
            System.out.println("Invalid cookies(Exception)");
            try {
                loginAndSave("https://ubytovanie.stuba.sk/new/sk/informacie/", loginName, password, old);
            } catch(IOException ex) {
                System.out.println("Login failed completely: " + ex.getMessage());
                cookies = new java.util.HashMap<>(); // пустые куки вместо null → избегаем NPE
            }
        }
    }

    private boolean areCookiesValid() {
        try {
            OutputStreamDocument doc = new OutputStreamDocument(this);
            return ConditionOfSatement
                    .get(doc.getDocument("https://ubytovanie.stuba.sk/new/sk/zoznamy/fei"), "status_1_right")
                    .length() > 0;
        } catch (IOException e) {
            return false;
        }
    }

    private void loadCookies() throws IOException, ClassNotFoundException {
        File file = new File(COOKIE_FILE);
        if(file.exists()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                cookies = (Map<String, String>) ois.readObject();
                System.out.println("Cookies was load from a file.");
            }
        }
    }

    private void loginAndSave(String login, String loginName, String password, String old)
            throws IOException {
        Connection.Response loginResponse = Jsoup.connect(login)
                .data("remember", "true")
                .data("anyid", loginName)
                .data("password", password)
                .data("acad_year", old)
                .data("login", "true")
                .method(Connection.Method.POST)
                .execute();

        cookies = loginResponse.cookies();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIE_FILE))) {
            oos.writeObject(cookies);
            System.out.println("Cookies was import to a file.");
        }
    }

    public Map<String, String> getCookies() {
        return cookies;
    }
}