package utilities;

import static utilities.Constants.LANGUAGE_REGEX;

import java.util.Map;
import java.util.Objects;

public class LocalEnviroment {

  public static String getPlatform() {
    return System.getenv("Platform");
  }

  public static String getApplication() {
    return Objects.nonNull(System.getenv("Application")) ? System.getenv("Application").toLowerCase() : "";
  }

  public static String getBrowser() {
    if (Objects.nonNull(System.getenv("Browser"))) {
      return System.getenv("Browser").toLowerCase();
    } else {
      return "chrome";
    }
  }

  public static String getResolution() {
    return System.getenv("Resolution");
  }

  public static String getUdid() {
    return System.getenv("Udid");
  }

  public static String getApk() {
    return System.getenv("Apk");
  }

  public static String getAppPackage() {
    return System.getenv("AppPackage");
  }

  public static String getAppActivity() {
    return System.getenv("AppActivity");
  }

  public static boolean getAccessibility() {
    return Objects.nonNull(System.getenv("Accessibility"))
        && System.getenv("Accessibility").equalsIgnoreCase("true");
  }

  public static boolean isMobile() {
    String platform = System.getenv("Platform");
    return Objects.nonNull(platform) && platform.equalsIgnoreCase("Android")
        || platform.equalsIgnoreCase("IOS");
  }

  public static String getApplicationUrl() throws IllegalArgumentException {
    Map<String, Map<String, String>> environment = DriverConfiguration.loadCapabilitiesWeb();
    Map<String, String> urls = environment.get("url");
    String url = null;

    if (!urls.containsKey(getApplication()) || getApplication().isBlank()) {
      throw new IllegalArgumentException("Application not found");
    }

    for (Map.Entry<String, String> entry : urls.entrySet()) {
      String application = entry.getKey().toLowerCase();
      String applicationUrl = entry.getValue();
      if (Objects.equals(application, getApplication())) {
        url = applicationUrl;
        break;
      }
    }
    return url;
  }

  public static String getLanguage() {
    String language = System.getenv("Language");
    if (Objects.isNull(language) || language.isEmpty()) {
      throw new IllegalArgumentException("Language environment variable not found");
    }
    if (!language.matches(LANGUAGE_REGEX)) {
      throw new IllegalArgumentException("Invalid language format. It should be xx-XX");
    }
    return language;
  }

  public static String getLanguageCode() {
    return getLanguage().split("-")[0];
  }

  public static String getCountryCode() {
    return getLanguage().split("-")[1];
  }
}
