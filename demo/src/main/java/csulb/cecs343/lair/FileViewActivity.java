package csulb.cecs343.lair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class FileViewActivity extends AppCompatActivity {
    private static final String TAG = "FileViewActivity";
    public static int VIDEO = 1;
    public static int IMAGE = 0;
    ImageView imgView;
    VideoView vidView;
    TextView textView;
    MediaController mediaController;
    private int type;
    private String fileSource;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_fileview);

        Log.v(TAG, "onCreate: began execution");
        // Get Parameters
        Bundle b = getIntent().getExtras();
        int _type = b.getInt("type");
        String _fileSource = b.getString("fileSource");
        String _fileName = b.getString("fileName");

        mediaController = new MediaController(this);
        imgView = (ImageView)findViewById(R.id.fileview_imageview);
        vidView = (VideoView)findViewById(R.id.fileview_videoview);
        textView = (TextView)findViewById(R.id.fileview_text_filename);
        mediaController.setAnchorView(vidView);
        vidView.setMediaController(mediaController);

        type = _type;
        fileSource = _fileSource;
        fileName = _fileName;

        display(type, fileSource, fileName);
        Log.v(TAG, "onCreate: executed successfully");
    }

    public void display(int _type, String _fileSource, String _fileName) {
        Log.v(TAG, "display: began execution");
        if (_type == VIDEO ) {
                imgView.setVisibility(View.INVISIBLE);
                vidView.setVisibility(View.VISIBLE);
                initializeVideo(_fileSource, _fileName);
        } else {
                mediaController.setVisibility(View.INVISIBLE);
                vidView.setVisibility(View.INVISIBLE);
                imgView.setVisibility(View.VISIBLE);
                initializeImage(_fileSource, _fileName);
        }
        Log.v(TAG, "display: executed successfully");
    }

    private void initializeVideo(String _fileSource, String _fileName) {
        Log.v(TAG, "initializeVideo: began execution");
        vidView.setZOrderOnTop(true);
        Uri uri = Uri.parse(_fileSource);
        vidView.setVideoURI(uri);
        textView.setText(_fileName); // Change page title on bar to file name
        vidView.requestFocus();
        vidView.start();
        Log.v(TAG, "initializeVideo: executed successfully");
    }


    private void initializeImage(String _fileSource, String _fileName) {
        Log.v(TAG, "initializeImage: began execution");
        imgView.bringToFront();
        Bitmap myBitmap = BitmapFactory.decodeFile(_fileSource);
        imgView.setImageBitmap(myBitmap);
        textView.setText(_fileName); // Change page title on bar to file name
        Log.v(TAG, "initializeImage: executed successfully");
    }
}
