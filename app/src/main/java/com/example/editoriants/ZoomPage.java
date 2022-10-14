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

public class ZoomPage extends Activity {

    private Button btnTakePictureZoom;
    private ImageView imageViewBeforeZoom;
    private ImageView imageViewAfterZoom;
    // test test
    // test
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_page);

        imageViewBeforeZoom = (ImageView) findViewById(R.id.imageViewBeforeZoom);
        imageViewAfterZoom = (ImageView) findViewById(R.id.imageViewAfterZoom);

        //Request for camera runtime permission
        if (ContextCompat.checkSelfPermission(ZoomPage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ZoomPage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

        btnTakePictureZoom = (Button)findViewById(R.id.buttonTakePictureZoom);

        btnTakePictureZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

    }

    private Bitmap zoomImage(Bitmap bm) {
        Bitmap temp = bm.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap cropBitmap = Bitmap.createBitmap((temp.getWidth()/3), (temp.getHeight()/3), Bitmap.Config.ARGB_8888);


        int width = temp.getWidth();
        int height = temp.getHeight();

        int A, R, G, B;
        int colorPixel = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                colorPixel = temp.getPixel(i, j);
                R = Color.red(colorPixel);
                G = Color.green(colorPixel);
                B = Color.blue(colorPixel);
                A = Color.alpha(colorPixel);

                if (i >= height/3 && i < (height * 2/3) && j >= width/3 && j < (width * 2/3)) {
                    cropBitmap.setPixel(i/3, j/3, Color.argb(A, R, G, B));
                }

            }
        }

        Bitmap scaleBitmap = Bitmap.createBitmap(temp.getWidth(), temp.getHeight(), Bitmap.Config.ARGB_8888);


        int m = 0, n = 0;
        int colorPixel1;
        for (int i = 0; i < cropBitmap.getWidth(); i++) {
            for (int j = 0; j < cropBitmap.getHeight(); j++) {

                colorPixel1= cropBitmap.getPixel(i, j);

                R = Color.red(colorPixel1);
                G = Color.green(colorPixel1);
                B = Color.blue(colorPixel1);
                A = Color.alpha(colorPixel1);

                scaleBitmap.setPixel(m, n, Color.argb(A, R, G, B));
                scaleBitmap.setPixel(m, (n+1), Color.argb(A, R, G, B));
                scaleBitmap.setPixel(m, (n+2), Color.argb(A, R, G, B));

                scaleBitmap.setPixel((m+1), n, Color.argb(A, R, G, B));
                scaleBitmap.setPixel((m+1), (n+1), Color.argb(A, R, G, B));
                scaleBitmap.setPixel((m+1), (n+2), Color.argb(A, R, G, B));

                scaleBitmap.setPixel((m+2), n, Color.argb(A, R, G, B));
                scaleBitmap.setPixel((m+2), (n+1), Color.argb(A, R, G, B));
                scaleBitmap.setPixel((m+2), (n+2), Color.argb(A, R, G, B));

               n+=3;
            }
            m+=3;
            n = 0;
        }

        return scaleBitmap;
    }

    public static Bitmap resizeBitmap(Bitmap source, int maxLength) {
        try {
            if (source.getHeight() >= source.getWidth()) {
                int targetHeight = maxLength;
                if (source.getHeight() <= targetHeight) { // if image already smaller than the required height
                    return source;
                }

                double aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                int targetWidth = (int) (targetHeight * aspectRatio);

                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                return result;
            } else {
                int targetWidth = maxLength;

                if (source.getWidth() <= targetWidth) { // if image already smaller than the required height
                    return source;
                }

                double aspectRatio = ((double) source.getHeight()) / ((double) source.getWidth());
                int targetHeight = (int) (targetWidth * aspectRatio);

                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                return result;

            }
        }
        catch (Exception e)
        {
            return source;
        }
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
            imageViewBeforeZoom.setImageBitmap(bitmap);
        }

        Bitmap bitmapAfter = zoomImage(bitmap);
        imageViewAfterZoom.setImageBitmap(bitmapAfter);

    }
}
