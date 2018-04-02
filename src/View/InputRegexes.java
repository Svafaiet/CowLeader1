package View;

public class InputRegexes {
    public static final String SET_DATE = "\\s*^(\\s*[1-9]\\d\\d\\d\\/(0[1-9]|1[0-2])\\/(0[1-9]|30|[1-2][0-9]\\s*))$\\s*";
    public static final String ADD_BAEBAND = "\\s*^(\\s*add\\s+barband\\s+\\d+\\s*)$\\s*";
    public static final String STATUS_BARBAND = "\\s*^(\\s*status\\s+barband\\s+\\d+\\s*)$\\s*";
    public static final String STATUS_COW = "\\s*^(\\s*status\\s+cow\\s+\\d+\\s*)$\\s*";
    public static final String ADD_COW = "\\s*^(\\s*add\\s+cow\\s+\\d+\\s*)$\\s*";
    public static final String ADD_TANK = "\\s*^(\\s*add\\s+tank\\s+\\d+\\s*)$\\s*";
    public static final String STATUS_FARM = "\\s*^(\\s*status\\s+farm\\s*)$\\s*";
    public static final String FEED_BARBAND = "\\s*^(\\s*feed\\s+barband\\s+\\d+\\s*)$\\s*";
    public static final String STATUS_STORAGE = "\\s*^(\\s*status\\s+storage\\s*)$\\s*";
    public static final String STATUS_TANKS = "\\s*^(\\s*status\\s+tanks\\s*)$\\s*";
    public static final String MILK_COW = "\\s*^(\\s*milk\\s+cow\\s+\\d+\\s+\\d+\\s*)$\\s*";
    public static final String ADD_FOOD_TO_STORAGE = "\\s*^(\\s*add\\s+storage\\s+\\S+\\s+\\d+\\s*)$\\s*";
    public static final String SELL_MILK = "\\s*^(\\s*sell\\s+milk\\s+\\d+\\s+\\d+\\s*)$\\s*";
    public static final String EMPTY_TANK = "\\s*^(\\s*empty\\s+tank\\s+\\d+\\s*)$\\s*";
    public static final String BUTCHER_COW = "\\s*^(\\s*butcher\\s+cow\\s+\\d+\\s*)$\\s*";
    public static final String MOVE_COW = "\\s*^(\\s*move\\s+cow\\s+\\d+\\s+\\d+\\s*)$\\s*";
    public static final String SHOW_RANKS = "\\s*^(\\s*show\\s+ranks\\s*)$\\s*";
    public static final String INCREASE_STORAGE_CAPACITY = "\\s*^(\\s*increase\\s+storage\\s+capacity\\s+\\d+\\s*)$\\s*";
    public static final String ADD_NEW_FOOD = "\\s*^(\\s*add\\s+new\\s+food\\s+\\S+\\s+\\d+\\s*)$\\s*";
    public static final String END_DAY = "\\s*^(\\s*day\\s+passed\\s+\\d+\\s*)$\\s*";
    public static final String FEED_TYPE = "\\s*^(\\s*\\S+\\s+\\d+\\s*)$\\s*";
    public static final String END_FEED = "\\s*^(\\s*end\\_feed\\s+\\d+\\s*)$\\s*";
    public static final String FEED_PROPERTIES = "\\s*^(\\s*\\d+\\s*)$\\s*";
    public static final String END = "\\s*^(\\s*end\\s*)$\\s*";
}
