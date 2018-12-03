package csulb.cecs343.lair;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserAuthenticationActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDB;
    FileDatabaseHelper myHDB;

    private Button one_button;
    private Button two_button;
    private Button three_button;
    private Button four_button;
    private Button five_button;
    private Button six_button;
    private Button seven_button;
    private Button eight_button;
    private Button nine_button;
    private Button zero_button;
    private Button clear;
    private Button confirm;

    String pincode;
    String pincode_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);

        myDB = new DatabaseHelper(this);
        myHDB = new FileDatabaseHelper(this);

        pincode = myDB.getPincode();
        pincode_entry = "";

        one_button = (Button)findViewById(R.id.one_button_1);
        two_button = (Button)findViewById(R.id.two_button_1);
        three_button = (Button)findViewById(R.id.three_button_1);
        four_button = (Button)findViewById(R.id.four_button_1);
        five_button = (Button)findViewById(R.id.five_button_1);
        six_button = (Button)findViewById(R.id.six_button_1);
        seven_button = (Button)findViewById(R.id.seven_button_1);
        eight_button = (Button)findViewById(R.id.eight_button_1);
        nine_button = (Button)findViewById(R.id.nine_button_1);
        zero_button = (Button)findViewById(R.id.zero_button_1);
        clear = (Button)findViewById(R.id.clear_button_1);
        confirm = (Button)findViewById(R.id.confirm_button_1);

        one_button.setOnClickListener(this);
        two_button.setOnClickListener(this);
        three_button.setOnClickListener(this);
        four_button.setOnClickListener(this);
        five_button.setOnClickListener(this);
        six_button.setOnClickListener(this);
        seven_button.setOnClickListener(this);
        eight_button.setOnClickListener(this);
        nine_button.setOnClickListener(this);
        zero_button.setOnClickListener(this);
        clear.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_button_1:
                pincode_entry = pincode_entry + "1";
                break;
            case R.id.two_button_1:
                pincode_entry = pincode_entry + "2";
                break;
            case R.id.three_button_1:
                pincode_entry = pincode_entry + "3";
                break;
            case R.id.four_button_1:
                pincode_entry = pincode_entry + "4";
                break;
            case R.id.five_button_1:
                pincode_entry = pincode_entry + "5";
                break;
            case R.id.six_button_1:
                pincode_entry = pincode_entry + "6";
                break;
            case R.id.seven_button_1:
                pincode_entry = pincode_entry + "7";
                break;
            case R.id.eight_button_1:
                pincode_entry = pincode_entry + "8";
                break;
            case R.id.nine_button_1:
                pincode_entry = pincode_entry + "9";
                break;
            case R.id.zero_button_1:
                pincode_entry = pincode_entry + "0";
                break;
            case R.id.clear_button_1:
                pincode_entry = "";
                break;
            case R.id.confirm_button_1:

                if (!pincode_entry.equals(pincode)){
                    pincode = "";
                    myDB.updateFailedPin(myDB.getFailedPin() + 1);

                    if (myDB.getFailedPin() > 2){
                        Toast.makeText(this, "Profile deleted due to security issues.", Toast.LENGTH_SHORT).show();
                        myDB.deleteDB(this);
                        myHDB.deleteDB(this);
                        myHDB.close();
                        myDB.close();;
                        Intent del = new Intent(UserAuthenticationActivity.this, Main2Activity.class);
                        startActivity(del);
                    } else{
                        Toast.makeText(this, "Incorrect PIN code. You have " +  (3 - myDB.getFailedPin()) + " attempts left.", Toast.LENGTH_SHORT).show();
                    }

                } else{
                    Toast.makeText(this, "Re-do facial recognition.", Toast.LENGTH_SHORT).show();
                    Intent redo = new Intent(UserAuthenticationActivity.this, TrainModelActivity.class);
                    startActivity(redo);
                }
                break;
        }
    }
    public void onBackPressed() {}
}
