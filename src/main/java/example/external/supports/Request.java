package example.external.supports;

import example.model.Videos;
import lombok.Builder;

public class Request {

    @Builder
    private Request(Videos apiMethod, Parameters parameters) {

    }
}
