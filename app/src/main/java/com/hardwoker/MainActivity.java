package com.hardwoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hardwoker.adapter.DiscountedProductAdapter;
import com.hardwoker.modul.DiscountedProducts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecylerView;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountRecylerView = findViewById(R.id.discountedRecycler);

        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, R.drawable.discountedbaja));
        discountedProductsList.add(new DiscountedProducts(2, R.drawable.discountedpipa));
        discountedProductsList.add(new DiscountedProducts(3, R.drawable.discountedsemenn));
        discountedProductsList.add(new DiscountedProducts(4, R.drawable.discountedsemen));
        discountedProductsList.add(new DiscountedProducts(5, R.drawable.discountedpasir));
        discountedProductsList.add(new DiscountedProducts(6, R.drawable.discountedbesi));





        setDiscountedRecycler(discountedProductsList);
    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecylerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecylerView.setAdapter(discountedProductAdapter);
    }

}
