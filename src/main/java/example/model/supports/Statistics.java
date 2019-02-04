package example.model.supports;

public class Statistics {

    private ViewCount viewCount;

    public Statistics(int viewCount) {
        this.viewCount = new ViewCount(viewCount);
    }

    public ViewCount getViewCount() {
        return viewCount;
    }
}
