package Controller;

import Model.Cow;
import Model.DairyFarm;
import Model.ReturnValues.AddToStorageReturnValue;
import Model.ReturnValues.MilkCowReturnValue;
import Model.ReturnValues.MoveCowReturnValue;
import Model.ReturnValues.SellMilkReturnValue;
import Model.Tank;
import View.CommandType;
import View.DairyFarmView;
import View.ViewRequest;

import java.util.HashMap;
import java.util.Map;

public class DairyFarmController {
    private DairyFarm dairyFarmModel;
    private DairyFarmView dairyFarmView;
    private ViewRequest viewRequest;
    private WhichPartAreWeIn whichPartAreWeIn;

    private int feedPropertiesCount;
    private int saveBarbandNum;
    private Map<String, Integer> feedsMovingToAkhoor;
    private String saveFoodName;
    private int saveFoodCount;
    private int[] saveFeedProperties;

    public DairyFarmController() {
        dairyFarmView = new DairyFarmView();
        whichPartAreWeIn = WhichPartAreWeIn.SET_DATE;
        feedPropertiesCount = 0;
        saveFeedProperties = new int[3];
    }

    public boolean hasEnded() {
        return viewRequest.getCommandType() == CommandType.END;
    }

    public void start() {
        dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.START));
    }

    public void takeCommand() {
        viewRequest = dairyFarmView.getRequest();
    }

    public void handleCommands() {
        if (viewRequest.getCommandType() != CommandType.NOTHING) {
            switch (whichPartAreWeIn) {
                case SET_DATE:
                    handleCommandsInSetDate();
                    break;
                case GET_FEED_PROPERTIES:
                    handleCommandsInGetFeedProperties();
                    break;
                case GET_FEED_BARBAND_FEEDS:
                    handleCommandsInGetFeedBarbandFeeds();
                    break;
                case MENU:
                    handleCommandsInMenu();
                    break;
            }
        }
    }

    private void handleCommandsInSetDate() {
        switch (viewRequest.getCommandType()) {
            case SET_DATE:
                dairyFarmModel = new DairyFarm(
                        Integer.parseInt(viewRequest.getRequestWord(1)),
                        Integer.parseInt(viewRequest.getRequestWord(2)),
                        Integer.parseInt(viewRequest.getRequestWord(3)));
                whichPartAreWeIn = WhichPartAreWeIn.MENU;
                break;
            case END:
                break;
            default:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_DATE));
        }
    }

    private void handleCommandsInGetFeedProperties() {
        switch (viewRequest.getCommandType()) {
            case END:
                break;
            case FEED_PROPERTIES:
                saveFeedProperties[feedPropertiesCount] = Integer.parseInt(viewRequest.getRequestWord(1));
                feedPropertiesCount++;
                if (feedPropertiesCount == 3) {
                    feedPropertiesCount = 0;
                    whichPartAreWeIn = WhichPartAreWeIn.MENU;
                    dairyFarmModel.addNewFoodToStorage(saveFoodCount, saveFoodName,
                            saveFeedProperties[2], saveFeedProperties[1], saveFeedProperties[0]);
                }
                break;
            default:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COMMAND));
                whichPartAreWeIn = WhichPartAreWeIn.MENU;
                //may be this change fixme
                break;
        }
    }

    private void handleCommandsInGetFeedBarbandFeeds() {
        switch (viewRequest.getCommandType()) {
            case END:
                break;
            case END_FEED:
                dairyFarmModel.feedBarband(saveBarbandNum, feedsMovingToAkhoor);
                whichPartAreWeIn = WhichPartAreWeIn.MENU;
                saveBarbandNum = -1;
                break;
            case FEED_TYPES:
                feedsMovingToAkhoor.put(viewRequest.getRequestWord(1),
                        Integer.parseInt(viewRequest.getRequestWord(2)));

                break;
            default:
                break;
        }

    }

    private void handleCommandsInMenu() {
        switch (viewRequest.getCommandType()) {
            case FEED_BARBAND:
                feedBarband();
                break;
            case ADD_NEW_FOOD:
                addNewFood();
                break;
            case END:
                break;
            case MOVE_COW:
                moveCow();
                break;
            case ADD_COW:
                addCow();
                break;
            case END_DAY:
                dairyFarmModel.endDay();
                break;
            case ADD_TANK:
                dairyFarmModel.addTank(Integer.parseInt(viewRequest.getRequestWord(3)));
                break;
            case SELL_MILK:
                sellMilk();
                break;
            case MILK_COW:
                milkCow();
                break;
            case EMPTY_TANK:
                emptyTank();
                break;
            case ADD_BAEBAND:
                dairyFarmModel.addBarband(Integer.parseInt(viewRequest.getRequestWord(3)));
                break;
            case BUTCHER_COW:
                butcherCow();
                break;
            case ADD_FOOD_TO_STORAGE:
                addFoodToStorage();
                break;
            case INCREASE_STORAGE_CAPACITY:
                dairyFarmModel.increaseStorageCapacity(Integer.parseInt(viewRequest.getRequestWord(4)));
                break;
            case SHOW_RANKS:
                break;
            case STATUS_COW:
                statusCow();
                break;
            case STATUS_FARM:
                statusFarm();
                break;
            case STATUS_TANKS:
                statusTanks();
                break;
            case STATUS_BARBAND:
                statusBarband();
                break;
            case STATUS_STORAGE:
                statusStorage();
                break;
            default:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COMMAND));
                break;
        }
    }

    private void statusStorage() {
        dairyFarmView.showControllerRequest(
                new ControllerRequest(ControllerRequestType.SHOW_STORAGE,
                        dairyFarmModel.getStorage().getInformation().split(" "))
        );
    }

    private void statusBarband() {
        String barbandNum = viewRequest.getRequestWord(3);
        dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.SHOW_BARBAND,
                (barbandNum + " " + dairyFarmModel.getBarband(Integer.parseInt(barbandNum)).getInformation())
                        .split(" ")));
    }

    private void statusTanks() {
        for (int i = 0; i < dairyFarmModel.getTanks().size(); i++) {
            Tank tank = dairyFarmModel.getTanks().get(i);
            dairyFarmView.showControllerRequest(
                    new ControllerRequest(ControllerRequestType.SHOW_TANK,
                            (String.valueOf(i + 1) + " " + tank.getInformation()).split(" ")));
        }
    }

    private void statusFarm() {
        dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.SHOW_FARM,
                dairyFarmModel.getInformation().split(" ")));
    }

    private void statusCow() {
        Cow cow = dairyFarmModel.getCowByNumber(Integer.parseInt(viewRequest.getRequestWord(3)));
        if (cow == null) {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COW));
        } else {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.SHOW_COW,
                    cow.getInformation().split(" ")));
        }
    }

    private void addFoodToStorage() { //fixme
        AddToStorageReturnValue addToStorageReturnValue =
                dairyFarmModel.addToStorage(viewRequest.getRequestWord(3),
                        Integer.parseInt(viewRequest.getRequestWord(4)));
        switch (addToStorageReturnValue) {
            case NOT_ENOUGH_SPACE:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.THERE_IS_NOT_ENOUGH_SPACE));
                break;
            case NO_SUCH_A_FOOD:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_FOOD_NAME));
                break;
            case ADDED_SUCCESSFULLY:
                break;
        }
    }

    private void butcherCow() {
        if (dairyFarmModel.butcherCow(Integer.parseInt(viewRequest.getRequestWord(3)))) {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.BUTCHER_COW));
        } else {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COW));
        }
    }

    private void emptyTank() {
        if (!dairyFarmModel.emptyTank(Integer.parseInt(viewRequest.getRequestWord(3)))) {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_TANK));
        }
    }

    private void milkCow() {
        MilkCowReturnValue milkCowReturnValue =
                dairyFarmModel.milkCow(Integer.parseInt(viewRequest.getRequestWord(3)),
                        Integer.parseInt(viewRequest.getRequestWord(4)));
        switch (milkCowReturnValue) {
            case NOT_ENOUGH_SPACE:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.THERE_IS_NOT_ENOUGH_SPACE));
                break;
            case INVALID_COW:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COW));
                break;
            case INVALID_TANK:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_TANK));
                break;
            case MILKED_SUCCESSFULLY:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.COW_MILKED_SUCCESSFULLY));
                break;
            case NO_MILK:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.COW_HAS_NO_MILK));
                break;
        }
    }

    private void sellMilk() {
        SellMilkReturnValue sellMilkReturnValue =
                dairyFarmModel.sellMilk(Integer.parseInt(viewRequest.getRequestWord(3)),
                        Integer.parseInt(viewRequest.getRequestWord(4)));
        switch (sellMilkReturnValue) {
            case MILK_SOLD_SUCCESSFULLY:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.MILK_SOLD_SUCCESSFULLY));
                break;
            case NOT_ENOUGH_MILK:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.THERE_IS_NOT_ENOUGH_MILK));
                break;
            case INVALD_TANK:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_TANK));
                break;
        }
    }

    private void addCow() {
        int cowNum = dairyFarmModel.addNewCow(Integer.parseInt(viewRequest.getRequestWord(3)));
        if (cowNum != -1) {
            String[] cowNumInString = new String[1];
            cowNumInString[0] = String.valueOf(cowNum);
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.COW_ADDED, cowNumInString));
        } else {
            String[] barbandNum = new String[1];
            barbandNum[0] = viewRequest.getRequestWord(3);
            dairyFarmView.showControllerRequest(
                    new ControllerRequest(ControllerRequestType.THERE_IS_NOT_ENOUGH_SPACE_IN_BARBAND,
                            barbandNum));
        }
    }

    private void moveCow() {
        MoveCowReturnValue moveCowReturnValue = dairyFarmModel.moveCow(
                Integer.parseInt(viewRequest.getRequestWord(3)),
                Integer.parseInt(viewRequest.getRequestWord(4)));
        switch (moveCowReturnValue) {
            case INVALID_COW:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COW));
                break;
            case INVALID_BARBAND:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_BARBAND));
                break;
            case COW_MOVED_SUCCESSULLY:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.MOVE_COW));
                break;
            case NOT_ENOUGH_SPACE:
                String[] barbandNum = new String[1];
                barbandNum[0] = viewRequest.getRequestWord(4);
                dairyFarmView.showControllerRequest(
                        new ControllerRequest(
                                ControllerRequestType.THERE_IS_NOT_ENOUGH_SPACE_IN_BARBAND, barbandNum
                        )
                );
                break;
        }
    }

    private void addNewFood() {
        whichPartAreWeIn = WhichPartAreWeIn.GET_FEED_PROPERTIES;
        saveFoodName = viewRequest.getRequestWord(4);
        saveFoodCount = Integer.parseInt(viewRequest.getRequestWord(5));
    }

    private void feedBarband() {
        if (dairyFarmModel.isBarbandValid(Integer.parseInt(viewRequest.getRequestWord(3)))) {
            saveBarbandNum = Integer.parseInt(viewRequest.getRequestWord(3));
            feedsMovingToAkhoor = new HashMap<>();
            whichPartAreWeIn = WhichPartAreWeIn.GET_FEED_BARBAND_FEEDS;
        } else {
            dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_BARBAND));
        }
    }
}
