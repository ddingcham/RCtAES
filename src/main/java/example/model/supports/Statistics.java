package example.model.supports;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Statistics {

    private ViewCount viewCount;

    public static Statistics of(int viewCount) {
        return new Statistics(ViewCount.of(viewCount));
    }

    public int getViewCount() {
        return viewCount.toInt();
    }
}
