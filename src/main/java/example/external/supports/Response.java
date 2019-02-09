package example.external.supports;

import example.model.Video;
import example.model.Videos;
import example.model.YoutubeRecord;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Response {

    Map<Integer, YoutubeRecord> youtubeRecords = new HashMap<>();

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    private void addYoutubeRecord(int youtubeRecordID, int viewCount) {
        if (youtubeRecords.containsKey(youtubeRecordID)) {
            throw new IllegalStateException("중복 id : " + youtubeRecordID);
        }
        youtubeRecords.put(youtubeRecordID,
                YoutubeRecord
                        .builder()
                        .id(youtubeRecordID)
                        .viewCount(viewCount)
                        .build());
    }

    public YoutubeRecord findById(int youtubeRecordID) {
        if(youtubeRecords.containsKey(youtubeRecordID)) {
            return youtubeRecords.get(youtubeRecordID);
        }
        throw new NoSuchElementException("없는 id : " + youtubeRecordID);
    }

    public Videos getVideos() {
        Videos videos = new Videos();
        youtubeRecords.forEach(
                (id, record) -> videos
                        .addVideo(Video.builder()
                                .id(record.getId())
                                .viewCount(record.getStatistics().getViewCount())
                                .build()));
        return videos;
    }


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ResponseBuilder {
        private boolean isBuild;
        private Response response;

        public ResponseBuilder with(int youtubeRecordID, int viewCount) {
            if (response == null) {
                response = new Response();
            }
            if (isBuild) {
                throw new IllegalStateException("build response only one time");
            }
            response.addYoutubeRecord(youtubeRecordID, viewCount);
            return this;
        }

        public Response build() {
            isBuild = true;
            return response;
        }
    }
}
