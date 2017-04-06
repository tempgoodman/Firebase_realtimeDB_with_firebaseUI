package com.tollgroup.controltower;

/**
 * Created by pong on 2017/4/4.
 */

import android.content.Context;
import android.provider.LiveFolders;
import android.provider.VoicemailContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        public TextView tDisplayTime;
        public ImageView imAlertType;
        public TextView tServerName;
        public TextView tAlertDetail;
        public TextView tAlerStatus;

        public ViewHolder(View v) {
            super(v);
            tDisplayTime = (TextView)v.findViewById(R.id.display_time);
            tAlerStatus  = (TextView)v.findViewById(R.id.alert_status);
            tAlertDetail  = (TextView)v.findViewById(R.id.alert_detail);
            tServerName = (TextView)v.findViewById(R.id.server_name);
            imAlertType = (ImageView)v.findViewById(R.id.alert_type);
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
        switch (ssl.getAlertType()){
            case "CPU" :holder.imAlertType.setImageResource(R.drawable.ic_computer_black_24dp);
                break;
            case "Memory":holder.imAlertType.setImageResource(R.drawable.ic_memory_black_24dp);
                break;
            case "Service":holder.imAlertType.setImageResource(R.drawable.ic_do_not_disturb_black_24dp);
                break;
            case "Folder":holder.imAlertType.setImageResource(R.drawable.ic_folder_black_24dp);
                break;
            case "Hard Disk Status":holder.imAlertType.setImageResource(R.drawable.ic_filter_none_black_24dp);
                break;
        }
        holder.tServerName.setText("Server Name: "+ssl.getServerName());
        holder.tAlertDetail.setText("Detail: "+ssl.getAlertDetail());
        holder.tAlerStatus.setText("Status: "+ssl.getAlertStatus());
        holder.tDisplayTime.setText(ssl.getDisplayTime());
    }
    @Override
    public int getItemCount() {
        return this.ssls.size();
    }
}
