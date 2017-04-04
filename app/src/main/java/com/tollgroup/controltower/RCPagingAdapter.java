package com.tollgroup.controltower;

/**
 * Created by pong on 2017/4/4.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
public class RCPagingAdapter extends RecyclerView.Adapter<RCPagingAdapter.ViewHolder> {
    private ArrayList<ServerStatusLog> ssls;
    protected Context context;

    public RCPagingAdapter(Context context, ArrayList<ServerStatusLog> ssls) {
        this.ssls = ssls;
        this.context = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.logdetail);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.logdetail, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(RCPagingAdapter.ViewHolder holder, int position) {
        ServerStatusLog ssl = ssls.get(position);
        holder.mTextView.setText(ssl.getLogDetail());
    }
    @Override
    public int getItemCount() {
        return this.ssls.size();
    }
}
