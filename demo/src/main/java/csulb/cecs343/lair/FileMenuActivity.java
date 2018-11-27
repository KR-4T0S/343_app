package csulb.cecs343.lair;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FileMenuActivity extends AppCompatActivity
{
    private Button mAddFileButton;
    private Button mAddFolderButton;
    private ListView mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemenu);

        mAddFileButton = (Button) findViewById(R.id.add_file_button);
        mAddFileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(Main2Activity.this,
                //                R.string.file_menu_toast,
                //                Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FileMenuActivity.this, FileMenuActivity.class);
                startActivity(intent);
            }
        }
        );

        mAddFolderButton = (Button) findViewById(R.id.add_folder_button);
        mAddFolderButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(Main2Activity.this,
                //                R.string.file_menu_toast,
                //                Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FileMenuActivity.this, FileMenuActivity.class);
                startActivity(intent);
            }
        }
        );

        mMenuList = (ListView) findViewById(R.id.menu_list);

        ArrayList<String> testList = new ArrayList<>();

        testList.add("Hello");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");
        testList.add("World");


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, testList);

        mMenuList.setAdapter(adapter);
    }
}
