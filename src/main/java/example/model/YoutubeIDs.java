package example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class YoutubeIDs {

    List<Integer> IDs;

    public YoutubeIDs(Set<Integer> youtubeIDs) {
        IDs = new ArrayList<>(youtubeIDs);
    }

    public String join(String delim) {
        return null;
    }

    public Integer[] toArray() {
        return IDs.toArray(new Integer[IDs.size()]);
    }
}
