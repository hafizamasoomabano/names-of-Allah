package com.example.roubaisha.counter.Names;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.roubaisha.counter.R;

import java.util.List;

public class MRecyclerAdapter extends RecyclerView.Adapter<MRecyclerAdapter.MyViewHolder> {
    List<MuhammadNamesModel> seekerCycleModels;
    Activity activity;
    MuhammadNamesModel seekerCycleModel;
    int pre = 0;
    MediaPlayer mediaPlayer;
    public MRecyclerAdapter(Activity activity, List<MuhammadNamesModel> seekerCycleModels) {
        this.activity = activity;
        this.seekerCycleModels = seekerCycleModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=   LayoutInflater.from(parent.getContext()).inflate(R.layout.mcard_row_update,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        seekerCycleModel = seekerCycleModels.get(position);
        holder.banname.setText(seekerCycleModel.getBanName());
        holder.enname.setText(seekerCycleModel.getEnName());
        holder.banglamean.setText(seekerCycleModel.getBannglamean());
        holder.englishmean.setText(seekerCycleModel.getEnglishmean());

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer= MediaPlayer.create(activity,seekerCycleModels.get(position).getMp3File());
                mediaPlayer.start();

                if (mediaPlayer.isPlaying()){
                    holder.pause.setVisibility(View.VISIBLE);
                    holder.play.setVisibility(View.INVISIBLE);
                }

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                        holder.play.setVisibility(View.VISIBLE);
                        holder.pause.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return seekerCycleModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView banname;
        TextView enname;
        TextView banglamean;
        TextView englishmean;
        ImageButton play,pause;


        public MyViewHolder(View itemView) {
            super(itemView);

            banname = (TextView) itemView.findViewById(R.id.ban);
            enname = (TextView) itemView.findViewById(R.id.eng);
            banglamean = (TextView) itemView.findViewById(R.id.banglamean);
            englishmean = (TextView) itemView.findViewById(R.id.englishMean);
            play = (ImageButton) itemView.findViewById(R.id.play);
            pause = (ImageButton) itemView.findViewById(R.id.pause);
        }
    }
}

