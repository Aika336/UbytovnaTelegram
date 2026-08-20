package core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RegularCheck {
    public static ScheduledExecutorService SCEDULER = Executors.newScheduledThreadPool(1);
    private UbytovanieStatement info;
    private Runnable task;

    public RegularCheck(String loginName, String password, String old) {
        info = new UbytovanieStatement(loginName, password, old);
        task = () -> {
            if(info.isStatementPodana()) {
                System.out.println("ok");
            }else {
                System.out.println("fail");
            }
        };
    }

    public Runnable getTask() {
        return task;
    }
}
