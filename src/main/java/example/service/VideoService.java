package example.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/*
    The example code's job is to read some data about videos from a JSON file,
    enrich it with data from Youtube, calculate some simple further data,
    and then return the data in JSON.
 */
public class VideoService {
    public List<Video> getVideos() {
        Videos videos = JSON.parse(File.read('videos.json'));
        int[] youtubeIDs = videos.map(video -> video.getYoutubeIDs());
        Client client = GoogleAuthorizer
                .builder()
                .tokenKey('api-youtube')
                .applicationName('Gateway Youtube Example')
                .applicationVersion('0.1')
                .build();
        Youtube youtube = client.discoveredApi('youtube', 'v3');
        Request request = Request
                .builder()
                .apiMethod(youtube.getVideoList())
                .parameters(Parameters
                        .with("id", YoutubeIds.join(","))
                        .with("part", "snippet, contentDetails, statistics")
                        .build())
                .build();
        Response response = JSON.parse(client.execute(request).getBody());
        Arrays.stream(youtubeIDs)
                .forEach(youtubeID -> {
                    Video video = videos.findById(youtubeID);
                    YoutubeRecord youtubeRecord = response.findById(youtubeID);
                    // TODO : to_i 가 무슨 의미 였을지
                    video.setViews(youtubeRecord.getStatistics().viewCount().toI());
                    long daysAvailable = LocalDate.now().minus(youtubeRecord.getSnippet().getPublishedAt()).toEpochDay();
                    video.setMonthlyViews(video.getViews() * 365.0 / daysAvailable / 12);
                });
        return JSON.dump(videos);
    }
}
