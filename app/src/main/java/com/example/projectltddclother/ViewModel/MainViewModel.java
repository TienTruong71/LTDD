package com.example.projectltddclother.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectltddclother.Domain.BannerModel;
import com.example.projectltddclother.Domain.CategoryModel;
import com.example.projectltddclother.Domain.ItemsModel;
import com.example.projectltddclother.Repository.MainRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private final MainRepository repository = new MainRepository();
    public LiveData<ArrayList<CategoryModel>> loadCategory(){
        return repository. loadCategory();
    }
    public LiveData<ArrayList<BannerModel>> loadBanner(){
        return repository.loadBanner();
    }

    public LiveData<ArrayList<ItemsModel>> loadPopular(){
        return repository.loadPopular();
    }

}
