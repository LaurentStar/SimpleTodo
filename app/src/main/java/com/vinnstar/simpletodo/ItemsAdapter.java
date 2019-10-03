package com.vinnstar.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for taking data from model and displaying in recycler view rows
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {

        void onItemLongClicked(int position);


    }


    List<String> items;
    OnLongClickListener onLongClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener onLongClickListener) {
        this.items = items;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to flat view
        View toolView= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        //wrap inside a view holder and return it.
        return new ViewHolder(toolView);
    }

    //Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the posititon
        String item = items.get(position);
        //Bind the item into the specificed view holder
        holder.bind(item);
    }

    //Tells the recycler view hold many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {

                //Notify the listener which position was long clicked
                @Override
                public boolean onLongClick(View view) {
                    onLongClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
