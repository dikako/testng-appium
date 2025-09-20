package dibimbing.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
  private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

  public static void initDriver() {
    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")
        .setApp(System.getProperty("user.dir") + "/apk/demo.apk")
        .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
        .autoGrantPermissions();

    try {
      String appiumUrl = System.getProperty("appium.server.url", "http://127.0.0.1:4723");
      URL appiumServerUrl = new URL(appiumUrl);
      driver.set(new AndroidDriver(appiumServerUrl, options));
      driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public static AndroidDriver getDriver() {
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.remove();
    }
  }
}
