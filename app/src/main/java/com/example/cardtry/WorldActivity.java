package com.example.cardtry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class WorldActivity extends Activity {

    List<World> worlds;
    SharedPreferences sp;
    int coins;
    TextView coinsTV;
    WorldAdapter worldAdapter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        sp = getSharedPreferences("details", MODE_PRIVATE);
        coins = sp.getInt("coins", 0);
        coinsTV.setText(coins + "");

        worlds.get(0).setLevelMediumOpen(sp.getBoolean("AnimalsEasy", false));
        worlds.get(0).setLevelHardOpen(sp.getBoolean("AnimalsMedium", false));

        //if hardest level passed open world
        if (sp.getBoolean("AnimalsHard", false)){
            worlds.get(1).setOpen(true);
        }
        worlds.get(1).setLevelMediumOpen(sp.getBoolean("SeaEasy", false));
        worlds.get(1).setLevelHardOpen(sp.getBoolean("SeaMedium", false));

        //if hardest level passed open world
        if (sp.getBoolean("SeaHard", false)){
            worlds.get(2).setOpen(true);
        }

        worlds.get(2).setLevelMediumOpen(sp.getBoolean("SpaceEasy", false));
        worlds.get(2).setLevelHardOpen(sp.getBoolean("SpaceMedium", false));

        worldAdapter.notifyDataSetChanged();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worlds_activity);

        final RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        sp = getSharedPreferences("details", MODE_PRIVATE);
        coins = sp.getInt("coins", 0);
        coinsTV = findViewById(R.id.worldsNumOfCoinsTV);
        coinsTV.setText(coins + "");
        Button homeBtn = findViewById(R.id.backHome);
        ToggleButton musicBtn = findViewById(R.id.worldsMusic);
        ToggleButton soundBtn = findViewById(R.id.worldsSound);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorldActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        worlds = new ArrayList<>();


        worlds.add( new World("AnimalsWorld",getString(R.string.animals), R.drawable.animals_level_background_dark2, true, true, false,
                false, AnimalsLevelEasy.class, AnimalsLevelMedium.class, AnimalsLevelHard.class ));
        worlds.add( new World("SeaWorld",getString(R.string.Sea), R.drawable.sea_level_background_dark2, false, true, false,
                false, SeaLevelEasy.class, SeaLevelMedium.class, SeaLevelHard.class ));
        worlds.add( new World("SpaceWorld",getString(R.string.space), R.drawable.space_level_background_dark2, false, true, false,
                false, SpaceLevelEasy.class, SpaceLevelMedium.class, SpaceLevelHard.class ));
        worlds.add( new World("NoName","NoName", R.drawable.animals_level_background_dark2, false, true, false,
                false, AnimalsLevelEasy.class, AnimalsLevelMedium.class, AnimalsLevelHard.class ));
        worlds.add( new World("NoName","NoName", R.drawable.animals_level_background_dark2, false, true, false,
                false, AnimalsLevelEasy.class, AnimalsLevelMedium.class, AnimalsLevelHard.class ));



        for (World world: worlds){
            if (sp.getBoolean(world.getName(),false)){
                world.setOpen(true);
            }
        }

        worlds.get(0).setLevelMediumOpen(sp.getBoolean("AnimalsEasy", false));
        worlds.get(0).setLevelHardOpen(sp.getBoolean("AnimalsMedium", false));

        //if hardest level passed open world
        if (sp.getBoolean("AnimalsHard", false)){
            worlds.get(1).setOpen(true);
        }
        worlds.get(1).setLevelMediumOpen(sp.getBoolean("SeaEasy", false));
        worlds.get(1).setLevelHardOpen(sp.getBoolean("SeaMedium", false));

        //if hardest level passed open world
        if (sp.getBoolean("SeaHard", false)){
            worlds.get(2).setOpen(true);
        }

        worlds.get(2).setLevelMediumOpen(sp.getBoolean("SpaceEasy", false));
        worlds.get(2).setLevelHardOpen(sp.getBoolean("SpaceMedium", false));

        worldAdapter = new WorldAdapter(worlds, this);
        worldAdapter.setListener(new WorldAdapter.BuyLevelListener() {
            @Override
            public void onBuyLevelBtnClick(int position, View view) {

                if (position >=3 ){
                    Toast.makeText(WorldActivity.this, getText(R.string.coming_soon), Toast.LENGTH_SHORT).show();
                } else if (sp.getInt("coins", 0) < 5000){
                    Toast.makeText(WorldActivity.this, getText(R.string.not_enough_coins), Toast.LENGTH_SHORT).show();
                }
                else {
                    worlds.get(position).setOpen(true);
                    coins = coins - 5000;
                    worldAdapter.notifyDataSetChanged();
                    coinsTV.setText(coins + "");
                }
            }
        });

        recyclerView.setAdapter(worldAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        for (World world: worlds){
            editor.putBoolean(world.getName(), world.isOpen());
        }
        editor.putInt("coins", coins);
        editor.commit();
    }
}
