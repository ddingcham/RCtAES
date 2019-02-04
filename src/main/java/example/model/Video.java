package example.model;

import lombok.*;

@EqualsAndHashCode
@ToString
public class Video {

    @Getter
    private int id;
    @Getter @Setter
    private int viewCount;
    @Getter @Setter
    private double monthlyViewCount;

    @Builder
    public Video(int id, int viewCount, double monthlyViewCount) {
        this.id = id;
        this.viewCount = viewCount;
        this.monthlyViewCount = monthlyViewCount;
    }

}
