package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.google.android.cameraview.demo.R;
import csulb.cecs343.lair.R;

public class Main2Activity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mCreateProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Does nothing yet, but soon!
                Intent login = new Intent(Main2Activity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        mCreateProfileButton = (Button)findViewById(R.id.create_profile_button);
        mCreateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Does nothing yet, but soon!
                Intent intent = new Intent(Main2Activity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
