package com.example.gamebacklog.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gamebacklog.R;
import com.example.gamebacklog.service.model.GameBacklog;

import java.util.List;


public class GameBacklogAdapter extends RecyclerView.Adapter<GameBacklogAdapter.ViewHolder> {

    private List<GameBacklog> mGameBacklogs;
    final private OnItemClickListener mItemClickListener;

    public GameBacklogAdapter(List<GameBacklog> mGameBacklogs, OnItemClickListener mItemClickListener) {
        this.mGameBacklogs = mGameBacklogs;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cardview, parent, false);
        // Return a new holder instance
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GameBacklog gameBacklog = mGameBacklogs.get(i);
        viewHolder.titleView.setText(gameBacklog.getTitle());
        viewHolder.platformView.setText(gameBacklog.getPlatform());
        viewHolder.statusView.setText(gameBacklog.getStatus());
        viewHolder.dateView.setText(gameBacklog.getDate());
    }

    public GameBacklog getGameBacklogAt(int position) {
        return mGameBacklogs.get(position);
    }

    @Override
    public int getItemCount() {
        return mGameBacklogs.size();
    }

    public void swapList(List<GameBacklog> newList) {
        mGameBacklogs = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView;
        TextView platformView;
        TextView statusView;
        TextView dateView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.text_view_title);
            platformView = itemView.findViewById(R.id.text_view_platform);
            statusView = itemView.findViewById(R.id.text_view_status);
            dateView = itemView.findViewById(R.id.text_view_datum);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClick(position);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
