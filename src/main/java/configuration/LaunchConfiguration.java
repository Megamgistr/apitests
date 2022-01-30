package configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class LaunchConfiguration {
    private static final Config CONFIG = ConfigFactory.parseResources("configs/config.conf").resolve();
    public static final String SCHEME;
    public static final String BASE_HOST;
    public static final String BASE_URL;

    static {
        SCHEME = CONFIG.getString("api.scheme");
        BASE_HOST = CONFIG.getString("api.host");
        BASE_URL = String.format("%1$s://%2$s", SCHEME, BASE_HOST);
    }
}
