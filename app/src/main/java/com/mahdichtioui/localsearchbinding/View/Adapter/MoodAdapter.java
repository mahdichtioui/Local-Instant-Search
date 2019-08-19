package com.mahdichtioui.localsearchbinding.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahdichtioui.localsearchbinding.Model.Entity.Mood;
import com.mahdichtioui.localsearchbinding.R;

import java.util.ArrayList;
import java.util.List;

public class MoodAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Mood> lstMoods;

    public MoodAdapter(Context context, List<Mood> lstMoods) {
        this.context = context;
        this.lstMoods = lstMoods;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        return new viewHolder(inflater.inflate(R.layout.item_mood, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Mood mood = lstMoods.get(i);
        viewHolder holder = (viewHolder) viewHolder;

        holder.tvFeeling.setText(mood.getSentiment());
        holder.ivEmoji.setImageResource(mood.getEmoji());

    }

    @Override
    public int getItemCount() {
        return lstMoods.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{

        private TextView tvFeeling;
        private ImageView ivEmoji;

        public viewHolder(View itemView) {
            super(itemView);

            tvFeeling = (TextView) itemView.findViewById(R.id.tv_feeling);
            ivEmoji = (ImageView) itemView.findViewById(R.id.iv_emoji);

        }
    }

    public void filter(List<Mood> lstFiltred){
        lstMoods = new ArrayList<>();
        lstMoods.addAll(lstFiltred);
        notifyDataSetChanged();
    }

}
