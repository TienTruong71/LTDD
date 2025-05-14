package com.example.projectltddclother.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectltddclother.Adapter.PopularAdapter;
import com.example.projectltddclother.Helper.FavoriteManager;
import com.example.projectltddclother.Model.ItemsModel;
import com.example.projectltddclother.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteManager favoriteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.recyclerFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteManager = new FavoriteManager(this);
        ArrayList<ItemsModel> favoriteItems = favoriteManager.getFavorites();

        PopularAdapter adapter = new PopularAdapter(favoriteItems);
        recyclerView.setAdapter(adapter);
    }
}
