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

public class BrighteningPage extends Activity {

    private ImageView myImageBrightening;
    private Button buttonTakeBrightening;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brightening_menu_page);

        myImageBrightening = (ImageView) findViewById(R.id.myImageBrightening);
        buttonTakeBrightening = (Button) findViewById(R.id.buttonTakeBrightening);

        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(BrighteningPage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BrighteningPage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        buttonTakeBrightening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    private Bitmap doBrightness(int value) {

        Bitmap brighteningImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int A, R, G, B;
        int pixel;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixel = bitmap.getPixel(i, j);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                R += value;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }
                G += value;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }
                B += value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }
                brighteningImage.setPixel(i, j, Color.argb(A, R, G, B));
            }
        }
        return brighteningImage;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }

        bitmap = doBrightness(80);
        myImageBrightening.setImageBitmap(bitmap);

    }

}
