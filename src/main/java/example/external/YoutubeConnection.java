package example.external;

import example.external.supports.Parameters;
import example.external.supports.Request;
import example.external.supports.Response;
import example.model.Youtube;
import example.model.YoutubeIDs;
import example.supports.JSONUtils;

public class YoutubeConnection {
    public Response callYoutube(YoutubeIDs youtubeIDs) {
        Client client = GoogleAuthorizer
                .builder()
                .tokenKey("external-youtube")
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

        return JSONUtils.parseToResponse(client.execute(request).getBody());
    }
}
