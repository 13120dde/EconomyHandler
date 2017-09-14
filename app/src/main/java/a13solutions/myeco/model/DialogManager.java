package a13solutions.myeco.model;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import a13solutions.myeco.MainActivity;

/**
 * Created by 13120dde on 2017-09-14.
 */

public class DialogManager {

    public static void showNeutralDialog(String title, String message, MainActivity activity){
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
