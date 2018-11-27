package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.cameraview.demo.R;
import csulb.cecs343.lair.R;

public class Main2Activity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mCreateProfileButton;
    private Button mFileMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Run instances of activities here
        init_app_login(); // this initiates with login (main) page, comment out for testing other activities.

    }

    // Testing and Debug Tools
    // Place all your testing here,
    private void init_app_login(){
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

        mFileMenuButton = (Button) findViewById(R.id.file_menu_button);
        mFileMenuButton.setOnClickListener(new View.OnClickListener()
                                           {
                                               @Override
                                               public void onClick(View v)
                                               {
                                                   //Toast.makeText(Main2Activity.this,
                                                   //                R.string.file_menu_toast,
                                                   //                Toast.LENGTH_SHORT).show();
                                                   Intent intent = new Intent(Main2Activity.this, FileMenuActivity.class);
                                                   startActivity(intent);
                                               }
                                           }
        );
    }
}
