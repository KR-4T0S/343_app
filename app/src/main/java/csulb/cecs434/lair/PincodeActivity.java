package csulb.cecs434.lair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PincodeActivity extends AppCompatActivity {
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


    Integer pincode;
    String combined = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode);

        myDB = new DatabaseHelper(this);

        one_button = (Button)findViewById(R.id.one_button);
        one_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "1";
                pincode = new Integer(Integer.parseInt(combined));
            }
        });

        two_button = (Button)findViewById(R.id.two_button);
        two_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "2";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });

        three_button = (Button)findViewById(R.id.three_button);
        three_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "3";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });

        four_button = (Button)findViewById(R.id.four_button);
        four_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "4";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });

        five_button = (Button)findViewById(R.id.five_button);
        five_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "5";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        six_button = (Button)findViewById(R.id.six_button);
        six_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "6";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        seven_button = (Button)findViewById(R.id.seven_button);
        seven_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "7";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        eight_button = (Button)findViewById(R.id.eight_button);
        eight_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "8";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        nine_button = (Button)findViewById(R.id.nine_button);
        nine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "9";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        zero_button = (Button)findViewById(R.id.zero_button);
        zero_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combined = Integer.toString(pincode) + "0";
                pincode = new Integer(Integer.parseInt(combined));

            }
        });
        new_pincode = (Button)findViewById(R.id.new_pincode_button);
        new_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        confirm = (Button)findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}
