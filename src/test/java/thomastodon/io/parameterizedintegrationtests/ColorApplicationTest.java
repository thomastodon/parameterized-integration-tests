package thomastodon.io.parameterizedintegrationtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ColorApplicationTest {

    @Autowired ColorService colorService;

    @DisplayName("adding colors")
    @CsvSource({"red, yellow, orange", "blue, red, purple"})
    @ParameterizedTest(name = "adding {0} and {1} returns {2}")
    void test(String colorOne, String colorTwo, String colorThree) {
        assertThat(colorService.add(colorOne, colorTwo)).isEqualTo(colorThree);
    }
}