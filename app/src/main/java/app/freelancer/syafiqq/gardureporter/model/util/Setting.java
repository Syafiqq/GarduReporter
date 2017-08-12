package app.freelancer.syafiqq.gardureporter.model.util;

/*
 * This <GarduReporter> created by :
 * Name         : syafiq
 * Date / Time  : 07 July 2017, 8:14 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.dao.TokenDao;
import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class Setting
{
    private static final Setting ourInstance = new Setting();

    private Networking networking;

    private Setting()
    {
        this.networking = new Networking();

        this.networking.setDomain("https://freelancer.gardu.reporter.app");
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

        public static SSLContext getSSLSocketFactory(Context context)
        {
            final String CLIENT_TRUST_PASSWORD = "ez24get";
            final String CLIENT_AGREEMENT = "TLS";
            final String CLIENT_TRUST_KEYSTORE = "BKS";
            SSLContext sslContext = null;
            try
            {
                sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
                TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                KeyStore tks = KeyStore.getInstance(CLIENT_TRUST_KEYSTORE);
                InputStream is;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                {
                    is = context.getResources().openRawResource(R.raw.freelancer_gardu_reporter_app_bks);
                }
                else
                {
                    is = context.getResources().openRawResource(R.raw.freelancer_gardu_reporter_app_v1_bks);
                }
                try
                {
                    tks.load(is, CLIENT_TRUST_PASSWORD.toCharArray());
                }
                finally
                {
                    is.close();
                }
                trustManager.init(tks);
                sslContext.init(null, trustManager.getTrustManagers(), null);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Timber.e("SslContextFactory", e.getMessage());
            }
            return sslContext;
        }

        public static HostnameVerifier getHostNameVerifier(String domain)
        {
            return new HostnameVerifier()
            {
                @Override public boolean verify(String host, SSLSession sslSession)
                {
                    boolean isVerified = false;
                    switch(host)
                    {
                        case "freelancer.gardu.reporter.app":
                        {
                            isVerified = true;
                            break;
                        }
                    }
                    return isVerified;
                }
            };
        }

        public static OkHttpClient getReservedClient(final Context context, final boolean needToken) throws NullPointerException
        {
            return new okhttp3.OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .sslSocketFactory(Setting.Networking.getSSLSocketFactory(context).getSocketFactory(), systemDefaultTrustManager())
                    .hostnameVerifier(Setting.Networking.getHostNameVerifier(Setting.getOurInstance().getNetworking().getDomain()))
                    .addInterceptor(new Interceptor()
                    {
                        @Override
                        public okhttp3.Response intercept(@NonNull Interceptor.Chain chain) throws IOException
                        {
                            Request original = chain.request();

                            Request.Builder builder = original.newBuilder()
                                                              .header("X-Requested-With", "XMLHttpRequest")
                                                              .header("X-Access-Permission", Setting.getOurInstance().getNetworking().getCertificate())
                                                              .header("X-Access-Guard", Setting.getOurInstance().getNetworking().getGuard())
                                                              .header("Content-Type", "application/json; charset=utf-8");
                            if(needToken)
                            {
                                @NotNull TokenOrm token = TokenDao.retrieveToken(context);
                                if(token.getToken() != null)
                                {
                                    builder = builder.header("X-Access-Token", token.getToken());
                                }
                            }

                            builder = builder.method(original.method(), original.body());

                            return chain.proceed(builder.build());
                        }
                    })
                    .build();
        }

        public static X509TrustManager systemDefaultTrustManager()
        {
            try
            {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                        TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if(trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager))
                {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                return (X509TrustManager) trustManagers[0];
            }
            catch(GeneralSecurityException e)
            {
                throw new AssertionError(); // The system has no TLS. Just give up.
            }
        }

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
        public static final int CLASS_GARDU_INDEX_INSERT = 0x01;
        public static final int CLASS_AUTH_LOGIN = 0x02;
        public static final int CLASS_GARDU_INDEX_MEASUREMENT = 0x03;
    }
}
