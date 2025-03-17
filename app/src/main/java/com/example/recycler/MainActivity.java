package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv1;

    EditText etNim, etNama;

    public static String TAG = "RV1";

    Button btnTambah;
    MahasiswaAdapter adapter;
    ArrayList<Mahasiswa> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.editTextTextPersonName2);

        rv1 = findViewById(R.id.rv1);
        btnTambah = findViewById(R.id.bt1);

        data = getData();
        adapter = new MahasiswaAdapter(this, data);

        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        btnTambah.setOnClickListener(v -> {
            tambahMahasiswaBaru();
        });
    }

    public ArrayList<Mahasiswa> getData() {
        ArrayList<Mahasiswa> data = new ArrayList<>();
        List<String> nim = Arrays.asList(getResources().getStringArray(R.array.nim));
        List<String> nama = Arrays.asList(getResources().getStringArray(R.array.nama));
        for (int i = 0; i < nim.size(); i++) {
            Mahasiswa mhs = new Mahasiswa();
            mhs.nim = nim.get(i);
            mhs.nama = nama.get(i);
            Log.d(TAG,"getData "+mhs.nim);
            data.add(mhs);
        }
        return data;
    }

    private void tambahMahasiswaBaru() {
        String nimInput = etNim.getText().toString().trim();
        String namaInput = etNama.getText().toString().trim();

        if (nimInput.isEmpty() || namaInput.isEmpty()) {
            Toast.makeText(this, "NIM dan Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        Mahasiswa mahasiswaBaru = new Mahasiswa();
        mahasiswaBaru.nim = nimInput;
        mahasiswaBaru.nama = namaInput;

        data.add(mahasiswaBaru);
        adapter.notifyItemInserted(data.size() - 1);

        etNim.setText("");
        etNama.setText("");
    }

}