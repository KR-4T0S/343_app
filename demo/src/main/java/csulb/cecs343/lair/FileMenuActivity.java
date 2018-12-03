package csulb.cecs343.lair;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.NonNull;
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

import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static java.security.AccessController.getContext;



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
    TextView textView;
    private final List<String> IMAGE_EXTENSIONS = new ArrayList<>(Arrays.asList("jpg", "png"));
    private final List<String> VIDEO_EXTENSIONS = new ArrayList<>(Arrays.asList("mp4"));

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemenu);
        textView = (TextView) findViewById(R.id.textView3);

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

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }


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
                new MaterialFilePicker()
                        .withActivity(FileMenuActivity.this)
                        .withRequestCode(1000)
                        //.withFilter(Pattern.compile(".*\\.jpg$")) // Filtering files and directories by file name using regexp
                        //.withFilterDirectories(true) // Set directories filterable (false by default)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

                //AlertDialog.Builder builder = new AlertDialog.Builder(FileMenuActivity.this);
                //builder.setTitle("Enter The File Name");

                //final EditText input = new EditText(FileMenuActivity.this);
                //input.setInputType(InputType.TYPE_CLASS_TEXT);
                //builder.setView(input);

                /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mInputResult = input.getText().toString();

                        int position = 0;

                        for (Element e : mData)
                        {
                            if (!e.isFolder())
                            {
                                break;
                            }
                            position++;
                        }

                        String title = mInputResult;
                        mData.add(position, new Element(title, R.drawable.ic_file, false, false, true));
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
                */

                //builder.show();
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
                        mData.add(position, new Element(title, "", 0, R.drawable.ic_folder, false, true, false));
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
                // Do nothing for now, temp solution?
            }

            @Override
            public void onLongClick(View view, int position)
            {
                if (mData.get(position).isFolder())
                {
                    Intent intent = new Intent(FileMenuActivity.this, FileMenuActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Element e = mData.get(position);

                    Intent fileView = new Intent(FileMenuActivity.this, FileViewActivity.class);
                    fileView.putExtra("fileSource", e.path);
                    fileView.putExtra("fileName", e.title);
                    fileView.putExtra("type", e.type);

                    startActivity(fileView);
                }
            }
        }
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK)
        {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
            File path = getFilesDir();
            File storeIn = new File(path, filePath);

            int fileType = 0;
            String ext = "";

            int i = fileName.lastIndexOf('.');
            if (i > 0)
            {
                ext = fileName.substring(i + 1);
            }

            if (VIDEO_EXTENSIONS.contains(ext))
            {
                fileType = 1;
            }
            else
            {
                fileType = 0;
            }

            int position = mData.size();
            mData.add(position, new Element(fileName, filePath, fileType, R.drawable.ic_file, false, false, true));
            mAdapter.notifyItemInserted(position);
            mRecyclerView.scrollToPosition(position);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case 1001:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}
