package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.cameraview.demo.R;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreatePinCodeButton;
    private Button mTrainModelButton;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        myDB = new DatabaseHelper(this);

        mCreatePinCodeButton = (Button) findViewById(R.id.create_pincode_button);
        mTrainModelButton = (Button) findViewById(R.id.train_model_button);

        mCreatePinCodeButton.setOnClickListener(this);
        mTrainModelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_pincode_button:
                if (!myDB.getPincode().equals("")) {
                    Toast.makeText(this, "Pincode has already been registered. You can update it in Profile Menu.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(CreateProfileActivity.this, PincodeActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.train_model_button:
                if (!myDB.getUserTrained().equals("0")) {
                    Toast.makeText(this, "Facial Recognitions has already been registered. You can update it in Profile Menu.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent3 = new Intent(CreateProfileActivity.this, TrainModelActivity.class);
                    startActivity(intent3);
                }
                break;
        }
    }
}
