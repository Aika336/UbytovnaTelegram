package core;

import java.io.IOException;

public class UbytovanieStatement {
    private Login user;
    private OutputStreamDocument outDoc;

    public UbytovanieStatement(String loginName, String password, String old) {
        try {
            user = new Login(loginName, password, old);
            outDoc = new OutputStreamDocument(user);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isStatementPodana() {
        if (outDoc == null) {
            System.out.println("isStatementPodana: outDoc is null, login failed during init");
            return false;
        }

        try {
            return ConditionOfSatement
                    .get(outDoc.getDocument("https://ubytovanie.stuba.sk/new/sk/zoznamy/fei"), "status_1_right")
                    .contains("podaná");
        } catch (IOException e) {
            System.out.println("isStatementPodana error: " + e.getMessage());
            return false;
        }
    }
}
