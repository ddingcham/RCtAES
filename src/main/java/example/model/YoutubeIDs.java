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
        StringBuilder result = new StringBuilder(IDs.get(0));
        for (int id : IDs.subList(1, IDs.size())) {
            result.append(delim + id);
        }
        return result.toString();
    }

    public Integer[] toArray() {
        return IDs.toArray(new Integer[IDs.size()]);
    }
}
