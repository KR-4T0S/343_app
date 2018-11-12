package csulb.cecs434.lair;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrainModelActivity extends AppCompatActivity {
    DatabaseHelper myDb;
/*
    private ImageButton mTrainingPhotoButton;
    private ImageView mTrainingPhotoView;
    private static final int REQUEST_PHOTO = 2;
    final Context mContext = this;
    final File mPhotoFile = getPhotoFile();
    final PackageManager packageManager = this.getPackageManager();

*/


    private ImageButton mTrainingPhotoButton;
    private ImageView mTrainingPhotoView;
    private ArrayList<String> directories;
    static final int CAM_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_model);
        myDb = new DatabaseHelper(this);


        mTrainingPhotoButton = (ImageButton) findViewById(R.id.training_camera);
        mTrainingPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directories = new ArrayList<>();

/*
                Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                intent4.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent4, CAM_REQUEST);

*/

                Intent intent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              //  File file = getFile();
              //  intent4.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));


                startActivityForResult(intent4,0);


            }
        });

        mTrainingPhotoView = (ImageView) findViewById(R.id.training_photo);


/*


        mTrainingPhotoButton = (ImageButton) findViewById(R.id.training_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mTrainingPhotoButton.setEnabled(canTakePhoto);

        mTrainingPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = FileProvider.getUriForFile(mContext,"csulb.cecs434.lair.fileprovider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities = packageManager.queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : cameraActivities){
                    mContext.grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });




        mTrainingPhotoView = (ImageView) findViewById(R.id.training_photo);
 //       updatePhotoView();
 */
    }

    private void init(){
        //"storage/emulated/0"
        String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
        String PICTURES = ROOT_DIR + "/Pictures";
        String CAMERA = ROOT_DIR + "/DCIM/camera";

        if(getDirectoryPaths(PICTURES) != null){
            directories = getDirectoryPaths(PICTURES);
        }
        directories.add(CAMERA);

       // ArrayAdapter<String> adapter new

    }
    /**
     * Search a dictory and return a list of all **directory** contained inside
     * @param directory
     * @return
     */
    private ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String>pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listfiles = file.listFiles();
        for(int i = 0; i < listfiles.length; i++){
            if(listfiles[i].isDirectory()){
                pathArray.add(listfiles[i].getAbsolutePath());
            }
        }
        return pathArray;
    }

    /**
     * Search a dictory and return a list of all **files** contained inside
     * @param directory
     * @return
     */
    private ArrayList<String> getFilePaths(String directory){
        ArrayList<String>pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listfiles = file.listFiles();
        for(int i = 0; i < listfiles.length; i++){
            if(listfiles[i].isFile()){
                pathArray.add(listfiles[i].getAbsolutePath());
            }
        }
        return pathArray;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

    //    if (requestCode == CAM_REQUEST && resultCode == RESULT_OK){

    //        Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            mTrainingPhotoView.setImageBitmap(bitmap);




    //    super.onActivityResult(requestCode, resultCode, data);
    //    String path = "sdcard/camera_app/training_photo.jpg";
    //    mTrainingPhotoView.setImageDrawable(Drawable.createFromPath(path));


 //       Bitmap bitmap = (Bitmap)data.getExtras().get("data");
 //       mTrainingPhotoView.setImageBitmap(bitmap);

    }

    private File getFile(){
        File folder = new File("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File image_file = new File(folder, "training_photo.jpg");
        return image_file;
    }

 /*
    public void onActivityResult(int requestCode, int resultCode, Intent data ){
        if (requestCode == REQUEST_PHOTO){
            Uri uri = FileProvider.getUriForFile(mContext, "csulb.cecs434.lair.fileprovider", mPhotoFile);
            mContext.revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            updatePhotoView();
        }

    }
*/

//    private void updatePhotoView(){
//        if(mPhotoFile == null || !mPhotoFile.exists()){
//            mTrainingPhotoView.setImageDrawable(null);
//        }else{
//            Bitmap bitmap = PictureUtils.getScaleBitmap(
//                    mPhotoFile.getPath(), this);
//            mTrainingPhotoView.setImageBitmap(bitmap);
//        }
//    }

//    public File getPhotoFile(){
//        File filesDir = this.getFilesDir();
//        return new File(filesDir,"training_photo.jpg" );
//    }

}
