package a13solutions.myEco.model;

import android.util.Log;

import a13solutions.myEco.MainActivity;
import a13solutions.myEco.dbHelper.DBManager;

/**
 * Created by 13120dde on 2017-09-13.
 */

public class LogicRegister {

    private MainActivity activity;
    private DBManager dbManager;


    public LogicRegister(MainActivity activity) {
        this.activity=activity;
        dbManager = new DBManager(activity);

    }

    /**Validates the password to match the password requirements. The requirements are: minlength=8,
     * at least one digit and uppercase letter.
     *
     * @param password : String
     * @return : boolean
     */
    public ReturnPacket checkPassword(String password, String passwordRepeat) {

        String message="";
        boolean isSuccess=true;

        if(!password.equals(passwordRepeat)){
            message+="Passwords do not match.\n";
            isSuccess=false;
        }else{
            //Password length
            if(password.length()<8){
                message+="Password is too short (min 8 characters.)\n";
                isSuccess=false;
            }
            //password contains a number and uppercase
            boolean isDigit=false, isUpperCase=false;
            for (char c : password.toCharArray()){
                if(Character.isDigit(c)){
                    isDigit=true;
                }
                if(Character.isUpperCase(c)){
                    isUpperCase = true;
                }
            }
            if(!isDigit){
                message+="Password must contain at least one digit.\n";
                isSuccess=false;
            }
            if(!isUpperCase){
                message+="Password must contain at least one upper-case letter.\n";
                isSuccess=false;
            }
        }
        return new ReturnPacket(isSuccess,message);
    }

    /**Validates the email to see if input meets the email requirements. Doesn't check if the email
     * is already registered in the system, YET!
     *
     * @param email : String
     * @return : boolean
     */
    public ReturnPacket checkEmail(String email) {
        String message="";
        boolean isSuccess=true;
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ReturnPacket res = dbManager.getUserEmail(email);
            if(res.isSuccess()){
                isSuccess =false;
                message+=res.getMessage()+"is already registered";
            }
        }else{
            isSuccess=false;
            message+="Incorrect email format";
        }
        return new ReturnPacket(isSuccess,message);
    }

    public boolean registerAccount(String email , String password, String passwordRepeat, String firstName, String surname) {
        String resultTitle="Success", resultInfo="";
        boolean allSuccessfull=true;

        ReturnPacket result;

        result = checkEmail(email);
        if(!result.isSuccess()){
            resultTitle="Error";
            resultInfo+=result.getMessage()+"\n";
            allSuccessfull=result.isSuccess();
        }
        result = checkPassword(password,passwordRepeat);
        if(!result.isSuccess()){
            resultTitle="Error";
            resultInfo+=result.getMessage()+"\n";
            allSuccessfull=result.isSuccess();
        }

        if(firstName.isEmpty() || surname.isEmpty()){
            resultTitle="Error";
            resultInfo+="Enter your first and sur-name";
            allSuccessfull=false;
        }

        if(allSuccessfull){
            dbManager.registerUser(email,password,firstName,surname);
            Log.d("IN_email",email);

             UtilityMethods.populateEmptyTable(activity,email);

            resultInfo="Registration complete.\nProcede to login.";
            activity.showHomeFragment();

        }

        UtilityMethods.showNeutralDialog(resultTitle,resultInfo, activity);
        return allSuccessfull;
    }


}
