package csulb.cecs343.lair;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder
{
    TextView name;
    ImageView image;
    CheckBox checkbox;

    ViewHolder(View itemView)
    {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        image = (ImageView) itemView.findViewById(R.id.image);
        checkbox = (CheckBox)  itemView.findViewById(R.id.checkbox);
    }
}
