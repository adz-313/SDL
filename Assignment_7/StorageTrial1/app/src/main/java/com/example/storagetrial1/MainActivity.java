package com.example.storagetrial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";

    Button esave,eload;
    EditText editText;
    TextView tvload;
    String file_name = "";
    String file_path = "";
    String file_content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        esave = findViewById(R.id.button_extsave);
        eload = findViewById(R.id.button_extload);
        editText = findViewById(R.id.edit_text);
        tvload = findViewById(R.id.tvLoad);
        file_name = "myFile.txt";
        file_path = "MyFileDir";
        if(!isExternalStorageAvailableForRW())
        {
            esave.setEnabled(false);
        }

        esave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvload.setText("");
                file_content = editText.getText().toString().trim();
                if(!file_content.equals("")) {
                    File myExternalFile = new File(getExternalFilesDir(file_path), file_name);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myExternalFile);
                        fos.write(file_content.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editText.setText("");
                    Toast.makeText(MainActivity.this, "Saved to SD card", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
            }
        });
        eload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileReader fr = null;
                File myExternalFile = new File(getExternalFilesDir(file_path), file_name);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    fr = new FileReader(myExternalFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while(line != null) {
                        stringBuilder.append(line).append('\n');
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    String fileContents = "External Storage:\n" + stringBuilder.toString();
                    tvload.setText(fileContents);
                }
            }
        });
    }

    private boolean isExternalStorageAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        return false;
    }


    public void save(View v) {
        String text = editText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            editText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            tvload.setText("Internal Storage:\n" + sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}