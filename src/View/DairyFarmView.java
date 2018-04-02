package View;

import Controller.ControllerRequest;

import java.util.Scanner;

public class DairyFarmView {
    private Scanner scanner = new Scanner(System.in);
    private String commandText;
    private CommandType commandType;

    public ViewRequest getRequest() {
        commandText = scanner.nextLine().replaceAll("\\s+", " ");
        if (commandText.matches("^\\s")) {
            commandText = commandText.substring(1);
        }
        if (commandText.matches("\\s$")) {
            commandText = commandText.substring(0, commandText.length() - 1);
        }
        distinguishCommandType();
        if (commandType == CommandType.SET_DATE) {
            return new ViewRequest(commandType, commandText.split("\\\\"));
        }
        return new ViewRequest(commandType, commandText.split("\\s+"));
    }

    private void distinguishCommandType() {
        switch (commandText) {
            case InputRegexes.ADD_BAEBAND:
                commandType = CommandType.ADD_BAEBAND;
                break;
            case InputRegexes.ADD_COW:
                commandType = CommandType.ADD_COW;
                break;
            case InputRegexes.SET_DATE:
                commandType = CommandType.SET_DATE;
                break;
            case InputRegexes.SELL_MILK:
                commandType = CommandType.SELL_MILK;
                break;
            case InputRegexes.ADD_FOOD_TO_STORAGE:
                commandType = CommandType.ADD_FOOD_TO_STORAGE;
                break;
            case InputRegexes.ADD_NEW_FOOD:
                commandType = CommandType.ADD_NEW_FOOD;
                break;
            case InputRegexes.ADD_TANK:
                commandType = CommandType.ADD_TANK;
                break;
            case InputRegexes.BUTCHER_COW:
                commandType = CommandType.BUTCHER_COW;
                break;
            case InputRegexes.EMPTY_TANK:
                commandType = CommandType.EMPTY_TANK;
                break;
            case InputRegexes.END_DAY:
                commandType = CommandType.END_DAY;
                break;
            case InputRegexes.FEED_BARBAND:
                commandType = CommandType.FEED_BARBAND;
                break;
            case InputRegexes.MILK_COW:
                commandType = CommandType.MILK_COW;
                break;
            case InputRegexes.MOVE_COW:
                commandType = CommandType.MOVE_COW;
                break;
            case InputRegexes.STATUS_BARBAND:
                commandType = CommandType.STATUS_BARBAND;
                break;
            case InputRegexes.STATUS_COW:
                commandType = CommandType.STATUS_COW;
                break;
            case InputRegexes.STATUS_FARM:
                commandType = CommandType.STATUS_FARM;
                break;
            case InputRegexes.STATUS_TANKS:
                commandType = CommandType.STATUS_TANKS;
                break;
            case InputRegexes.STATUS_STORAGE:
                commandType = CommandType.STATUS_STORAGE;
                break;
            case InputRegexes.SHOW_RANKS:
                commandType = CommandType.SHOW_RANKS;
                break;
            case InputRegexes.INCREASE_STORAGE_CAPACITY:
                commandType = CommandType.INCREASE_STORAGE_CAPACITY;
                break;
            case InputRegexes.FEED_TYPE:
                commandType = CommandType.FEED_TYPES;
                break;
            case InputRegexes.END_FEED:
                commandType = CommandType.END_FEED;
                break;
            case InputRegexes.FEED_PROPERTIES:
                commandType = CommandType.FEED_PROPERTIES;
                break;
            case InputRegexes.END:
                commandType = CommandType.END;
                break;
            default:
                commandType = CommandType.INVALID_COMMAND;
        }
    }

    public void showControllerRequest(ControllerRequest controllerRequest) {
        switch (controllerRequest.getControllerRequestType()) {
            case INVALID_BARBAND:
                showInvalidBarband();
                break;
            case INVALID_COW:
                showInvalidCow();
                break;
            case INVALID_DATE:
                showInvalidDate();
                break;
            case INVALID_TANK:
                showInvalidTank();
                break;
            case INVALID_FOOD_NAME:
                showInvalidFoodName();
                break;
            case INVALID_COMMAND:
                showInvalidCommand();
                break;
            case MILK_SOLD_SUCCESSFULLY:
                showMilkSold();
                break;
            case DO_NOTHING:
                break;
            case COW_HAS_NO_MILK:
                showNoMilk();
                break;
            case THERE_IS_NOT_ENOUGH_MILK:
                showNoMilk();
                break;
            case THERE_IS_NOT_ENOUGH_SPACE:
                showNotEnoughSpace();
                break;
            case BUTCHER_COW:
                showExplodedCow();
                break;
            case MOVE_COW:
                showMoveCow();
                break;
            case COW_MILKED_SUCCESSFULLY:
                showSuccessfulMilking();
                break;
            case COW_ADDED:

                break;
            case SHOW_FARM:

                break;
            case SHOW_COW:

                break;
            case SHOW_TANK:

                break;
            case SHOW_BARBAND:

                break;
            case SHOW_STORAGE:

                break;
            case THERE_IS_NOT_ENOUGH_SPACE_IN_BARBAND:

                break;
        }
    }

    private void showSuccessfulMilking() {
        System.out.println(Massage.COW_MILKED_SUCCESSFULLY);
    }

    private void showMoveCow() {
        System.out.println(Massage.MOVE_COW);
    }

    private void showExplodedCow() {
        System.out.println(Massage.BUTCHER_COW);
    }

    private void showNotEnoughSpace() {
        System.out.println(Massage.THERE_IS_NOT_ENOUGH_SPACE);
    }

    private void showNotEnoughMilk() {
        System.out.println(Massage.THERE_IS_NOT_ENOUGH_MILK);
    }

    private void showNoMilk() {
        System.out.println(Massage.COW_HAS_NO_MILK);
    }

    private void showMilkSold() {
        System.out.println(Massage.MILK_SOLD_SUCCESSFULLY);
    }

    private void showInvalidCommand() {
        System.out.println(Massage.INVALID_COMMAND);
    }

    private void showInvalidFoodName() {
        System.out.println(Massage.INVALID_FOOD_NAME);
    }

    private void showInvalidTank() {
        System.out.println(Massage.INVALID_TANK);
    }

    private void showInvalidDate() {
        System.out.println(Massage.INVALID_DATE);
    }

    private void showInvalidBarband() {
        System.out.println(Massage.INVALID_BARBAND);
    }

    private void showInvalidCow() {
        System.out.println(Massage.INVALID_COW);
    }
}

