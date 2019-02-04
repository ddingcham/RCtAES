package example.service;

import example.api.Response;
import example.model.Video;
import example.model.Videos;
import example.supports.JSON;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class VideoServiceTest {

    VideoService videoService;

    @Before
    public void setUp() {
        videoService = spy(VideoService.class);
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
                .when(videoService)
                .callYoutube(any());
        String actual = videoService.getVideos();

        System.out.println("actual(videos) : " + actual);

        assertThat(actual)
                .isEqualTo(expectedVideos());
    }

    private String expectedVideos() {
        return JSON.dump(fixturedVideos());
    }

    private Response stubCallYoutube() {
        return Response.builder()
                .with(0, 0)
                .with(1, 1)
                .with(2, 2)
                .build();
    }

    private Videos stubReadVideoMeta() {
//        TODO
//        return JSON.parseToVideos(File.read("test/data/youtube-video-list.json"));
        return fixturedVideos();
    }

    private Videos fixturedVideos() {
        Videos videos = new Videos();
        videos.addVideo(Video.builder()
                .id(0)
                .viewCount(0)
                .monthlyViewCount(0)
                .build());
        videos.addVideo(Video.builder()
                .id(1)
                .viewCount(1)
                .monthlyViewCount(1 * 365.0 / 3L / 12)
                .build());
        videos.addVideo(Video.builder()
                .id(2)
                .viewCount(2)
                .monthlyViewCount(2 * 365.0 / 3L / 12)
                .build());
        return videos;
    }
}
