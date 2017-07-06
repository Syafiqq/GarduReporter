package app.freelancer.syafiqq.gardureporter.model.util;

/**
 * Created by syafiq on 6/23/17.
 */

public class Setting {
    private static final Setting ourInstance = new Setting();

    private Networking networking;

    static Setting getInstance() {
        return ourInstance;
    }

    private Setting() {
        this.networking = new Networking();

        this.networking.setDomain("http://freelancer.gardu.reporter.app");
    }

    public static Setting getOurInstance() {
        return ourInstance;
    }

    public Networking getNetworking() {
        return networking;
    }

    public static class Networking {
        private String domain;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }
}
