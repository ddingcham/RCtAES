package example.model;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class YoutubeRecordTest {

    // TODO viewCount 하나만 이슈 인 듯 (스펙 용도로 남길 이유가 잇나)
    @Test
    public void generate_default_value() {
        int id = 1;
        YoutubeRecord youtubeRecord = YoutubeRecord
                .builder()
                .id(id)
                .viewCount(0)
                .dateTime("19930301")
                .formatter("yyyyMMdd")
                .build();

        assertThat(youtubeRecord).isEqualTo(defaultYoutubeRecord(id));
    }

    @Test
    public void generate_illegal_arguments() {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThatThrownBy(
                () -> YoutubeRecord.builder()
                        .build())
                .isInstanceOf(IllegalArgumentException.class);

        softly.assertThatThrownBy(() -> defaultYoutubeRecord(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private YoutubeRecord defaultYoutubeRecord(int id) {
        return YoutubeRecord
                .builder()
                .id(id)
                .dateTime("19930301")
                .formatter("yyyyMMdd")
                .build();
    }
}
