package example.model;

import example.model.supports.Snippet;
import example.model.supports.Statistics;
import lombok.*;

@EqualsAndHashCode
@ToString
public class YoutubeRecord {

    @Getter
    private int id;
    @Getter
    private Statistics statistics;
    @Getter
    private Snippet snippet;

    @Builder
    private static YoutubeRecord of(int id, int viewCount, String dateTime, String formatter) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be defined as positive");
        }
        return new YoutubeRecord(id, viewCount, dateTime, formatter);
    }

    private YoutubeRecord(int id, int viewCount, String dateTime, String formatter) {
        this.id = id;
        this.statistics = Statistics.of(viewCount);
        this.snippet = Snippet.of(dateTime, formatter);
    }
}
