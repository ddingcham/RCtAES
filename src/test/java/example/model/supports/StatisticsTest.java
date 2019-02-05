package example.model.supports;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatisticsTest {

    @Test
    public void can_compare_with_primitive() {
        int viewCount = 3;
        Statistics statistics = Statistics.of(viewCount);
        int actual = statistics.getViewCount();

        assertThat(actual == viewCount).isTrue();

        actual = 5;

        assertThat(actual != statistics.getViewCount()).isTrue();
    }
}
