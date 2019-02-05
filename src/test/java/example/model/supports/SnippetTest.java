package example.model.supports;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class SnippetTest {

    @Test
    public void generate_illegal_arguments() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThatThrownBy(() -> Snippet.of(null, null))
                .describedAs("NPE case")
                .isInstanceOf(IllegalArgumentException.class);
        softly.assertThatThrownBy(() -> Snippet.of("a", "a"))
                .describedAs("DateTimeParseException case")
                .isInstanceOf(IllegalArgumentException.class);

        softly.assertAll();
    }

}
