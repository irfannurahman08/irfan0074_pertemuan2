package id.web.iotproject.irfan0074_pertemuan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private int MY_REQUEST_CODE = 1;
    EditText etNamaFile;
    EditText etIsiFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaFile = (EditText) findViewById(R.id.etNamaFile);
        etIsiFile = (EditText) findViewById(R.id.etIsiFile);
    }

    public void buatFile(View view) {
        if (isExternalStorageWritable() &&
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File textfile = new File(Environment.getExternalStorageDirectory(),etNamaFile.getText().toString());
            try {
                FileOutputStream fos = new FileOutputStream(textfile);
                fos.write(etIsiFile.getText().toString().getBytes());
                fos.close();
                Toast.makeText(this, "File Berhasil disimpan",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File tidak dapat disimpan",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission},MY_REQUEST_CODE);

            } else {
                Toast.makeText(this, "Permission Diberikan", Toast.LENGTH_SHORT).show();
            }
        }
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public boolean isExternalStorageWritable() {
        if
        (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public void bacaData(View view) {
        Intent intent=new Intent(MainActivity.this,BacaFileActivity.class);
        startActivity(intent);
    }
}