package com.example.musicplayer1;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter {

    ArrayList names;
    Context context;
    String song;
    int posn;

    public CustomAdapter(ArrayList names, Context context)
    {
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;
        myViewHolder.name.setText((CharSequence) names.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), (CharSequence) names.get(position),Toast.LENGTH_LONG).show();
                song = "R.raw." + (CharSequence) names.get(position);
                posn = position;
                System.out.println(song);
                System.out.println(position);
            }
        });
    }

    public String getSong()
    {
        return song;
    }

    public int getPosn()
    {
        return posn;
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
        }
    }
}
