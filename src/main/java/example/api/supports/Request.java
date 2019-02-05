package example.api.supports;

import example.api.supports.Parameters;
import example.model.Videos;
import lombok.Builder;

public class Request {

    @Builder
    private Request(Videos apiMethod, Parameters parameters) {

    }
}
