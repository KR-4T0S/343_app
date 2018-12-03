package csulb.cecs343.lair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateProfileActivity extends AppCompatActivity {

    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        myDB = new DatabaseHelper(this);
        redirect();
    }

    public void redirect(){
        if ((myDB.getPincode().length() > 1) && (myDB.getUserTrained().equals("0"))){
            Intent intent3 = new Intent(CreateProfileActivity.this, TrainModelActivity.class);
            startActivity(intent3);
        }else if(myDB.getPincode().equals("")){
            Intent intent2 = new Intent(CreateProfileActivity.this, PincodeActivity.class);
            startActivity(intent2);
        }else{
            Toast.makeText(this, "Profile has already been created!", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(CreateProfileActivity.this, Main2Activity.class);
            startActivity(intent1);
        }
    }

}
