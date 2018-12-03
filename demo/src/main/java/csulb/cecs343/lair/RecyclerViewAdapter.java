package csulb.cecs343.lair;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>
{
    List<Element> list = Collections.emptyList();
    private SparseBooleanArray states = new SparseBooleanArray();
    Context context;

    public RecyclerViewAdapter(List<Element> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.name.setText(list.get(position).title);
        holder.image.setImageResource(list.get(position).imageID);

        final Element e = list.get(position);
        holder.box.setOnCheckedChangeListener(null);
        holder.box.setSelected(e.isSelected());
        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    e.setSelected(true);
                }
                else
                {
                    e.setSelected(false);
                }
            }
        }
        );

        holder.box.setChecked(e.isSelected());
        holder.container.setBackgroundColor(Color.parseColor("#403F3F"));
    }

    @Override
    public int getItemCount()
    {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Element element)
    {
        list.add(position, element);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Element element)
    {
        int position = list.indexOf(element);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
