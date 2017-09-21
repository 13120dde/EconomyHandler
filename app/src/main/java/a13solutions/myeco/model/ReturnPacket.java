package a13solutions.myEco.model;

import java.util.HashMap;

/**
 * Created by 13120dde on 2017-09-13.
 */
public class ReturnPacket{

    private boolean success;
    private String message;
    private HashMap<String, String> vals;
    private String scanValue;
    private double amount;
    private int scanId;

    private String title, category;

    public String getScanValue() {
        return scanValue;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setVals(HashMap<String, String> vals) {
        this.vals = vals;
    }

    public String getScanContent() {
        return scanValue;
    }

    public void setScanContent(String scanValue) {
        this.scanValue = scanValue;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ReturnPacket(boolean success, String message){
        this.success=success;
        this.message=message;
    }

    public ReturnPacket(boolean success, HashMap<String, String> vals) {
        this.success=success;
        this.vals=vals;
    }

    public ReturnPacket() {

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

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public int getScanId() {
        return scanId;
    }
}
