package thomastodon.io.parameterizedintegrationtests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(JUnitParamsRunner.class)
public class ColorApplicationTest {

    @Rule public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @ClassRule public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Autowired ColorService colorService;

    @Test
    @Parameters({
        "red, yellow",
        "blue, green",
    })
    public void contextLoads(String colorOne, String colorTwo) {
        assertThat(colorService.add(colorOne, colorTwo)).isEqualTo("purple");
    }
}