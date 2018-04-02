package Controller;

public class ControllerRequest {
    private ControllerRequestType controllerRequestType;
    private String[] information;

    public ControllerRequest(ControllerRequestType controllerRequestType) {
        this.controllerRequestType = controllerRequestType;
    }

    public ControllerRequest(ControllerRequestType controllerRequestType, String[] information) {
        this.controllerRequestType = controllerRequestType;
        this.information = information;
    }

    public ControllerRequestType getControllerRequestType() {
        return controllerRequestType;
    }

    public String[] getInformation() {
        return information;
    }
}
