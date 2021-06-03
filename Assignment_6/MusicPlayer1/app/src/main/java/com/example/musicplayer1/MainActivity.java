package com.example.musicplayer1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button start,play,pause,stop,next,previous;
    private RecyclerView recyclerView;
    List<String> names;
    MediaPlayer music_player = null;
    int posn = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btn_start);
        play = (Button) findViewById(R.id.btn_play);
        pause = (Button) findViewById(R.id.btn_pause);
        stop = (Button) findViewById(R.id.btn_stop);
        next = (Button) findViewById(R.id.btn_next);
        previous = (Button) findViewById(R.id.btn_previous);

        recyclerView = (RecyclerView) findViewById(R.id.music_list);

        names = new ArrayList<>();
        names.add("Crab Rave");
        names.add("Megalovania");
        names.add("Sandstorm");
        names.add("Supernova");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter((ArrayList) names,MainActivity.this);
        recyclerView.setAdapter(customAdapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posn == -1)
                {
                    posn = customAdapter.getPosn();
                }
                Toast.makeText(v.getContext(), "Track selected: " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
                createPlayer();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Playing " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
                controlPlayer(0);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pausing " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
                controlPlayer(1);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Stopping " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
                controlPlayer(2);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posn = (posn+1)%4;
                Toast.makeText(v.getContext(), "Track selected: " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posn == 0)
                {
                    posn = 3;
                }
                else
                {
                    posn = (posn-1)%4;
                }
                Toast.makeText(v.getContext(), "Track selected: " + (CharSequence) names.get(posn), Toast.LENGTH_LONG).show();
            }
        });

    }

    void createPlayer() {
        if(posn == 0)
        {
            music_player = MediaPlayer.create(this, R.raw.crab_rave);
        }
        if(posn == 1)
        {
            music_player = MediaPlayer.create(this, R.raw.megalovania);
        }
        if(posn == 2)
        {
            music_player = MediaPlayer.create(this, R.raw.sandstorm);
        }
        if(posn == 3) {
            music_player = MediaPlayer.create(this, R.raw.supernova);
        }
    }

    void controlPlayer(int input)
    {
        if(input == 0)
        {
            if (music_player!= null && !music_player.isPlaying()) {
                music_player.start();
            }
        }
        else if(input == 1)
        {
            music_player.pause();
        }
        else if(input == 2)
        {
            music_player.stop();
        }
        else if(input == 3)
        {
            music_player.stop();
            posn++;
        }
        else if(input == 4)
        {
            music_player.stop();
            posn--;
        }
    }

}