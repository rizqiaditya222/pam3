package com.example.recycler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    final LayoutInflater inflater;
    final Context context;
    final ArrayList<Mahasiswa> data;

    public MahasiswaAdapter(Context context, ArrayList<Mahasiswa> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa mhs = data.get(position);
        Log.d(MainActivity.TAG, "data " + position);
        Log.d(MainActivity.TAG, "nim " + mhs.nim);
        Log.d(MainActivity.TAG, "nama " + mhs.nama);

        holder.tvNim.setText(mhs.nim);
        holder.tvNama.setText(mhs.nama);

        // Klik item RecyclerView untuk berpindah ke Activity2
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Activity2.class);
            intent.putExtra("nim", mhs.nim);
            intent.putExtra("nama", mhs.nama);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        Log.d(MainActivity.TAG, "Jumlah data " + data.size());
        return data.size();
    }

    class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNim;
        TextView tvNama;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNim = itemView.findViewById(R.id.tvNim);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}
