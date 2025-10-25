package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.GlobalPage;
import dibimbing.pages.LoginPage;
import dibimbing.pages.ProductPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
  private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

  @Test
  public void testSuccessfulLogin() {
    GlobalPage globalPage = new GlobalPage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    ProductPage productPage = new ProductPage(DriverManager.getDriver());

    globalPage.clickViewMenu();
    globalPage.clickLoginMenu();
    loginPage.login("bod@example.com", "10203040");
    Assert.assertTrue(productPage.isProductTitleDisplayed(), "Product title is not displayed");
  }

  @Test
  public void testTapByCoordinate() {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    loginPage.clickChartByCoordinate();
  }

  @Test
  public void testGetCoordinateFormElement() {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    loginPage.getCoordinateFromElement();
  }

  @Test
  public void testSwipeUp() {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    loginPage.swipeUp();
  }

  @Test
  public void testCheckFooter() {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    Assert.assertTrue(loginPage.checkFooter());
  }

  @Test
  public void testProductDetail() {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    loginPage.checkDetailsVioletBackpack();
  }
}
