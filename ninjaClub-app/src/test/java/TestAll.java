import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ninjaclub.dashboard.model.PlayerTest;
import com.ninjaclub.dashboard.util.PlayerUtilTest;

@RunWith(Suite.class)
@SuiteClasses({PlayerTest.class, PlayerUtilTest.class})
public class TestAll {

}
