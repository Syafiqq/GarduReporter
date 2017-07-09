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

        this.networking.setDomain("https://gardureporter.000webhostapp.com");
        this.networking.setGuard("cca0f1b5701d4f00dc3729b83b7000da");
        this.networking.setCertificate("edd22733c75459253d27126e8d9f628baae653b36cd2e86ac81776580a41645407ee6fc5219c675cd3a4ba1bdab8401376f6909fbcb7e5e22b07fa8480430f45");
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
        private String guard;
        private String certificate;

        public String getDomain()
        {
            return domain;
        }

        public void setDomain(String domain)
        {
            this.domain = domain;
        }

        public String getGuard()
        {
            return this.guard;
        }

        public void setGuard(String guard)
        {
            this.guard = guard;
        }

        public String getCertificate()
        {
            return this.certificate;
        }

        public void setCertificate(String certificate)
        {
            this.certificate = certificate;
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
