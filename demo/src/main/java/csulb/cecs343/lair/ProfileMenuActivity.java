package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLogOutButton;
    private Button mUpdatePincodeButton;
    private Button mDeleteProfileButton;
    private Button mUpdateFacialRecognitionButton;
    private Button mFile2MenuButton;

    DatabaseHelper myDB;
    FileDatabaseHelper myFDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);

        myDB = new DatabaseHelper(this);
        myFDB = new FileDatabaseHelper(this);

        mUpdatePincodeButton = (Button)findViewById(R.id.update_pincode_button);
        mLogOutButton = (Button)findViewById(R.id.log_out_button);
        mDeleteProfileButton = (Button)findViewById(R.id.delete_profile_button);
        mUpdateFacialRecognitionButton = (Button)findViewById(R.id.update_facial_recognition_button);
        mFile2MenuButton = (Button)findViewById(R.id.file2_menu_button);

        mUpdatePincodeButton.setOnClickListener(this);
        mLogOutButton.setOnClickListener(this);
        mDeleteProfileButton.setOnClickListener(this);
        mUpdateFacialRecognitionButton.setOnClickListener(this);
        mFile2MenuButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_pincode_button:
                Toast.makeText(this, "Reset your pincode." , Toast.LENGTH_SHORT).show();
                Intent reset = new Intent(ProfileMenuActivity.this,  PincodeActivity.class);
                startActivity(reset);
                break;
            case R.id.log_out_button:
                Intent logout = new Intent(ProfileMenuActivity.this,  Main2Activity.class);
                startActivity(logout);
                break;
            case R.id.delete_profile_button:
                myDB.deleteDB(this);
                myFDB.deleteDB(this);
                myFDB.close();
                myDB.close();
                Intent delete = new Intent(ProfileMenuActivity.this,  Main2Activity.class);
                startActivity(delete);
                Toast.makeText(this, "Your profile was deleted" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_facial_recognition_button:
                Intent rec = new Intent(ProfileMenuActivity.this,  TrainModelActivity.class);
                startActivity(rec);
                break;
            case R.id.file2_menu_button:
                Intent fil = new Intent(ProfileMenuActivity.this,  FileMenuActivity.class);
                startActivity(fil);
                break;
        }
    }
}