package example.model;

import org.junit.Test;

public class YoutubeTest {

    @Test(expected = IllegalArgumentException.class)
    public void generate_illegal_arguments() {
        new Youtube(null);
    }
}
