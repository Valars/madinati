package sif.ali.questionnaire1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import sif.ali.questionnaire1.Model.Model;

public class CameraActivity extends AppCompatActivity {

    Button buttonCapture;
    ImageView imageView;
    Button validerImage;
    static final int CAM_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        Intent intent=getIntent();
        final Model model=(Model)intent.getSerializableExtra("Model");

        buttonCapture=(Button)findViewById(R.id.ButtonCapture);
        imageView=(ImageView)findViewById(R.id.image_view);
        validerImage=(Button)findViewById(R.id.buttonValiderImage);

        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file=getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                //conversion en base64
                Bitmap bm= BitmapFactory.decodeFile(file.getPath());
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                byte[] b=baos.toByteArray();
                String encodedImage= Base64.encodeToString(b, Base64.NO_WRAP);
                //Log.d("Mon Image64",encodedImage);
                model.setImage(encodedImage);
                model.setPath_image(file.getPath());

                //model.setPath_image(file.getPath());
                startActivityForResult(camera_intent, CAM_REQUEST);


            }
        });

        validerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CameraActivity.this,MenuChoix.class);
                //Log.d("3_Send", model.toString());
                intent1.putExtra("Model", model);
                startActivity(intent1);
            }
        });

    }


    private File getFile()
    {
        File folder= new File("sdcard/camera_app");
        if(!folder.exists())
        {
            folder.mkdir();
        }
        File image_file=new File(folder, "cam_image.jpg");
        return image_file;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String path="sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }

}
