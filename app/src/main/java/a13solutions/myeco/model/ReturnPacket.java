package a13solutions.myeco.model;

/**
 * Created by 13120dde on 2017-09-13.
 */
public class ReturnPacket{

    private boolean success;
    private String message;

    public ReturnPacket(boolean success, String message){
        this.success=success;
        this.message=message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
