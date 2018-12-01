package csulb.cecs343.lair;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private Button mProfileMenu;

    //random comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Run other tests here BEFORE running any activity
        // init_test_filedb(); // test file db

        // Run instances of activities here
        init_app_login(); // this initiates with login (main) page, comment out for testing other activities.
        //init_test_fileview();

    }

    // Testing and Debug Tools
    // Place all your testing here,
    private void init_app_login(){
        setContentView(R.layout.activity_main2);


        setContentView(R.layout.activity_main2);
        mProfileMenu = (Button)findViewById(R.id.profile_menu_button);
        mProfileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Does nothing yet, but soon!

                Intent profile = new Intent(Main2Activity.this, ProfileMenuActivity.class);
                startActivity(profile);
            }
        });



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

    private void init_test_filedb() {
        // START Initiate db //
        FileDatabaseHelper db = new FileDatabaseHelper(this);
        db.getWritableDatabase(); // THIS IS REQUIRED
        // END Initiate db //

        db.addFolder("ROOT", "/", "");
        db.addFolder("videos", "/root","1");
        db.addFolder("pictures", "/root", "1");

        db.addFile("test file", "jpg","/root", "1");
        db.addFile("Fido at the beach", "mp4", "/root/videos","2");
        db.addFile("cat playing piano", "mp4", "/root/videos","2");
        db.addFile("evolution of dance", "mp4", "/root/videos","2");
        db.addFile("family reunion 2016", "png", "/root/pictures","3");
        db.addFile("totally not nudes", "png", "/root/pictures","3");

        db.close();
        db.deleteDB(this);
    }

    private void init_test_fileview() {
        // Test Image
//        String fileSource = "/data/data/com.google.android.cameraview.demo/files/kHPxj2F.jpg";
//        String fileName = "Test Image";
//        Intent fileview = new Intent(Main2Activity.this, FileViewActivity.class);
//        fileview.putExtra("fileSource", fileSource);
//        fileview.putExtra("fileName", fileName);
//        fileview.putExtra("type", FileViewActivity.IMAGE);

        // Test Video
        String vidSource = "/data/data/com.google.android.cameraview.demo/files/infinite dab.mp4";
        String vidName = "Infinite Dab";
        Intent fileview = new Intent(Main2Activity.this, FileViewActivity.class);
        fileview.putExtra("fileSource", vidSource);
        fileview.putExtra("fileName", vidName);
        fileview.putExtra("type", FileViewActivity.VIDEO);

        startActivity(fileview);
    }
    @Override
    public void onBackPressed() {}
}
