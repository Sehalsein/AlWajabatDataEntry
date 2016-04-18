package com.alwajabat.alwajabatdataentry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deezdroid on 18/04/16.
 */
public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.ItemViewHolder> {

    private Context context;
    private List<String> titles =  new ArrayList<>();

    public AllListAdapter(Context context, List<String> titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ItemViewHolder viewHolder =  new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.title.setText(titles.get(position));


    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
