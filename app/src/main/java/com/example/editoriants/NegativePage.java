package com.example.editoriants;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NegativePage extends Activity {
    private static final int RGB_MASK = 0x00FFFFFF;
    private ImageView myImageNegative;
    private Button buttonTakeNegative;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negative_menu_page);

        myImageNegative = (ImageView) findViewById(R.id.myImageNegative);
        buttonTakeNegative = (Button) findViewById(R.id.buttonTakeNegative);


        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(NegativePage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NegativePage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        buttonTakeNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

    }

    public Bitmap invert() {
        // Create mutable Bitmap to invert, argument true makes it mutable
        Bitmap inversion = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        // Get info about Bitmap
        int width = inversion.getWidth();
        int height = inversion.getHeight();
        int pixels = width * height;

        // Get original pixels
        int[] pixel = new int[pixels];
        inversion.getPixels(pixel, 0, width, 0, 0, width, height);

        // Modify pixels
        for (int i = 0; i < pixels; i++)
            pixel[i] = pixel[i] ^ RGB_MASK;
        inversion.setPixels(pixel, 0, width, 0, 0, width, height);

        return inversion;
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

        bitmap = invert();
        myImageNegative.setImageBitmap(bitmap);

    }

}







//        private Bitmap negativeImage() {
//
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//
//        for(int i = 0; i < width; i++) {
//            for(int j = 0; j < height; j++) {
//                int colour = bitmap.getPixel(i, j);
//
//                int r = (Color.red(colour)>>24)&0xff;
//                int g = (Color.green(colour)>>16)&0xff;
//                int b = (Color.blue(colour)>>8)&0xff;
//                int a = Color.alpha(colour)&0xff;
//
//                r = 255 - r;
//                g = 255 - g;
//                b = 255 - b;
//
//                colour = (a<<24) | (r<<16) | (g<<8) | b;
//
//                bitmap.setPixel(i, j, Color.rgb(r,g,b));
//
//
//            }
//        }
//
//
//
//
//    }



