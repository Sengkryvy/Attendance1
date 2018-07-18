package com.example.attendance;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class QRFragment extends Fragment {

    Main main;
    int screenHeight;
    int screenWidth;

    public QRFragment() {
        // Required empty public constructor
    }

    SurfaceView surfaceView;
    TextView textView ;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    int RequestCameraPermissionID = 1001;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr, container, false);

        main = new Main();
        screenHeight = Main.screenHeight;
        screenWidth = Main.screenWidth;
        Log.i("TAG", "W = "+screenWidth);
        Log.i("TAG", "H = " + screenHeight);

        surfaceView = (SurfaceView)view.findViewById(R.id.camerapreview);
        textView = (TextView)view.findViewById(R.id.textView);

        barcodeDetector = new BarcodeDetector
                .Builder(this.getActivity())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(Objects.requireNonNull(this.getActivity()), barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize( screenHeight, screenWidth)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback()  {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // request permission
                    ActivityCompat.requestPermissions(Objects.requireNonNull(QRFragment.this.getActivity()),
                            new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(surfaceView.getHolder());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                //make sure  is not zero in size
                if(qrCodes.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            assert vibrator != null;
                            vibrator.vibrate(1000);
                            textView.setText(qrCodes.valueAt(0).displayValue);

                        }
                    });
                }
            }
        });
        return view;
    }

}
