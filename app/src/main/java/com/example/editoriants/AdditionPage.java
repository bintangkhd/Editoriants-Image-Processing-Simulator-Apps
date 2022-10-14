package com.example.editoriants;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdditionPage extends Activity {

    private final int GALLERY_REQ_CODE = 1000;
    private Button buttonSelectImageArithmetic;
    private ImageView imageViewArithmetic1;
    private ImageView imageViewArithmetic2;
    private ImageView imageViewArithmeticResult;

    private Bitmap resultImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addition_page);

        //Select Image
        imageViewArithmetic1 = (ImageView) findViewById(R.id.imageViewArithmetic1);

        imageViewArithmetic2 = (ImageView) findViewById(R.id.imageViewArithmetic2);

        imageViewArithmeticResult = (ImageView) findViewById(R.id.imageViewArithmeticResult);

        buttonSelectImageArithmetic = (Button) findViewById(R.id.buttonSelectImageArithmetic);

        buttonSelectImageArithmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(AdditionPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AdditionPage.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    }, GALLERY_REQ_CODE);
                    return;
                }

                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                iGallery.setType("image/*");
                startActivityForResult(iGallery, GALLERY_REQ_CODE);



            }
        });

    }

    public Bitmap additionImage(Bitmap image1, Bitmap image2) {

        Bitmap bmImage1 = image1.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap bmImage2 = image2.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap bmResult = image2.copy(Bitmap.Config.ARGB_8888, true);


        int width = bmImage1.getWidth();
        int height = bmImage2.getHeight();

        int A1, R1, G1, B1;
        int A2, R2, G2, B2;
        int A3 = 0, R3 = 0, G3 = 0, B3 = 0;
        int pixel1;
        int pixel2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                pixel1 = bmImage1.getPixel(i, j);
                pixel2 = bmImage2.getPixel(i, j);

                A1 = Color.alpha(pixel1);
                R1 = Color.red(pixel1);
                G1 = Color.green(pixel1);
                B1 = Color.blue(pixel1);

                A2 = Color.alpha(pixel2);
                R2 = Color.red(pixel2);
                G2 = Color.green(pixel2);
                B2 = Color.blue(pixel2);

                A3 = A1 + A2;
                R3 = R1 + R2;
                G3 = G1 + G2;
                B3 = B1 + B2;

                if (A3 > 255) {
                    A3 = 255;
                } else if (A3 < 0) {
                    A3 = 0;
                }
                if (R3 > 255) {
                    R3 = 255;
                } else if (R3 < 0) {
                    R3 = 0;
                }
                if (G3 > 255) {
                    G3 = 255;
                } else if (G3 < 0) {
                    G3 = 0;
                }
                if (B3 > 255) {
                    B3 = 255;
                } else if (B3 < 0) {
                    B3 = 0;
                }

                bmResult.setPixel(i, j, Color.argb(A3, R3, G3, B3));
            }
        }
        return bmResult;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ArrayList<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();
            for (int i = 0; i < clipData.getItemCount(); i++) {
                Uri imageUri = clipData.getItemAt(i).getUri();

                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    bitmaps.add(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

            resultImage = additionImage(bitmaps.get(0), bitmaps.get(1));

            imageViewArithmetic1.setImageBitmap(bitmaps.get(0));

            imageViewArithmetic2.setImageBitmap(bitmaps.get(1));

            imageViewArithmeticResult.setImageBitmap(resultImage);


        }


    }

}
