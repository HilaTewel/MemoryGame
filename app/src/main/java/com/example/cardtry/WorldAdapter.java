package com.example.cardtry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorldAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int LOCKED_CELL = 1;
    private final int OPEN_CELL = 2;

    private List<World> worlds;
    Context context;
    BuyLevelListener buyLevelListener;

    public void setListener(BuyLevelListener listener){
        buyLevelListener = listener;
    }

    interface BuyLevelListener{
        void onBuyLevelBtnClick(int position, View view);
    }

    public WorldAdapter(List<World> worlds, Context context) {
        this.worlds = worlds;
        this.context = context;
    }

    //create 2 view holders for each cell type.
     class LockedCellViewHolder extends RecyclerView.ViewHolder{

        Button buyLevel;
        //could change price

        public LockedCellViewHolder(@NonNull View itemView) {
            super(itemView);
           buyLevel = itemView.findViewById(R.id.buyLevelBtn);
           buyLevel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    if (buyLevelListener != null){
                        buyLevelListener.onBuyLevelBtnClick(getAdapterPosition(), v);
                    }
               }
           });
        }
    }

    class OpenCellViewHolder extends RecyclerView.ViewHolder{

        Button levelBtn1;
        Button levelBtn2;
        Button levelBtn3;
        TextView worldName;
        LinearLayout linearLayout;

        public OpenCellViewHolder(@NonNull View itemView) {
            super(itemView);

            levelBtn1 = itemView.findViewById(R.id.levelBtn1);
            levelBtn2 = itemView.findViewById(R.id.levelBtn2);
            levelBtn3 = itemView.findViewById(R.id.levelBtn3);
            worldName = itemView.findViewById(R.id.worldTitle);
            linearLayout = itemView.findViewById(R.id.openCellLinearLayout);

            levelBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   World world = worlds.get(getAdapterPosition());
                    Intent intent = new Intent(context, world.getLevelEasy());

                    context.startActivity(intent);
                }
            });

            levelBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    World world = worlds.get(getAdapterPosition());
                    Intent intent = new Intent(context, world.getLevelMedium());

                    context.startActivity(intent);

                }
            });

            levelBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    World world = worlds.get(getAdapterPosition());
                    Intent intent = new Intent(context, world.getLevelHard());

                    context.startActivity(intent);


                }
            });
        }
    }

    //override 3 methods


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case OPEN_CELL:{
                View open = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_world_cell, parent, false);
                OpenCellViewHolder openCellViewHolder = new OpenCellViewHolder(open);
                return openCellViewHolder;
            }
            case LOCKED_CELL:{
                View locked = LayoutInflater.from(parent.getContext()).inflate(R.layout.locked_world_cell, parent, false);
                LockedCellViewHolder lockedCellViewHolder = new LockedCellViewHolder(locked);
                return lockedCellViewHolder;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        World world = worlds.get(position);
        if (world.isOpen()){
            ((OpenCellViewHolder)holder).worldName.setText(world.getTitle());
            ((OpenCellViewHolder)holder).linearLayout.setBackgroundResource(world.getBackgroundRes());

            if(world.isLevelMediumOpen()){
                ((OpenCellViewHolder)holder).levelBtn2.setEnabled(true);
            }

            if(world.isLevelHardOpen()){
                ((OpenCellViewHolder)holder).levelBtn3.setEnabled(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return worlds.size();
    }

    //for different types of cells override this:
    @Override
    public int getItemViewType(int position) {
        if (worlds.get(position).isOpen()){
            return OPEN_CELL;
        }
        return LOCKED_CELL;
    }
}
