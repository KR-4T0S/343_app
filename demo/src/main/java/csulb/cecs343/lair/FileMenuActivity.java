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
import android.util.SparseBooleanArray;
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
import java.util.Collections;
import java.util.List;


public class FileMenuActivity extends AppCompatActivity
{
    private Context mContext;
    private Button mAddFileButton;
    private Button mAddFolderButton;
    private Button mDeleteButton;
    private List<Element> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemenu);

        mContext = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.items);

        //mData = fill_with_data();

        mData = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewAdapter(mData, mContext);
        mRecyclerView.setAdapter(mAdapter);

/*      RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);*/

        controls();
    }

    public void controls()
    {
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

                /*ContextWrapper cw = new ContextWrapper(getApplicationContext());
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
                Log.d("Create File", "File exists?"+new_file.exists());*/
                int position = 0;
                mData.add(position, new Element("Test", R.drawable.ic_folder, false));
                mAdapter.notifyItemInserted(position);
                mRecyclerView.scrollToPosition(position);
            }
        }
        );

        mDeleteButton = (Button) findViewById(R.id.delete_folder_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int position = 0;
                List<Integer> positions = new ArrayList<>();

                for (Element e : mData)
                {
                    if (e.isSelected())
                    {
                        positions.add(position);
                    }

                    position++;
                }

                int dataSize = mData.size();

                for (int i = dataSize - 1; i >= 0; i--)
                {
                    if (positions.contains(i))
                    {
                        mData.remove(i);
                        mAdapter.notifyItemRemoved(i);
                    }
                }
            }
        }
        );
    }

    public List<Element> fill_with_data()
    {

        List<Element> data = new ArrayList<>();

        data.add(new Element("Batman vs Superman", R.drawable.ic_folder, false));
        data.add(new Element("X-Men: Apocalypse", R.drawable.ic_folder, false));
        data.add(new Element("Captain America: Civil War", R.drawable.ic_folder, false));
        data.add(new Element("Kung Fu Panda 3", R.drawable.ic_file, false));
        data.add(new Element("Warcraft", R.drawable.ic_file, false));
        data.add(new Element("Alice in Wonderland", R.drawable.ic_file, false));

        return data;
    }

}
