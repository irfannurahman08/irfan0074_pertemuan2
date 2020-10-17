package id.web.iotproject.irfan0074_pertemuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BacaFileActivity extends AppCompatActivity {
    EditText etNamaFile;
    EditText etIsiFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_file);

        etNamaFile = (EditText) findViewById(R.id.etNamaFile);
        etIsiFile = (EditText) findViewById(R.id.etIsiFile);
    }

    public void bacaData(View view) {
        if (isExternalStorageReadable()) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                File textfile = new File(Environment.getExternalStorageDirectory(),etNamaFile.getText().toString());
                FileInputStream fis = new FileInputStream(textfile);

                if (fis != null) {
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    String line = null;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    fis.close();
                }
                etIsiFile.setText(stringBuilder);

            } catch (IOException e) {
                Toast.makeText(this, e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Tidak dapat membaca File",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isExternalStorageReadable() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }

    }
}