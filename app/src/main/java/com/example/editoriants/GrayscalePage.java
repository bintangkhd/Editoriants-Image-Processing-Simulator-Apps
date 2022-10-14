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


public class GrayscalePage extends Activity {

    private ImageView myImageGrayscale;
    private Button buttonTakeGrayscale;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grayscale_menu_page);

        myImageGrayscale = (ImageView) findViewById(R.id.myImageGrayscale);
        buttonTakeGrayscale = (Button) findViewById(R.id.buttonTakeGrayscale);

        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(GrayscalePage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GrayscalePage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        buttonTakeGrayscale.setOnClickListener(new View.OnClickListener() {
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

    public Bitmap grayscaleMode() {

        Bitmap grayscaleImage = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        int A, R, G, B;
        int colorPixel;
        int width = grayscaleImage.getWidth();
        int height = grayscaleImage.getHeight();

        for(int  i = 0; i < width; i++) {
            for(int  j = 0; j < height; j++) {
                colorPixel = bitmap.getPixel(i, j);
                R = Color.red(colorPixel);
                G = Color.green(colorPixel);
                B = Color.blue(colorPixel);
                A = Color.alpha(colorPixel);
                System.out.println("Red : " + R);
                System.out.println("Green : " + G);
                System.out.println("Blue : " + B);

                R = ((R * 299/1000) + (G * 587/1000) + (B * 144/1000));
                G = R;
                B = R;

                grayscaleImage.setPixel(i, j, Color.argb(A, R, G, B));
            }
        }

        return grayscaleImage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");

        }

        bitmap = grayscaleMode();
        myImageGrayscale.setImageBitmap(bitmap);
    }
}
