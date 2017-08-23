package com.hift.biodict;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText filterText;
    private ArrayAdapter<String> listAdapter;
    private WordsList wordsList;
    private Spinner languageSpinner;
    private ProgressBar progressBar;

    /*TODO: load data dari db ke list untuk array adapter
      TODO: lengkapin DictActivity
      TODO: bikin sampel beberapa data
      TODO: search directly from dict activity?
      TODO optional: bikin dumerau-lavensthein function buat nyari similar string kalo gak ada result*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filterText = (EditText)findViewById(R.id.editText);
        final ListView itemList = (ListView)findViewById(R.id.listView);

//        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
//        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this, R.array.language_array, android.R.layout.simple_spinner_item);
//        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        languageSpinner.setAdapter(languageAdapter);
//        languageSpinner.setSelection(0);

        DBBackend dbBackend = new DBBackend(MainActivity.this, "BAHASA");
        this.wordsList = dbBackend.makeWordsList();
        String[] terms = wordsList.getTermsArray();

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);

        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // make Toast when click
//                Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, DictionaryActivity.class);
                Map<String, Integer> map = wordsList.getEntryMap();
                String key = itemList.getItemAtPosition(position).toString().toUpperCase();
                int index = map.get(key);
                String language = "BAHASA";
                intent.putExtra("DICTIONARY_INDEX", index);
                intent.putExtra("LANGUAGE", language);
                startActivity(intent);
            }
        });

        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.listAdapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
