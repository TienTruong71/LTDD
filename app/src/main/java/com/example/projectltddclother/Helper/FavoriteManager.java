package com.example.projectltddclother.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectltddclother.Model.ItemsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoriteManager {
    private static final String PREF_NAME = "favorite_items";
    private static final String KEY_LIST = "fav_list";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public FavoriteManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addFavorite(ItemsModel item) {
        ArrayList<ItemsModel> list = getFavorites();
        for (ItemsModel model : list) {
            if (model.getTitle().equals(item.getTitle())) return; // tránh thêm trùng
        }
        list.add(item);
        saveList(list);
    }

    public void removeFavorite(ItemsModel item) {
        ArrayList<ItemsModel> list = getFavorites();
        list.removeIf(model -> model.getTitle().equals(item.getTitle()));
        saveList(list);
    }

    public boolean isFavorite(ItemsModel item) {
        for (ItemsModel model : getFavorites()) {
            if (model.getTitle().equals(item.getTitle())) return true;
        }
        return false;
    }

    public ArrayList<ItemsModel> getFavorites() {
        String json = sharedPreferences.getString(KEY_LIST, "");
        Type type = new TypeToken<ArrayList<ItemsModel>>() {}.getType();
        return json.isEmpty() ? new ArrayList<>() : gson.fromJson(json, type);
    }

    private void saveList(ArrayList<ItemsModel> list) {
        sharedPreferences.edit().putString(KEY_LIST, gson.toJson(list)).apply();
    }
}
