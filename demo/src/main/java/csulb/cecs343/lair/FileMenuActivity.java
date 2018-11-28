package csulb.cecs343.lair;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileMenuActivity extends AppCompatActivity
{
    private Button mAddFileButton;
    private Button mAddFolderButton;
    private Button mAddDeleteButton;
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
                //Intent intent = new Intent(FileMenuActivity.this, FileMenuActivity.class);
                //startActivity(intent);
                //Button button = (Button) v;
                //((Button) v).setText("clicked");

                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("themes", Context.MODE_WORLD_WRITEABLE);

                File new_file =new File(directory.getAbsolutePath() + File.separator +  "folder_icon.png");
                try
                {
                    new_file.createNewFile();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.d("Create File", "File exists?"+new_file.exists());
            }
        }
        );

        mAddDeleteButton = (Button) findViewById(R.id.delete_folder_button);
        mAddDeleteButton.setOnClickListener(new View.OnClickListener()
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


        //mMenuList = (ListView) findViewById(R.id.menu_list);

        //ArrayList<String> testList = new ArrayList<>();

        //testList.add("Hello");
        //testList.add("World");

        List<Folder> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

/*        ArrayAdapter  adapter = new ArrayAdapter  (this, android.R.layout.simple_expandable_list_item_1, testList)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);
                if(position % 2 == 1)
                {
                    // Set a background color for ListView regular row/item
                    //view.setBackgroundColor(Color.parseColor("#403F3F"));
                    view.setBackgroundResource(android.R.color.transparent);
                }
                else
                {
                    // Set the background color for alternate row/item
                    //view.setBackgroundColor(Color.parseColor("#333333"));
                    view.setBackgroundResource(android.R.color.darker_gray);
                }
                return view;
            }
        };*/

        //mMenuList.setAdapter(adapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }

    public List<Folder> fill_with_data()
    {

        List<Folder> data = new ArrayList<>();

        data.add(new Folder("Batman vs Superman",R.drawable.ic_folder));
        data.add(new Folder("X-Men: Apocalypse", R.drawable.ic_folder));
        data.add(new Folder("Captain America: Civil War", R.drawable.ic_folder));
        data.add(new Folder("Kung Fu Panda 3", R.drawable.ic_folder));
        data.add(new Folder("Warcraft", R.drawable.ic_folder));
        data.add(new Folder("Alice in Wonderland", R.drawable.ic_folder));

        return data;
    }

}
