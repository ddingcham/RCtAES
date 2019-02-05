package example.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class YoutubeIDsTest {

    private Integer[] ids;
    private YoutubeIDs youtubeIDs;

    @Before
    public void setUp() {
        ids = new Integer[]{1,2,3};
        youtubeIDs = new YoutubeIDs(new HashSet<>(Arrays.asList(ids)));
    }

    @Test
    public void join() {
        String expected = "1,2,3";

        assertThat(youtubeIDs.join(","))
                .isEqualTo(expected);
    }

    @Test
    public void toArray() {
        assertThat(youtubeIDs.toArray()).isEqualTo(ids);
    }
}
