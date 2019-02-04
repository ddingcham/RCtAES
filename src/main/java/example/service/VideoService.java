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
import java.util.Arrays;

/*
    The example code's job is to read some data about videos from a JSON file,
    enrich it with data from Youtube, calculate some simple further data,
    and then return the data in JSON.
 */
public class VideoService {
    public String getVideos() {
        Videos videos = JSON.parseToVideos(File.read("videos.json"));
        YoutubeIDs youtubeIDs = videos.getVideoIDs();
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
        Response response = JSON.parseToResponse(client.execute(request).getBody());
        Arrays.stream(youtubeIDs.toArray())
                .forEach(youtubeID -> {
                    Video video = videos.findById(youtubeID);
                    YoutubeRecord youtubeRecord = response.findById(youtubeID);
                    video.setViewCount(youtubeRecord.getStatistics().getViewCount().toInteger());
                    long daysAvailable = LocalDate.now().minus(youtubeRecord.getSnippet().getPublishedAt()).toEpochDay();
                    video.setMonthlyViewCount(video.getViewCount() * 365.0 / daysAvailable / 12);
                });
        return JSON.dump(videos);
    }
}
