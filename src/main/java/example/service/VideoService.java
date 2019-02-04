package example.service;

import example.api.Client;
import example.api.supports.Parameters;
import example.api.Request;
import example.api.Response;
import example.model.*;
import example.supports.File;
import example.api.supports.GoogleAuthorizer;
import example.supports.JSON;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;

/*
    The example code's job is to read some data about videos from a JSON file,
    enrich it with data from Youtube, calculate some simple further data,
    and then return the data in JSON.
 */
public class VideoService {
    public String getVideos() {
        Videos videos = readVideoMeta();
        YoutubeIDs youtubeIDs = videos.getVideoIDs();
        Response response = callYoutube(youtubeIDs);

        Arrays.stream(youtubeIDs.toArray())
                .forEach(youtubeID -> {
                    Video video = videos.findById(youtubeID);
                    YoutubeRecord youtubeRecord = response.findById(youtubeID);
                    //TODO -> viewCount
                    video.setViewCount(youtubeRecord.getStatistics().getViewCount().toInteger());
                    long daysAvailable = calculateDaysAvailable(youtubeRecord.getSnippet().getPublishedAt());
                    video.setMonthlyViewCount(video.getViewCount() * 365.0 / daysAvailable / 12);
                });

        return JSON.dump(videos);
    }

    public Videos readVideoMeta() {
        return JSON.parseToVideos(File.read("videos.json"));
    }

    public long calculateDaysAvailable(TemporalAmount publishedAt) {
        return LocalDate.now().minus(publishedAt).toEpochDay();
    }

    public Response callYoutube(YoutubeIDs youtubeIDs) {
        Client client = GoogleAuthorizer
                .builder()
                .tokenKey("api-youtube")
                .applicationName("Gateway Youtube Example")
                .applicationVersion("0.1")
                .build();
        Youtube youtube = client.discoveredApi("youtube", "v3");
        Request request = Request
                .builder()
                .apiMethod(youtube.getVideoList())
                .parameters(Parameters
                        .with("id", youtubeIDs.join(","))
                        .with("part", "snippet, contentDetails, statistics")
                        .build())
                .build();

        return JSON.parseToResponse(client.execute(request).getBody());
    }
}
