package Controller;

import Model.DairyFarm;
import Model.ReturnValues.MilkCowReturnValue;
import Model.ReturnValues.MoveCowReturnValue;
import Model.ReturnValues.SellMilkReturnValue;
import View.DairyFarmView;
import View.ViewRequest;

public class FarmDairyController {
    private DairyFarm dairyFarmModel;
    private DairyFarmView dairyFarmView;
    private ViewRequest viewRequest;
    private WhichPartAreWeIn whichPartAreWeIn;
    private int feedProperties;
    private 

    private int barbandNum;
    private String foodName;
    private int foodCount;

    public FarmDairyController() {
        dairyFarmView = new DairyFarmView();
        whichPartAreWeIn = WhichPartAreWeIn.SET_DATE;
        feedProperties = 0;
    }

    public void takeCommand() {
        viewRequest = dairyFarmView.getRequest();
    }

    public void handleCommands() {
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
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COMMAND));
        }
    }

    private void handleCommandsInGetFeedProperties() {
        switch (viewRequest.getCommandType()) {
            case END:
                break;
            case FEED_PROPERTIES:
                feedProperties++;
                if (feedProperties == 3) {
                    feedProperties = 0;
                    whichPartAreWeIn = WhichPartAreWeIn.MENU;
                }
                break;
        }

    }

    private void handleCommandsInGetFeedBarbandFeeds() {
        switch (viewRequest.getCommandType()) {
            case END:
                break;
            case END_FEED:
                barbandNum = -1;
                whichPartAreWeIn = WhichPartAreWeIn.MENU;
                break;
            case FEED_TYPES:
                dairyFarmModel.feedBarband(barbandNum, viewRequest.getRequestWord(1),
                        Integer.parseInt(viewRequest.getRequestWord(2)));
                break;
            default:
                dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COMMAND));
                break;
        }

    }

    private void handleCommandsInMenu() {
        switch (viewRequest.getCommandType()) {
            case FEED_BARBAND:
                if (dairyFarmModel.isBarbandValid(Integer.parseInt(viewRequest.getRequestWord(3)))) {
                    barbandNum = Integer.parseInt(viewRequest.getRequestWord(3));
                    whichPartAreWeIn = WhichPartAreWeIn.GET_FEED_PROPERTIES;
                } else {
                    dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_BARBAND));
                }
                break;
            case ADD_NEW_FOOD:
                whichPartAreWeIn = WhichPartAreWeIn.GET_FEED_PROPERTIES;
                foodName = viewRequest.getRequestWord(4);
                foodCount = Integer.parseInt(viewRequest.getRequestWord(5));
                break;
            case END:
                break;
            case MOVE_COW:
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
                        dairyFarmView.showControllerRequest(new ControllerRequest());
                        break;
                }
                break;
            case ADD_COW:
                if (dairyFarmModel.addNewCow(Integer.parseInt(viewRequest.getRequestWord(3)))) {
                    dairyFarmView.showControllerRequest(new ControllerRequest());
                } else {
                    dairyFarmView.showControllerRequest(new ControllerRequest());
                }
                break;
            case END_DAY:
                dairyFarmModel.endDay();
                break;
            case ADD_TANK:
                dairyFarmModel.addTank(Integer.parseInt(viewRequest.getRequestWord(3)));
                break;
            case SELL_MILK:
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
                break;
            case MILK_COW:
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
                break;
            case EMPTY_TANK:
                if(!dairyFarmModel.emtptyTank(Integer.parseInt(viewRequest.getRequestWord(3)))){
                    dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_TANK));
                }
                break;
            case ADD_BAEBAND:
                dairyFarmModel.addTank(Integer.parseInt(viewRequest.getRequestWord(3)));
                break;
            case BUTCHER_COW:
                if(dairyFarmModel.butcherCow(Integer.parseInt(viewRequest.getRequestWord(3)))) {
                    dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.BUTCHER_COW));
                } else {
                    dairyFarmView.showControllerRequest(new ControllerRequest(ControllerRequestType.INVALID_COW));
                }
                break;
            case ADD_FOOD_TO_STORAGE:
                dairyFarmModel.a
        }
    }
}
