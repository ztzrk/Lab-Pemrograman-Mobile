package com.example.activity_assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.shape);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shape, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
            int pos, long id) {
                View view1 = (View) findViewById(R.id.bola);
                View view2 = (View) findViewById(R.id.balok);
                View view3 = (View) findViewById(R.id.kerucut);
                String shape = adapter.getItem(pos).toString();

                if (shape.equals("Bola")) {
                    TextView v = (TextView) findViewById(R.id.volume);
                    v.setText("Hasil");
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                    Button hitung = (Button) findViewById(R.id.hitungBola);
                    EditText input1 = (EditText) findViewById(R.id.jari_jariBola);

                    hitung.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(input1.getText().toString())) {
                                input1.setError("Input Tidak Boleh Kosong");
                                return;
                            }
                            else {
                                int jari = Integer.parseInt(input1.getText().toString());
                                double pie=3.14285714286;
                                DecimalFormat decim = new DecimalFormat("0.00");
                                double volume= Double.parseDouble(decim.format((4.0/3.0)*pie*(jari*jari*jari)));
                                v.setText(String.valueOf(volume));
                            }
                        }
                    });
                }
                else if (shape.equals("Balok")) {
                    TextView v = (TextView) findViewById(R.id.volume);
                    v.setText("Hasil");
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.GONE);
                    Button hitung = (Button) findViewById(R.id.hitungBalok);
                    EditText input1 = (EditText) findViewById(R.id.panjang);
                    EditText input2 = (EditText) findViewById(R.id.lebar);
                    EditText input3 = (EditText) findViewById(R.id.tinggi);

                    hitung.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(TextUtils.isEmpty(input1.getText().toString())) {
                                input1.setError("Input Tidak Boleh Kosong");
                                if(TextUtils.isEmpty(input2.getText().toString())) {
                                    input2.setError("Input Tidak Boleh Kosong");
                                    if(TextUtils.isEmpty(input3.getText().toString())) {
                                        input3.setError("Input Tidak Boleh Kosong");
                                        return;
                                    } return;
                                } return;
                            } else if (TextUtils.isEmpty(input2.getText().toString())) {
                                input2.setError("Input Tidak Boleh Kosong");
                                if (TextUtils.isEmpty(input3.getText().toString())) {
                                    input3.setError("Input Tidak Boleh Kosong");
                                    return;
                                } return;
                            } else if (TextUtils.isEmpty(input3.getText().toString())) {
                                input3.setError("Input Tidak Boleh Kosong");
                                return;
                            }
                            else {
                                int panjang = Integer.parseInt(input1.getText().toString());
                                int lebar = Integer.parseInt(input2.getText().toString());
                                int tinggi = Integer.parseInt(input3.getText().toString());
                                v.setText(String.valueOf(panjang*lebar*tinggi));
                            }
                        }
                    });
                }
                else if (shape.equals("Kerucut")) {
                    TextView v = (TextView) findViewById(R.id.volume);
                    v.setText("Hasil");
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.VISIBLE);
                    Button hitung = (Button) findViewById(R.id.hitungKerucut);
                    EditText input1 = (EditText) findViewById(R.id.jari_jariKerucut);
                    EditText input2 = (EditText) findViewById(R.id.tinggiKerucut);

                    hitung.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(TextUtils.isEmpty(input1.getText().toString())) {
                                input1.setError("Input Tidak Boleh Kosong");
                                if (TextUtils.isEmpty(input2.getText().toString())) {
                                    input2.setError("Input Tidak Boleh Kosong");
                                    return;
                                } return;
                            } else if (TextUtils.isEmpty(input2.getText().toString())) {
                                input2.setError("Input Tidak Boleh Kosong");
                                return;
                            }
                            else {
                                int jari = Integer.parseInt(input1.getText().toString());
                                int tinggi = Integer.parseInt(input2.getText().toString());
                                double pie=3.14285714286;
                                DecimalFormat decim = new DecimalFormat("0.00");
                                double volume= Double.parseDouble(decim.format((1.0/3.0)*pie*(tinggi*jari*jari)));
                                v.setText(String.valueOf(volume));
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Another interface callback
            }
        });
    }
}