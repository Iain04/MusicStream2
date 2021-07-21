package tp.edu.musicstream;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyView extends RecyclerView.ViewHolder {

    public TextView titleTxt;
    public TextView artisteTxt;
    public ImageButton imageButton;

    public MyView(@NonNull View itemView) {
        super(itemView);

        titleTxt = itemView.findViewById(R.id.titleTxt);
        artisteTxt = itemView.findViewById(R.id.artisteTxt);
        imageButton = itemView.findViewById(R.id.imageButton);
    }
}
