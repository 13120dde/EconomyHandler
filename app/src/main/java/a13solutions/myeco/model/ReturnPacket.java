package a13solutions.myEco.model;

import java.util.HashMap;

/**
 * Created by 13120dde on 2017-09-13.
 */
public class ReturnPacket{

    private boolean success = false;
    private String message = null;
    private HashMap<String, String> vals = null;

    public ReturnPacket(boolean success, String message){
        this.success=success;
        this.message=message;
    }

    public ReturnPacket(boolean success, HashMap<String, String> vals) {
        this.success=success;
        this.vals=vals;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String,String> getVals(){
        return vals;
    }
}
