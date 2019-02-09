package example.external;

import example.external.supports.Parameters;
import example.external.supports.Request;
import example.model.Youtube;
import example.model.YoutubeIDs;

public class YoutubeConnection {
    public String callYoutube(YoutubeIDs youtubeIDs) {
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

        return client.execute(request).getBody();
    }
}
