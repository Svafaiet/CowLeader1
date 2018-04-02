package Controller;

public class ControllerRequest {
    private ControllerRequestType controllerRequestType;
    private String[] informations;

    public ControllerRequest(ControllerRequestType controllerRequestType) {
        this.controllerRequestType = controllerRequestType;
    }

    public ControllerRequestType getControllerRequestType() {
        return controllerRequestType;
    }

    public String[] getInformations() {
        return informations;
    }
}
