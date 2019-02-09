package example.service;

import example.external.YoutubeConnection;
import example.external.supports.Response;
import example.model.*;
import example.supports.FileUtils;
import example.supports.JSONUtils;

import java.time.LocalDate;
import java.util.Arrays;

/*
    The example code's job is to read some data about videos from a JSONUtils file,
    enrich it with data from Youtube, calculate some simple further data,
    and then return the data in JSONUtils.
 */
public class VideoService {
    public String getVideos() {
        Videos videos = readVideoMeta();
        YoutubeIDs youtubeIDs = videos.getVideoIDs();
        Response response = JSONUtils.parseToResponse(new YoutubeConnection().callYoutube(youtubeIDs));

        Arrays.stream(youtubeIDs.toArray())
                .forEach(youtubeID -> {
                    Video video = videos.findById(youtubeID);
                    YoutubeRecord youtubeRecord = response.findById(youtubeID);
                    video.setViewCount(youtubeRecord.getStatistics().getViewCount());
                    long daysAvailable = calculateDaysAvailable(youtubeRecord.getSnippet().getPublishedAt());
                    video.setMonthlyViewCount(video.getViewCount() * 365.0 / daysAvailable / 12);
                });

        return JSONUtils.dump(videos);
    }

    public Videos readVideoMeta() {
        return JSONUtils.parseToVideos(FileUtils.read("videos.json"));
    }

    public long calculateDaysAvailable(LocalDate publishedAt) {
        return LocalDate.now().minusDays(publishedAt.toEpochDay()).toEpochDay();
    }
}
