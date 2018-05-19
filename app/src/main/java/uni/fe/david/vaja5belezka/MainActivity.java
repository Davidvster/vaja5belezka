package uni.fe.david.vaja5belezka;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
 
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filename = getResources().getString(R.string.datotekaZaBelezko);
    }

    public void shrani(View v){
        // da izvemo kdo poklice funkcijo v.getId();
        EditText vnosno = (EditText) findViewById(R.id.vnosno);
        String vsebina = vnosno.getText().toString();

        // vpišemo v datoteko
        vpisiVDatoteko(vsebina+"\n");

        // pobrisat vsebino
        vnosno.setText("");

    }

    private void vpisiVDatoteko(String vsebina){

        try {
            //ustvarimo izhodni tok
            FileOutputStream os = openFileOutput(filename, Context.MODE_PRIVATE | Context.MODE_APPEND);
            //FileOutputStream os = openFileOutput(filename, Context.MODE_PRIVATE);
            //zapisemo posredovano vsebino v datoteko
            os.write(vsebina.getBytes());
            //sprostimo izhodni tok
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void izpisi(View v){
        //preberemo iz datoteke

        String vsebinaDatoteke = beriIzDatoteke();


        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(getApplicationContext(), vsebinaDatoteke, duration);
        toast.show();

        //EditText vnosno = (EditText) findViewById(R.id.vnosno);
        //vnosno.setText(vsebinaDatoteke);


    }

    private String beriIzDatoteke(){

        // ustvarimo vhodni podatkovni tok
        FileInputStream inputStream;

        //ugotovimo, koliko je velika datoteka
        File file = new File(getFilesDir(), filename);
        int length = (int) file.length();

        //pripravimo spremenljivko, v katero se bodo prebrali podatki
        byte[] bytes = new byte[length];

        //preberemo podatke
        try {
            inputStream = openFileInput(filename);
            inputStream.read(bytes);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //podatke pretvorimo iz polja bajtov v znakovni niz
        String vsebina = new String(bytes);

        return vsebina;
    }
}
