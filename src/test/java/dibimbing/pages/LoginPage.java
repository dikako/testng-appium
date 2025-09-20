package dibimbing.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

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

  public void login(String username, String password) {
    nameET.sendKeys(username);
    passwordET.sendKeys(password);
    loginButton.click();
  }
}
