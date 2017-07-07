package app.freelancer.syafiqq.gardureporter.model.util;

/*
 * This <GarduReporter> created by :
 * Name         : syafiq
 * Date / Time  : 07 July 2017, 8:14 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Setting
{
    private static final Setting ourInstance = new Setting();

    private Networking networking;

    private Setting()
    {
        this.networking = new Networking();

        this.networking.setDomain("http://freelancer.gardu.reporter.app");
    }

    static Setting getInstance()
    {
        return ourInstance;
    }

    public static Setting getOurInstance()
    {
        return ourInstance;
    }

    public Networking getNetworking()
    {
        return networking;
    }

    public static class Networking
    {
        private String domain;

        public String getDomain()
        {
            return domain;
        }

        public void setDomain(String domain)
        {
            this.domain = domain;
        }
    }

    public static class SharedPreferences
    {
        public static final String SHARED_PREFERENCES_AUTHENTICATION = "SHARED_PREFERENCE_AUTHENTICATION";
    }

    public static class Jumper
    {
        public static final String NAME = "jumper";
        public static final int CLASS_DASHBOARD = 0x01;
        public static final int CLASS_AUTH_LOGIN = 0x02;
    }
}
