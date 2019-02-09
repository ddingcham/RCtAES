package example.service;

import example.external.YoutubeConnection;
import example.external.supports.Response;
import example.model.Video;
import example.model.Videos;
import example.supports.JSONUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class VideoServiceTest {

    VideoService videoService;
    YoutubeConnection youtubeConnection;

    @Before
    public void setUp() {
        videoService = spy(VideoService.class);
        youtubeConnection = mock(YoutubeConnection.class);
    }

    @Test
    public void microservicesMonthlyJson() {
        doReturn(stubReadVideoMeta())
                .when(videoService)
                .readVideoMeta();
        doReturn(3L)
                .when(videoService)
                .calculateDaysAvailable(any());
        doReturn(stubCallYoutube())
                .when(youtubeConnection)
                .callYoutube(any());
        videoService.setYoutubeConnection(youtubeConnection);
        String actual = videoService.getVideos();

        System.out.println("actual(videos) : " + actual);

        assertThat(actual)
                .isEqualTo(expectedVideos());
    }

    private String expectedVideos() {
        return JSONUtils.dump(fixturedVideos());
    }

    private String stubCallYoutube() {
        return "";
    }

    private Videos stubReadVideoMeta() {
        return fixturedVideos();
    }

    private Videos fixturedVideos() {
        Videos videos = new Videos();
        videos.addVideo(Video.builder()
                .id(1)
                .viewCount(0)
                .monthlyViewCount(0)
                .build());
        videos.addVideo(Video.builder()
                .id(2)
                .viewCount(1)
                .monthlyViewCount(1 * 365.0 / 3L / 12)
                .build());
        videos.addVideo(Video.builder()
                .id(3)
                .viewCount(2)
                .monthlyViewCount(2 * 365.0 / 3L / 12)
                .build());
        return videos;
    }
}
