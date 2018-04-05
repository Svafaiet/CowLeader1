package View;

import Controller.ControllerRequest;
import Controller.Separator;

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
            return new ViewRequest(commandType, commandText.split("/"));
        }
        return new ViewRequest(commandType, commandText.split("\\s+"));
    }

    private void distinguishCommandType() {
        if(commandText.matches(InputRegexes.NOTHING)) {
            commandType = CommandType.NOTHING;
        } else if (commandText.matches(InputRegexes.ADD_BAEBAND)) {
            commandType = CommandType.ADD_BAEBAND;
        } else if (commandText.matches(InputRegexes.ADD_COW)) {
            commandType = CommandType.ADD_COW;
        } else if (commandText.matches(InputRegexes.SET_DATE)) {
            commandType = CommandType.SET_DATE;
        } else if (commandText.matches(InputRegexes.SELL_MILK)) {
            commandType = CommandType.SELL_MILK;
        } else if (commandText.matches(InputRegexes.ADD_FOOD_TO_STORAGE)) {
            commandType = CommandType.ADD_FOOD_TO_STORAGE;
        } else if (commandText.matches(InputRegexes.ADD_NEW_FOOD)) {
            commandType = CommandType.ADD_NEW_FOOD;
        } else if (commandText.matches(InputRegexes.ADD_TANK)) {
            commandType = CommandType.ADD_TANK;
        } else if (commandText.matches(InputRegexes.BUTCHER_COW)) {
            commandType = CommandType.BUTCHER_COW;
        } else if (commandText.matches(InputRegexes.EMPTY_TANK)) {
            commandType = CommandType.EMPTY_TANK;
        } else if (commandText.matches(InputRegexes.END_DAY)) {
            commandType = CommandType.END_DAY;
        } else if (commandText.matches(InputRegexes.FEED_BARBAND)) {
            commandType = CommandType.FEED_BARBAND;
        } else if (commandText.matches(InputRegexes.MILK_COW)) {
            commandType = CommandType.MILK_COW;
        } else if (commandText.matches(InputRegexes.MOVE_COW)) {
            commandType = CommandType.MOVE_COW;
        } else if (commandText.matches(InputRegexes.STATUS_BARBAND)) {
            commandType = CommandType.STATUS_BARBAND;
        } else if (commandText.matches(InputRegexes.STATUS_COW)) {
            commandType = CommandType.STATUS_COW;
        } else if (commandText.matches(InputRegexes.STATUS_FARM)) {
            commandType = CommandType.STATUS_FARM;
        } else if (commandText.matches(InputRegexes.STATUS_TANKS)) {
            commandType = CommandType.STATUS_TANKS;
        } else if (commandText.matches(InputRegexes.STATUS_STORAGE)) {
            commandType = CommandType.STATUS_STORAGE;
        } else if (commandText.matches(InputRegexes.SHOW_RANKS)) {
            commandType = CommandType.SHOW_RANKS;
        } else if (commandText.matches(InputRegexes.INCREASE_STORAGE_CAPACITY)) {
            commandType = CommandType.INCREASE_STORAGE_CAPACITY;
        } else if (commandText.matches(InputRegexes.FEED_TYPE)) {
            commandType = CommandType.FEED_TYPES;
        } else if (commandText.matches(InputRegexes.END_FEED)) {
            commandType = CommandType.END_FEED;
        } else if (commandText.matches(InputRegexes.FEED_PROPERTIES)) {
            commandType = CommandType.FEED_PROPERTIES;
        } else if (commandText.matches(InputRegexes.END)) {
            commandType = CommandType.END;
        } else {
            commandType = CommandType.INVALID_COMMAND;
        }
    }

    public void showControllerRequest(ControllerRequest controllerRequest) {
        switch (controllerRequest.getControllerRequestType()) {
            case START:
                System.out.println(Massage.SET_DATE);
                break;
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
                showNotEnoughMilk();
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
            case THERE_IS_NOT_ENOUGH_SPACE_IN_BARBAND:
                showNotEnoughSpaceInBarband(controllerRequest.getInformation());
                break;
            case COW_ADDED:
                showCowAdded(controllerRequest.getInformation());
                break;
            case SHOW_FARM:
                showFarm(controllerRequest.getInformation());
                break;
            case SHOW_COW:
                showCow(controllerRequest.getInformation());
                break;
            case SHOW_TANK:
                showTank(controllerRequest.getInformation());
                break;
            case SHOW_BARBAND:
                showBarband(controllerRequest.getInformation());
                break;
            case SHOW_STORAGE:
                showStorage(controllerRequest.getInformation());
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

    private void showNotEnoughSpaceInBarband(String[] information) {
        System.out.println(Massage.THERE_IS_NOT_ENOUGH_SPACE_IN_BARBAND.replaceAll("BARBAND_NUM", information[0]));
    }

    private void showCowAdded(String[] information) {
        System.out.println(Massage.COW_ADDED.replaceAll("COW_NUM", information[0]));
    }

    private void showFarm(String[] information) {
        String output;
        output = Massage.SHOW_FARM.replaceAll("BARBAND_NUM", information[0]);
        output = output.replaceAll("COW_NUM", information[1]);
        output = output.replaceAll("CAPACITY", information[2]);
        output = output.replaceAll("MILK_TANK_NUM", information[3]);
        output = output.replaceAll("MAX_MILK_PRODUCTION", information[4]);
        System.out.println(output);
    }

    private void showCow(String[] information) {
        String output;
        output = Massage.SHOW_COW.replaceAll("COW_NUM", information[0]);
        output = output.replaceAll("AGE", information[1]);
        output = output.replaceAll("HUNGER", information[2]);
        output = output.replaceAll("WEIGHT", information[3]);
        output = output.replaceFirst("MILK", information[4]);
        output = output.replaceAll("MILK_PRODUCED", information[5]);
        System.out.println(output);
    }

    private void showTank(String[] information) {
        String output;
        output = Massage.SHOW_TANK.replaceAll("TANK_NUM", information[0]);
        output = output.replaceAll("CAPACITY", information[1]);
        output = output.replaceAll("EMPTY_SPACE", information[2]);
        output = output.replaceAll("EXPDA", information[3]);
        output = output.replaceAll("NO_EXPDA", "");
        System.out.println(output);
    }

    private void showStorage(String[] information) {
        String output;
        output = Massage.SHOW_STORAGE.replaceAll("CAPACITY", information[0]);
        int feedCount = Integer.parseInt(information[1]);
        for (int i = 0; i < feedCount; i++) {
            output = output.replaceAll("FEEDS", information[2 + i] + "\nFEEDS" );
        }
        output = output.replaceAll("FEEDS", "").replaceAll(Separator.SEPARATOR, " "); //fixme maybe
        System.out.println(output);
    }

    private void showBarband(String[] information) {
        int cowCount = Integer.parseInt(information[1]);
        int feedCount = Integer.parseInt(information[3]);
        String output;
        output = Massage.SHOW_BARBAND.replaceAll("BARBAND_NUM", information[0]);
        output = output.replaceFirst("COW_NUM", information[1]);
        output = output.replaceAll("CAPACITY", information[2]);
        for (int i = 0; i < feedCount; i++) {
            output = output.replaceAll("REMAINING_FEED", information[4 + i] + "\nREMAINING_FEED");
        }
        output = output.replaceAll("\nREMAINING_FEED", "").replaceAll(Separator.SEPARATOR, " ");  //fixme maybe
        for (int i = 0; i < cowCount; i++) {
            output = output.replaceAll("COW_NUMBERS", information[4 + feedCount + i] + "\nCOW_NUMBERS");
        }
        output = output.replaceAll("\nCOW_NUMBERS", ""); //fixme maybe
        System.out.println(output);
    }
}