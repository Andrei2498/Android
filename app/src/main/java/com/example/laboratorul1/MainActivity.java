package com.example.laboratorul1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    TextView text;
    String[] ListElements = new String[] {
            "Produs1",
            "Produs2",
            "Produs3",
            "Produs4",
            "Produs5"
    };
    String[] ListOfDescriptions = new String[]{
            "Descriere Produs1",
            "Descriere Produs2",
            "Descriere Produs3",
            "Descriere Produs4",
            "Descriere Produs5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);
        text = findViewById(R.id.textView2);
        if(savedInstanceState != null){
            text.setText(savedInstanceState.getCharSequence("Text"));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = (String) parent.getItemAtPosition(position);
                text.setText(ListOfDescriptions[position]);
            }
        });
        Log.d("lifecycle","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","onPause invoked");
    }
    @Override
        protected void onStop() {
            super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","onDestroy invoked");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("Text", text.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                return true;
            case R.id.Cart:
                openCartMenu();
                return true;
            case R.id.Login:
                openLoginMenu();
                return true;
            case R.id.settings:
                openSettingsMenu();
                return true;
            case R.id.sensors:
                openSensorsMenu();
                return true;
            case R.id.camera:
                openCameraMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCartMenu() {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void openLoginMenu() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openSettingsMenu(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openSensorsMenu(){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void openCameraMenu(){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void onSaveButtonCliked(View view) {
        String filename = "DataFile.txt";
        text = findViewById(R.id.textView2);
        Context context = getApplicationContext();
        File file = new File(context.getFilesDir(), filename);
        String fileContents = (String) text.getText();
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            System.out.println(new String(data));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
