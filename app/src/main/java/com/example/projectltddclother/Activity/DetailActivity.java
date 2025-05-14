package com.example.projectltddclother.Activity;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.projectltddclother.Adapter.PicListAdapter;
import com.example.projectltddclother.Helper.FavoriteManager;
import com.example.projectltddclother.Model.ItemsModel;
import com.example.projectltddclother.Helper.ManagmentCart;
import com.example.projectltddclother.R;
import com.example.projectltddclother.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private ItemsModel object;
    private int numberOrder= 1;
    private ManagmentCart managmentCart;
    private FavoriteManager favoriteManager;
    private boolean isFavorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        favoriteManager = new FavoriteManager(this);
        managmentCart = new ManagmentCart(this);
        getBundles();
        initPicList();
        initFavoriteButton();
    }

    private void initPicList() {
        ArrayList<String> picList = new ArrayList<>(object.getPicUrl());

        Glide.with(this)
                .load(picList.get(0))
                .into(binding.pic);

        binding.picList.setAdapter(new PicListAdapter(picList, binding.pic));
        binding.picList.
                setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

    }

    private void getBundles() {
            object = (ItemsModel) getIntent().getSerializableExtra("object");
            binding.titleTxt.setText(object.getTitle());
            binding.priceTxt.setText("$" + object.getPrice());
        binding.oldPriceTxt.setText("$" + object.getOldPrice());
        binding.oldPriceTxt.setPaintFlags(binding.oldPriceTxt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        binding.descriptionTxt.setText(object.getDescription());

        binding.addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            managmentCart.insertItem(object);
        });

        binding.backBtn.setOnClickListener(v -> finish());
    }
    private void initFavoriteButton() {
        isFavorite = favoriteManager.isFavorite(object);
        updateFavIcon();

        binding.favBtn.setOnClickListener(v -> {
            if (isFavorite) {
                favoriteManager.removeFavorite(object);
            } else {
                favoriteManager.addFavorite(object);
            }
            isFavorite = !isFavorite;
            updateFavIcon();
        });
    }

    private void updateFavIcon() {
        if (isFavorite) {
            binding.favBtn.setImageResource(R.drawable.fav_filled);
        } else {
            binding.favBtn.setImageResource(R.drawable.favv);
        }
    }



}