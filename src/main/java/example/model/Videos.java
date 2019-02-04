package example.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Videos {

    Map<Integer, Video> videos = new HashMap<Integer, Video>();

    public YoutubeIDs getVideoIDs() {
        return new YoutubeIDs(videos.keySet());
    }

    public Video findById(int videoID) {
        if (videos.containsKey(videoID)) {
            return videos.get(videoID);
        }
        throw new NoSuchElementException("no video id : " + videoID);
    }

    public void addVideo(Video video) {
        videos.put(video.getId(), video);
    }
}
