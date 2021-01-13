package com.hardwoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hardwoker.adapter.CategoryAdapter;
import com.hardwoker.adapter.DiscountedProductAdapter;
import com.hardwoker.model.Category;
import com.hardwoker.model.DiscountedProducts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecylerView, categoryRecyclerView;

    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recylerview untuk diskon
        discountRecylerView = findViewById(R.id.discountedRecycler);

        //recylerview untuk kategori
        categoryRecyclerView = findViewById(R.id.categoryRecycler);


        //bagian discon
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, R.drawable.discountedbaja));
        discountedProductsList.add(new DiscountedProducts(2, R.drawable.discountedpipa));
        discountedProductsList.add(new DiscountedProducts(3, R.drawable.discountedsemenn));
        discountedProductsList.add(new DiscountedProducts(4, R.drawable.discountedsemen));
        discountedProductsList.add(new DiscountedProducts(5, R.drawable.discountedpasir));
        discountedProductsList.add(new DiscountedProducts(6, R.drawable.discountedbesi));

        //bagian kategori
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, R.drawable.ic_home_material));
        categoryList.add(new Category(2, R.drawable.ic_home_material));
        categoryList.add(new Category(3, R.drawable.ic_home_material));
        categoryList.add(new Category(4, R.drawable.ic_home_material));
        categoryList.add(new Category(5, R.drawable.ic_home_material));
        categoryList.add(new Category(6, R.drawable.ic_home_material));


        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);


    }

    // bagian discon
    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecylerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecylerView.setAdapter(discountedProductAdapter);
    }

    // bagian kategori
    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
}
