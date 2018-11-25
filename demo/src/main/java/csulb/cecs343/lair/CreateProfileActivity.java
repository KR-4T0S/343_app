package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.google.android.cameraview.demo.R;

public class CreateProfileActivity extends AppCompatActivity {

    private Button mCreatePinCodeButton;
    private Button mTrainModelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


        mCreatePinCodeButton = (Button)findViewById(R.id.create_pincode_button);
        mCreatePinCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(CreateProfileActivity.this, PincodeActivity.class);
                startActivity(intent2);
                //Does nothing yet, but soon!
            }
        });

        mTrainModelButton = (Button)findViewById(R.id.train_model_button);
        mTrainModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(CreateProfileActivity.this, TrainModelActivity.class);
                startActivity(intent3);
                //Does nothing yet, but soon!
            }
        });
    }


}
