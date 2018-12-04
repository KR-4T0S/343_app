package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private TextView pin_text;

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

        one_button = (Button)findViewById(R.id.auth_one_button);
        two_button = (Button)findViewById(R.id.auth_two_button);
        three_button = (Button)findViewById(R.id.auth_three_button);
        four_button = (Button)findViewById(R.id.auth_four_button);
        five_button = (Button)findViewById(R.id.auth_five_button);
        six_button = (Button)findViewById(R.id.auth_six_button);
        seven_button = (Button)findViewById(R.id.auth_seven_button);
        eight_button = (Button)findViewById(R.id.auth_eight_button);
        nine_button = (Button)findViewById(R.id.auth_nine_button);
        zero_button = (Button)findViewById(R.id.auth_zero_button);
        clear = (Button)findViewById(R.id.auth_new_pincode_button);
        confirm = (Button)findViewById(R.id.auth_confirm_button);
        pin_text = (TextView) findViewById(R.id.auth_text_pin);

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
            case R.id.auth_one_button:
                pincode_entry = pincode_entry + "1";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_two_button:
                pincode_entry = pincode_entry + "2";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_three_button:
                pincode_entry = pincode_entry + "3";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_four_button:
                pincode_entry = pincode_entry + "4";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_five_button:
                pincode_entry = pincode_entry + "5";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_six_button:
                pincode_entry = pincode_entry + "6";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_seven_button:
                pincode_entry = pincode_entry + "7";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_eight_button:
                pincode_entry = pincode_entry + "8";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_nine_button:
                pincode_entry = pincode_entry + "9";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_zero_button:
                pincode_entry = pincode_entry + "0";
                pin_text.setText(pincode_entry);
                break;
            case R.id.auth_new_pincode_button:
                pincode_entry = "";
                pin_text.setText("****");
                break;
            case R.id.auth_confirm_button:

                if (!pincode_entry.equals(pincode)){
                    pincode_entry = "";
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
                } else {
                    Toast.makeText(this, "Re-do facial recognition.", Toast.LENGTH_SHORT).show();
                    myDB.updateFailedPin(0); // Resets failed count after a single success.
                    myDB.updateFailedRec(0); // Resets failed count after a single success.
                    Intent redo = new Intent(UserAuthenticationActivity.this, TrainModelActivity.class);
                    startActivity(redo);
                }
                break;
        }
    }
    public void onBackPressed() {}
}
