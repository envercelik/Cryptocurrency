package com.celik.cryptocurrency.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.celik.cryptocurrency.R;
import com.celik.cryptocurrency.model.CryptoCurrency;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    ArrayList<CryptoCurrency> cryptoCurrencies;
    String[] colors = {"#a2b9bc","#feb236","#d64161","#ff7b25","#b5e7a0","#405d27","#c94c4c","#034f84"};

    public RecyclerViewAdapter(ArrayList<CryptoCurrency> cryptoCurrencies) {
        this.cryptoCurrencies = cryptoCurrencies;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoCurrencies.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoCurrencies.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textViewCryptoCurrencyName;
        TextView getTextViewCryptoCurrencyPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CryptoCurrency cryptoCurrency , String[] colors , Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]));
            textViewCryptoCurrencyName = itemView.findViewById(R.id.text_view_crypto_currency_name);
            getTextViewCryptoCurrencyPrice = itemView.findViewById(R.id.text_view_crypto_currency_price);
            textViewCryptoCurrencyName.setText(cryptoCurrency.getName());
            getTextViewCryptoCurrencyPrice.setText(cryptoCurrency.getPrice());
        }
    }
}
