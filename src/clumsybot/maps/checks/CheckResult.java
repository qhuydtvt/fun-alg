package clumsybot.maps.checks;

/**
 * Created by huynq on 2/2/18.
 */
public class CheckResult {
    public boolean pass;
    public String message;

    public CheckResult(boolean pass, String message) {
        this.pass = pass;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "pass=" + pass +
                ", message='" + message + '\'' +
                '}';
    }
}
