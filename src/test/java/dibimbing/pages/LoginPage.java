package dibimbing.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LoginPage extends BasePage {
  public LoginPage(AndroidDriver driver) {
    super(driver);
  }

  @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
  private WebElement nameET;

  @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
  private WebElement passwordET;

  @AndroidFindBy(accessibility = "Tap to login with given credentials")
  private WebElement loginButton;

  @AndroidFindBy(accessibility = "Displays number of items in your cart")
  private WebElement cartButton;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"© 2023 Sauce Labs All Rights Reserved. Terms of Service | Privacy Policy\"]")
  private WebElement footer;

  public void login(String username, String password) {
    nameET.sendKeys(username);
    passwordET.sendKeys(password);
    loginButton.click();
    clickChartByCoordinate();
  }

  public void clickChartByCoordinate() {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);

    tap.addAction(finger.createPointerMove(
            Duration.ofMillis(0),
            PointerInput.Origin.viewport(), 993, 205
        )
    );
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(List.of(tap));
  }

  public void getCoordinateFromElement() {
    Point point = cartButton.getLocation();
    System.out.println("Point: " + point);
    System.out.println("X: " + point.getX());
    System.out.println("Y: " + point.getY());
  }

  public void swipeUp() {
    Dimension size = driver.manage().window().getSize();
    System.out.println("Size: " + size);
    System.out.println("Width: " + size.getWidth());
    System.out.println("Height: " + size.getHeight());

    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endY = (int) (size.getHeight() * 0.2);

    System.out.println("StartX: " + startX);
    System.out.println("StartY: " + startY);
    System.out.println("EndY: " + endY);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);

    swipe.addAction(finger.createPointerMove(
            Duration.ofMillis(0),
            PointerInput.Origin.viewport(), startX, startY
        )
    );
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(
            Duration.ofMillis(100),
            PointerInput.Origin.viewport(), startX, endY
        )
    );
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(List.of(swipe));
  }

  public boolean checkFooter() {
    int maxSwipe = 5;
    for (int i = 0; i < maxSwipe; i++) {
      swipeUp();
    }

    return footer.isDisplayed();
  }
}
