package com.hardwoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hardwoker.adapter.CategoryAdapter;
import com.hardwoker.adapter.DiscountedProductAdapter;
import com.hardwoker.database.DatabaseTB;
import com.hardwoker.model.Category;
import com.hardwoker.model.DiscountedProducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecylerView, categoryRecyclerView;
    FloatingActionButton tblData;
    RecyclerView recyclerView;


    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tambah data
        tblData = findViewById(R.id.tbl_data);
        recyclerView = findViewById(R.id.productRecycler);

        tblData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogTambahData();
            }
        });

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
    // tambah data produk
    private void showDialogTambahData() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.tambah_data);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        ImageButton tblKeluar = dialog.findViewById(R.id.tbl_keluar);
        tblKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final EditText txtTambah = dialog.findViewById(R.id.txt_tambah);
        final Button tblTambah = dialog.findViewById(R.id.tbl_tambah);

        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtTambah.getText())){
                    tblTambah.setError("Silahkan Isi Data");
                }else {
                    simpanData(txtTambah.getText().toString());
                    dialog.dismiss();
                }
            }


        });
        dialog.show();
    }
    //simpan data produk
    private void simpanData(String s) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        String kunci = myRef.push().getKey();
        DatabaseTB dataTB = new DatabaseTB(kunci, s);

        myRef.child(kunci).setValue(dataTB).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
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
