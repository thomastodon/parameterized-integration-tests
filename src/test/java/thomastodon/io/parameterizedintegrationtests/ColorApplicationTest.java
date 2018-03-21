package thomastodon.io.parameterizedintegrationtests;

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

    @ParameterizedTest
    @CsvSource({"red, yellow", "blue, green"})
    void contextLoads(String colorOne, String colorTwo) {
        assertThat(colorService.add(colorOne, colorTwo)).isEqualTo("purple");
    }
}