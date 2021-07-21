package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private String songLength;
    private String coverArt;
    private int position;

    private final MediaPlayer player = new MediaPlayer();
    private Button btnPlayPause = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Bundle songData = this.getIntent().getExtras();
        id = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        songLength = songData.getString("songLength");
        coverArt = songData.getString("imgCoverArt");
        position = songData.getInt("position");

        btnPlayPause = findViewById(R.id.btnPlayPause);
        displaySong(title, artiste, coverArt);
        playSong(fileLink);
        Log.d("temasek", String.valueOf(position));
    }


    public void displaySong(String title, String artiste, String imgCoverArt){

        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtiste = findViewById(R.id.txtArtist);
        txtArtiste.setText(artiste);

        ImageView iCoverArt = findViewById(R.id.imgCoverArt);
        Picasso.with(this).load(imgCoverArt).into(iCoverArt);


    }

    public void playSong(String url){
        try{
            player.reset();
            player.setDataSource(url);
            player.prepare();
            gracefullyStopsWhenMusicEnds();

            btnPlayPause.setText("PLAY");
            setTitle(title);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view){
        if(player.isPlaying()){
            player.pause();
            btnPlayPause.setText("PLAY");
        }
        else{
            player.start();
            btnPlayPause.setText("PAUSE");
        }
    }

    private void gracefullyStopsWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getBaseContext(), "The song had ended and the onCompleteListener is activated\n" +
                        "The button text is changed to 'PLAY", Toast.LENGTH_LONG).show();
                btnPlayPause.setText("PLAY");
            }
        });
    }

    public void playNext(View view){

        playSong(fileLink);
    }

    public void playPrev(View view){

        playSong(fileLink);
    }

    public void onBackPressed(){
        super.onBackPressed();
        player.release();
    }
}
