package com.hardwoker.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hardwoker.R;

import java.util.List;

public class AdapterTB extends RecyclerView.Adapter<AdapterTB.ViewHolder> {

    Context context;
    List<DatabaseTB> list;
    OnCallBack onCallBack;

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }


    public AdapterTB(Context context, List<DatabaseTB> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_listdata.setText(list.get(position).getIsi());

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBack.onbtndelete(list.get(position));
            }
        });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCallBack.onbtnedit(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_listdata;
        ImageButton btn_delete, btn_edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_listdata = itemView.findViewById(R.id.txt_listdata);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }
    public interface OnCallBack{
        void onbtndelete(DatabaseTB databaseTB);
        void onbtnedit(DatabaseTB databaseTB);
    }
}
