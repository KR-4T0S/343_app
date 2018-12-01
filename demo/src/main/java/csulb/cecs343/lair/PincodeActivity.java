package csulb.cecs343.lair;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.cameraview.demo.R;

public class PincodeActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDB;
    //   TestingDatabaseHelper myTDB;

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

    String pincode;
    String first_pincode_entry;
    String second_pincode_entry;
    // Integer pincode;
    // String combined = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode);

        myDB = new DatabaseHelper(this);
        //       myTDB = new TestingDatabaseHelper(this);
        pincode = "";
        first_pincode_entry = "";
        second_pincode_entry = "";

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
                break;
            case R.id.two_button:
                pincode = pincode + "2";
                break;
            case R.id.three_button:
                pincode = pincode + "3";
                break;
            case R.id.four_button:
                pincode = pincode + "4";
                break;
            case R.id.five_button:
                pincode = pincode + "5";
                break;
            case R.id.six_button:
                pincode = pincode + "6";
                break;
            case R.id.seven_button:
                pincode = pincode + "7";
                /*
                Integer deletedRows = myTDB.deleteData("1");
                if (deletedRows > 0){
                    Toast.makeText(this, "Data deleted.", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(this, "Data not deleted. ", Toast.LENGTH_SHORT).show();
                }
                */
                break;
            case R.id.eight_button:
                pincode = pincode + "8";

                /*

                Cursor res = myTDB.getAllData();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error", "Nothing found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Surname : " + res.getString(2) + "\n");
                    buffer.append("Marks : " + res.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());
                res.close();
                */
                break;
            case R.id.nine_button:
                /*
                boolean isUpdated = myTDB.updateData("1", "John", "Benson", "F");
                if (isUpdated == true){
                    Toast.makeText(this, "Data Updated successfully. ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "ERROR ", Toast.LENGTH_SHORT).show();
                }
                */
                pincode = pincode + "9";

                break;
            case R.id.zero_button:

                /*


                Cursor res = myDB.getResCount();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error", "Nothing found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("pincode : " + res.getString(1) + "\n");
                    buffer.append("userTrained : " + res.getString(2) + "\n");
                    buffer.append("FailedFace : " + res.getString(3) + "\n");
                    buffer.append("FailedPin : " + res.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());



                boolean isInserted = myTDB.insertData("", "Valjean", "F");
                if (isInserted == true){
                    Toast.makeText(this, "Data Inserted successfully. " + pincode, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "ERROR " + pincode, Toast.LENGTH_SHORT).show();
                }

                Cursor res = myTDB.getAllData();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error", "Nothing found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Surname : " + res.getString(2) + "\n");
                    buffer.append("Marks : " + res.getString(3) + "\n");
                }
                showMessage("Data", buffer.toString());

*/
                //    String stuff = "dsg";
                //    myDB.getPincode();
                //    myDB.initialData("k",false,0, 0);
                //    myDB.getPincode();
                // myDB.getUserTrained();
                Toast.makeText(this, "user trained: " + myDB.getUserTrained(), Toast.LENGTH_SHORT).show();
                pincode = pincode + "0";
                // String test = myDB.random_test();
                // String trying_pincode = myDB.getPincode();
                // Toast.makeText(this, test, Toast.LENGTH_SHORT).show();

                break;
            case R.id.new_pincode_button:

                //first check in pincode in the sqlite is null

                if ((!first_pincode_entry.equals("")) && (second_pincode_entry.equals(""))){
                    Toast.makeText(this, "Enter new pin code " + pincode, Toast.LENGTH_SHORT).show();
                    first_pincode_entry = "";
                }

                break;
            case R.id.confirm_button:

                if ((first_pincode_entry.equals("")) && (!pincode.equals(""))){
                    Toast.makeText(this, "Your first entry pin code is: " + pincode, Toast.LENGTH_SHORT).show();
                    first_pincode_entry = pincode;
                    pincode = "";
                }

                if ((second_pincode_entry.equals("")) && (!pincode.equals(""))){
                    second_pincode_entry = pincode;
                    pincode = "";
                    if (!first_pincode_entry.equals(second_pincode_entry)){
                        Toast.makeText(this, "Pin code doesn't match. Re-do pin code.", Toast.LENGTH_SHORT).show();
                        first_pincode_entry = "";
                        second_pincode_entry = "";
                    }
                    else{
                        myDB.updatePincode(first_pincode_entry);
                        //add pincode to SQLite database
                        //    myDB.getPincode();

                        Toast.makeText(this, "Your pin code is set: " + first_pincode_entry, Toast.LENGTH_SHORT).show();
                        first_pincode_entry = "";
                        second_pincode_entry = "";
                        myDB.close();

                        Intent pin = new Intent(PincodeActivity.this, Main2Activity.class);
                        startActivity(pin);
                    }
                }
                break;
        }
    }
}