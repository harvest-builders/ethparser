package pro.belbix.ethparser;

import static org.junit.Assert.assertEquals;
import static pro.belbix.ethparser.utils.CommonUtils.aprToApy;

import org.junit.jupiter.api.Test;

public class CommonTests {

  @Test
  public void testAprToApy() {
    double apr = 74.3;
    double period = 365.0;
    assertEquals(110.06457410361162, aprToApy(apr, period), 0.0);
  }
}
