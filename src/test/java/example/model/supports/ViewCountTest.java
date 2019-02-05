package example.model.supports;

import org.junit.Test;

public class ViewCountTest {

    @Test(expected = IllegalArgumentException.class)
    public void generate_illegal_arguments() {
        ViewCount.of(-1);
    }

}
