package UnitTest;

import Util.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPropertyReader {

  @Test
  public void testGetBaseUlrMethod() {
    String expectedUrl = "";
    String actualUrl = ConfigLoader.getInstance().getBaseUrl();
    Assert.assertEquals(expectedUrl, actualUrl, "url is not matching to expected url");
  }

  @Test
  public void testGetBaseUlrMethodWithInvalidInput()  {

    try {
      String actualUrl = ConfigLoader.getInstance().getBaseUrl("as");
    } catch (Exception e) {
      Assert.assertEquals(e.getMessage(),"couldn't fetch url");
    }
  }
}
