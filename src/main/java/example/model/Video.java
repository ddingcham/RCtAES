package example.model;

import example.model.supports.ViewCount;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public class Video {

    @Getter
    private int id;

    private ViewCount viewCount;

    @Getter @Setter
    private double monthlyViewCount;

    @Builder
    public Video(int id, int viewCount, double monthlyViewCount) {
        if(id <= 0) {
            throw new IDException();
        }
        this.viewCount = ViewCount.of(viewCount);
        this.monthlyViewCount = monthlyViewCount;
    }

    public double calculateMonthlyViewCount(LocalDate publishedAt) {
        return getViewCount() * 365.0 / calculateDaysAvailable(publishedAt) / 12;
    }

    public int getViewCount() {
        return viewCount.toInt();
    }

    public void setViewCount(int viewCount) {
        this.viewCount = ViewCount.of(viewCount);
    }

    private long calculateDaysAvailable(LocalDate publishedAt) {
        return LocalDate.now().minusDays(publishedAt.toEpochDay()).toEpochDay();
    }
}
