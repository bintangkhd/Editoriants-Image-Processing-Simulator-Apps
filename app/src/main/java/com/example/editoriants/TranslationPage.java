package com.example.editoriants;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TranslationPage extends Activity {

    private Button btnTakePictureTranslation;
    private ImageView imageViewBeforeTranslation;
    private ImageView imageViewAfterTranslation;

    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tranlation_page);

        imageViewBeforeTranslation = (ImageView) findViewById(R.id.imageViewBeforeTranslation);
        imageViewAfterTranslation = (ImageView) findViewById(R.id.imageViewAfterTranslation);


        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(TranslationPage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TranslationPage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        btnTakePictureTranslation = (Button)findViewById(R.id.buttonTakePictureTranslation);

        btnTakePictureTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

    }

    private Bitmap translationImage(Bitmap bm) {
        Bitmap temp = bm.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap resultBitmap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.ARGB_8888);

        int x_offset = 50;
        int y_offset = 50;

        int width = temp.getWidth();
        int height = temp.getHeight();

        int A, R, G, B;
        int colorPixel = 0;

        for(int  i = 0; i < width && i + x_offset < width; i++) {
            for (int j = 0; j < height && j + y_offset < height; j++) {
                colorPixel = temp.getPixel(i, j);
                R = Color.red(colorPixel);
                G = Color.green(colorPixel);
                B = Color.blue(colorPixel);
                A = Color.alpha(colorPixel);

                resultBitmap.setPixel(i + x_offset, j + y_offset, Color.argb(A, R, G, B));
            }
        }

        return resultBitmap;
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }

        imageViewBeforeTranslation.setImageBitmap(bitmap);
        Bitmap bitmapAfter = translationImage(bitmap);
        imageViewAfterTranslation.setImageBitmap(bitmapAfter);


    }
}
