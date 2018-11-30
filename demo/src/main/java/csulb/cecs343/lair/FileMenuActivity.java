package csulb.cecs343.lair;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Set;


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
    private String mInputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemenu);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.items);
        mData = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setAutoMeasureEnabled(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(mData, mContext);
        mRecyclerView.setAdapter(mAdapter);

/*        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        mRecyclerView.setItemAnimator(itemAnimator);*/

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
                AlertDialog.Builder builder = new AlertDialog.Builder(FileMenuActivity.this);
                builder.setTitle("Enter The Folder Name");

                final EditText input = new EditText(FileMenuActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mInputResult = input.getText().toString();

                        int position = 0;
                        String title = mInputResult;
                        mData.add(position, new Element(title, R.drawable.ic_folder, false));
                        mAdapter.notifyItemInserted(position);
                        mRecyclerView.scrollToPosition(position);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });

                builder.show();
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

        mRecyclerView.addOnItemTouchListener(new RecyclerViewListenerHelper(this, mRecyclerView, new RecyclerViewItemClickListener()
        {
            @Override
            public void onClick(View view, int position)
            {
                Toast.makeText(FileMenuActivity.this, "Short Click: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position)
            {
                Toast.makeText(FileMenuActivity.this, "Long Click: " + position, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
