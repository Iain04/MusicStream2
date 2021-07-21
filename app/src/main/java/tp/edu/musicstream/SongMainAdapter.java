package tp.edu.musicstream;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SongMainAdapter extends RecyclerView.Adapter<MyView>{

    public List<Song> songs;
    Context context;

    public SongMainAdapter(List<Song> songs) {
        this.songs = songs;
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View songView = inflater.inflate(R.layout.item_song_main, parent, false);
        MyView viewHolder = new MyView(songView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, final int position) {
        final Song song = songs.get(position);


        // Items setup on views
        TextView artiste = holder.artisteTxt;
        artiste.setText(song.getArtiste());
        TextView title = holder.titleTxt;
        title.setText(song.getTitle());
        Picasso.with(context).load(song.getImgCoverArt()).into(holder.imageButton);

        int index = holder.getAdapterPosition();

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlaySongActivity.class);
                intent.putExtra("id", song.getId());
                intent.putExtra("title", song.getTitle());
                intent.putExtra("artiste", song.getArtiste());
                intent.putExtra("fileLink", song.getFileLink());
                intent.putExtra("songLength", song.getSongLength());
                intent.putExtra("imgCoverArt", song.getImgCoverArt());
                intent.putExtra("position", index);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}