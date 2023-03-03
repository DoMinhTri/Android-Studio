package com.dmt.qrcode3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btScan;
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.textView);
        btScan = findViewById(R.id.button);

        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanQRCode();
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////
    private void ScanQRCode()
    {
        IntentIntegrator intel = new IntentIntegrator(this);
        intel.setPrompt("QRCode");
        intel.setOrientationLocked(true);
        intel.setCameraId(0);  // Use a specific camera of the device
        intel.setBeepEnabled(true);
        intel.setBarcodeImageEnabled(true);
        intel.initiateScan();
    }
    ////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result  = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            if(result.getContents()!="")
            {
                tvText.setText(result.getContents());
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////
}