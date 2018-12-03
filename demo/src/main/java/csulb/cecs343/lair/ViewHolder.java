package csulb.cecs343.lair;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder
{
    public TextView name;
    public ImageView image;
    public CheckBox box;
    public LinearLayout container;

    ViewHolder(View itemView)
    {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        image = (ImageView) itemView.findViewById(R.id.image);
        box = (CheckBox) itemView.findViewById(R.id.checkbox);
        container =  (LinearLayout) itemView.findViewById(R.id.file_menu_row);
    }
}
