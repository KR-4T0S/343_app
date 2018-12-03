package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mCreateProfileButton;
    private Button mFileMenuButton;
    private Button mProfileMenu;

    //random comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Run instances of activities here
        init_app_login(); // this initiates with login (main) page, comment out for testing other activities.
    }

    private void init_app_login(){
        setContentView(R.layout.activity_main2);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Main2Activity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        mCreateProfileButton = (Button)findViewById(R.id.create_profile_button);
        mCreateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
