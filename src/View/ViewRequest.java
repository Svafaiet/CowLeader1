package View;

public class ViewRequest {
    private CommandType commandType;
    private String[] request;

    public ViewRequest(CommandType commandType, String[] request) {
        this.commandType = commandType;
        this.request = request;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getRequest() {
        return request;
    }

    public String getRequestWord(int n) {
        return request[n-1]; //alert fixme
    }
}
