package dibimbing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class HelloAppiumTest {
  private AndroidDriver driver;

  @BeforeClass
  public void setUp() {
    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")
        .setApp(System.getProperty("user.dir") + "/apk/demo.apk")
        .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
        .autoGrantPermissions();

    options.setChromedriverExecutable(System.getProperty("user.dir") + "/webdriver/chromedriver");

    try {
      URL appiumServerUrl = new URL("http://127.0.0.1:4723");
      driver = new AndroidDriver(appiumServerUrl, options);
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHelloAppium() {
    assert driver.getSessionId() != null;
    System.out.println("Hello Appium! " + driver.getSessionId());

    WebElement el1 = driver.findElement(AppiumBy.accessibilityId("View menu"));
    el1.click();

    WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"WebView\")"));
    el2.click();

    driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlET")).sendKeys("google.com");
    driver.findElement(AppiumBy.accessibilityId("Tap to view content of given url")).click();

    // Static wait (not recommended in real project!!!)
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    Set<String> listContexts = driver.getContextHandles();

    System.out.println("List Contexts: " + listContexts);

    // Switch to webview context
    for (String context : listContexts) {
      if (context.contains("WEBVIEW_")) {
        driver.context(context);
      }
    }

    String webviewTitle = driver.getTitle();
    System.out.println("Title: " + webviewTitle);

    driver.context("NATIVE_APP");
  }

  @Test
  public void tapDrawing() {

    driver.findElement(AppiumBy.accessibilityId("View menu")).click();
    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Drawing\"]")).click();

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    Dimension size = driver.manage().window().getSize();

    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endY = (int) (size.getHeight() * 0.2);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX-2, endY));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX-4, endY));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX-6, endY));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Collections.singleton(swipe));
//    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//    Sequence tap = new Sequence(finger, 1);
//
//    tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 1005, 200));
//    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//    driver.perform(Arrays.asList(tap));
  }

  @Test
  public void tapByCoordinates() {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);

    tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 1005, 200));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(tap));
  }

  @Test
  public void testSwipingDownUsingUiScrollable() {
    driver.findElement(AppiumBy.androidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"Sauce Labs Bike Light\"))"
    ));
  }

  @Test
  public void testSwipingDown() {
    Dimension size = driver.manage().window().getSize();

    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endY = (int) (size.getHeight() * 0.2);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Collections.singleton(swipe));
  }

  @Test
  public void testSwipingUp() {
    testSwipingDown();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    Dimension size = driver.manage().window().getSize();

    System.out.println("Size: " + size);
    System.out.println("Width: " + size.getWidth());
    System.out.println("Height: " + size.getHeight());

    int startX = size.getWidth() / 2;
    System.out.println("StartX: " + startX);
    int startY = size.getHeight() / 2;
    System.out.println("StartY: " + startY);
    int endY = (int) (size.getHeight() * 0.8);
    System.out.println("EndY: " + endY);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);
    // Initiate tap screen
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

    // Do tap and hold and swipe
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    // Perform actionnya
    driver.perform(Collections.singleton(swipe));

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
