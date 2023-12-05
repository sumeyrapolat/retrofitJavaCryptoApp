package com.sumeyra.retrofitjava.adapter;

import android.graphics.Color;
import android.sax.RootElement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sumeyra.retrofitjava.R;
import com.sumeyra.retrofitjava.model.CryptoModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    private List<CryptoModel> cryptoModelList;
    private  String[] colors = {"#ff00a8","#bedb92","#b3c9f4","#bd33a4","#1975b5","#bd33a4"};

    public RecyclerViewAdapter(List<CryptoModel> cryptoModelList) {
        this.cryptoModelList = cryptoModelList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoModelList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoModelList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel,String[] colors, Integer position ){
            itemView.setBackgroundColor(Color.parseColor(colors[position%6]));
            textName= itemView.findViewById(R.id.textName);
            textPrice=itemView.findViewById(R.id.textPrice);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price) ;
        }
    }
}
