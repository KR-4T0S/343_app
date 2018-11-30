package csulb.cecs343.lair;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class FileViewActivity extends AppCompatActivity {

    public static int VIDEO = 1;
    public static int IMAGE = 0;
    ImageView imgView;
    VideoView vidView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_fileview);

        // Get Parameters
        Bundle b = getIntent().getExtras();
        int _type = b.getInt("type");
        String _fileSource = b.getString("fileSource");
        String _fileName = b.getString("fileName");

        imgView = (ImageView)findViewById(R.id.fileview_imageview);
        vidView = (VideoView)findViewById(R.id.fileview_videoview);
        textView = (TextView)findViewById(R.id.fileview_text_filename);

        if (_type == VIDEO ) {
            imgView.setVisibility(View.INVISIBLE);
            vidView.setVisibility(View.VISIBLE);
            initializeVideo(_fileSource, _fileName);
        }
        if (_type == IMAGE) {
            imgView.setVisibility(View.INVISIBLE);
            vidView.setVisibility(View.VISIBLE);
            initializeImage(_fileSource, _fileName);
        }
    }

    private void initializeVideo(String _fileSource, String _fileName) {
        Uri uri = Uri.parse(_fileSource);
        vidView.setVideoURI(uri);
        textView.setText(_fileName);
    }

    private void initializeImage(String _fileSource, String _fileName) {
        File imgFile = new  File(_fileSource);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.fileview_imageview);
            myImage.setImageBitmap(myBitmap);
        }
        textView.setText(_fileName);
    }
}
