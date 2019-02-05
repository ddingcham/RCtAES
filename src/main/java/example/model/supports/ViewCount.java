package example.model.supports;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class ViewCount {

    private int primitiveViewCount;

    public static ViewCount of(int viewCount) {
        if(viewCount < 0) {
            throw new IllegalArgumentException("view count can't be negative");
        }
        return new ViewCount(viewCount);
    }

    public int toInt() {
        return primitiveViewCount;
    }
}
