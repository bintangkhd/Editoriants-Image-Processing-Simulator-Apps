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

public class RotationPage extends Activity {

    private Button btnTakePictureRotation;
    private ImageView imageViewBeforeRotation;
    private ImageView imageViewAfterRotation;

    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotation_page);

        imageViewBeforeRotation = (ImageView) findViewById(R.id.imageViewBeforeRotation);
        imageViewAfterRotation = (ImageView) findViewById(R.id.imageViewAfterRotation);


        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(RotationPage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RotationPage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        btnTakePictureRotation = (Button)findViewById(R.id.buttonTakePictureRotation);

        btnTakePictureRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

    }

    private Bitmap rotationImage(Bitmap bm) {
        Bitmap temp = bm.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap resultBitmap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.ARGB_8888);

        int width = temp.getWidth();
        int height = temp.getHeight();

        int A, R, G, B;
        int colorPixel = 0;

        int count = 0;
        do {
            for (int i = 0, y = height - 1; i < height; i++, y--) {
                for (int j = 0, x = width - 1; j < width; j++, x--) {
                    colorPixel = temp.getPixel(i, j);
                    R = Color.red(colorPixel);
                    G = Color.green(colorPixel);
                    B = Color.blue(colorPixel);
                    A = Color.alpha(colorPixel);

                    resultBitmap.setPixel(j, y, Color.argb(A, R, G, B));
                }
            }
            temp = resultBitmap;
            count++;

            if (count < 3) {
                resultBitmap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.RGB_565);
            }

        } while (count < 3);


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
            imageViewBeforeRotation.setImageBitmap(bitmap);
        }

        Bitmap bitmapAfter = rotationImage(bitmap);
        imageViewAfterRotation.setImageBitmap(bitmapAfter);

    }
}
