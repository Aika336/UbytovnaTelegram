package core;

import java.io.IOException;

public class UbytovanieStatement {
    private Login user;
    private OutputStreamDocument outDoc;

    public UbytovanieStatement() {
        try {
            user = new Login("https://ubytovanie.stuba.sk/new/sk/informacie/");
            outDoc = new OutputStreamDocument(user);
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isStatementPodana() {
        try {
            return ConditionOfSatement
                    .get(outDoc.getDocument("https://ubytovanie.stuba.sk/new/sk/zoznamy/fei"), "status_1_right")
                    .equals("podaná: FEI");
        } catch (IOException e) {
            return false;
        }
    }
}
