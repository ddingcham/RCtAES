package example.model;

import example.model.supports.Snippet;
import example.model.supports.Statistics;
import lombok.Builder;
import lombok.Getter;

public class YoutubeRecord {

    @Getter
    private int id;
    @Getter
    private Statistics statistics;

    @Builder
    private YoutubeRecord(int id, int viewCount) {
        this.id = id;
        this.statistics = new Statistics(viewCount);
    }

    public Snippet getSnippet() {
        return new Snippet();
    }
}
