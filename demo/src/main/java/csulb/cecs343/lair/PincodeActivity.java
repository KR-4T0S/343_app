package csulb.cecs343.lair;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PincodeActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDB;

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
    private Button new_pincode;
    private Button confirm;
    private TextView pin_text;

    String pincode;
    String first_pincode_entry;
    String second_pincode_entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode);

        myDB = new DatabaseHelper(this);
        pincode = "";
        first_pincode_entry = "";
        second_pincode_entry = "";
        Toast.makeText(this, "Enter desired PIN code twice to confirm it." + pincode, Toast.LENGTH_SHORT).show();

        one_button = (Button)findViewById(R.id.one_button);
        two_button = (Button)findViewById(R.id.two_button);
        three_button = (Button)findViewById(R.id.three_button);
        four_button = (Button)findViewById(R.id.four_button);
        five_button = (Button)findViewById(R.id.five_button);
        six_button = (Button)findViewById(R.id.six_button);
        seven_button = (Button)findViewById(R.id.seven_button);
        eight_button = (Button)findViewById(R.id.eight_button);
        nine_button = (Button)findViewById(R.id.nine_button);
        zero_button = (Button)findViewById(R.id.zero_button);
        new_pincode = (Button)findViewById(R.id.new_pincode_button);
        confirm = (Button)findViewById(R.id.confirm_button);
        pin_text = (TextView) findViewById(R.id.pincode_text_pin);

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
        new_pincode.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_button:
                pincode = pincode + "1";
                pin_text.setText(pincode);
                break;
            case R.id.two_button:
                pincode = pincode + "2";
                pin_text.setText(pincode);
                break;
            case R.id.three_button:
                pincode = pincode + "3";
                pin_text.setText(pincode);
                break;
            case R.id.four_button:
                pincode = pincode + "4";
                pin_text.setText(pincode);
                break;
            case R.id.five_button:
                pincode = pincode + "5";
                pin_text.setText(pincode);
                break;
            case R.id.six_button:
                pincode = pincode + "6";
                pin_text.setText(pincode);
                break;
            case R.id.seven_button:
                pincode = pincode + "7";
                pin_text.setText(pincode);
                break;
            case R.id.eight_button:
                pincode = pincode + "8";
                pin_text.setText(pincode);
                break;
            case R.id.nine_button:
                pincode = pincode + "9";
                pin_text.setText(pincode);
                break;
            case R.id.zero_button:
                pincode = pincode + "0";
                pin_text.setText(pincode);
                break;
            case R.id.new_pincode_button:
                Toast.makeText(this, "PIN Cleared, Enter new PIN code", Toast.LENGTH_SHORT).show();
                confirm.setText("Enter");
                pincode = "";
                first_pincode_entry = "";
                second_pincode_entry = "";
                pin_text.setText("****");
                break;
            case R.id.confirm_button:

                if ((first_pincode_entry.equals("")) && (!pincode.equals(""))){
                    first_pincode_entry = pincode;
                    pincode = "";
                    pin_text.setText("****");
                    confirm.setText("Confirm PIN");
                }

                if ((second_pincode_entry.equals("")) && (!pincode.equals(""))){
                    second_pincode_entry = pincode;
                    pincode = "";
                    if (!first_pincode_entry.equals(second_pincode_entry)){
                        Toast.makeText(this, "Pin code doesn't match. Re-do PIN code.", Toast.LENGTH_SHORT).show();
                        pin_text.setText("****");
                        first_pincode_entry = "";
                        second_pincode_entry = "";
                    }
                    else{
                        myDB.updatePincode(first_pincode_entry);
                        Toast.makeText(this, "Your pin code is set: " + first_pincode_entry, Toast.LENGTH_SHORT).show();
                        first_pincode_entry = "";
                        second_pincode_entry = "";

                        if (myDB.getUserTrained().equals("0")){
                            myDB.close();
                            Intent pin = new Intent(PincodeActivity.this, TrainModelActivity.class);
                            startActivity(pin);
                        }else{
                            Intent back = new Intent(PincodeActivity.this, ProfileMenuActivity.class);
                            startActivity(back);
                        }
                    }
                }
                break;
        }
    }
}