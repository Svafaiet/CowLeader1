import Controller.DairyFarmController;

public class Main {
    public static void main(String[] args) {
        DairyFarmController dairyFarmController = new DairyFarmController();
        do {
            dairyFarmController.takeCommand();
            dairyFarmController.handleCommands();
        } while (!dairyFarmController.hasEnded());
    }
}
