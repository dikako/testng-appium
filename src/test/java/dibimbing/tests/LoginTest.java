package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.GlobalPage;
import dibimbing.pages.LoginPage;
import dibimbing.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
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
}
