package example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Youtube {

    @Getter
    private Videos videoList;

    public Youtube(Videos videoList) {
        if(videoList == null) {
            throw new IllegalArgumentException("videoList can't be null");
        }
        this.videoList = videoList;
    }
}
