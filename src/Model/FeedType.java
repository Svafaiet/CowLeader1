package Model;

public enum FeedType {
    BARLEY, ALFALFA, STRAW;

    public int loveDegree(FeedType feedType) {
        switch (feedType) {
            case BARLEY:
                return 80;
            case ALFALFA:
                return 60;
            case STRAW:
                return 20;
            default:
                return 0;
        }
    }

    public int wieghtIncrease(FeedType feedType) {
        switch (feedType) {
            case BARLEY:
                return 4;
            case ALFALFA:
                return 3;
            case STRAW:
                return 0;
            default:
                return 0;
        }
    }

    public int milkIncrease(FeedType feedType) {
        switch (feedType) {
            case BARLEY:
                return 4;
            case ALFALFA:
                return 3;
            case STRAW:
                return 2;
            default:
                return 0;
        }
    }

}
