package com.ak222102468.forex2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class ForexAdapter extends RecyclerView.Adapter<ForexViewHolder> {
    private JSONObject _rates;
    private JSONArray _names;

    private JSONObject _currencyNames;

    public ForexAdapter(JSONObject rates, JSONObject currencyNames){
        this._rates = rates;
        this._names = rates.names();
        this._currencyNames = currencyNames;
    }

    @NonNull
    @Override
    public ForexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_forex, parent, false);
        return new ForexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForexViewHolder holder, int position) {
        try {
            String kode = _names.get(position).toString(); // misalnya: AUD
            holder.kodeTextView.setText(kode);

            double kurs = _rates.getDouble(kode);
            double USD = _rates.getDouble("USD");
            double IDR = _rates.getDouble("IDR");

            double baseIDR = USD / kurs * IDR;

            DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");
            String kursformat = decimalFormat.format(baseIDR);
            holder.kursTextView.setText(kursformat); // nilai rupiah

            // ambil nama dari JSON (jika ada), atau fallback ke "Unknown Currency"
            String nama = _currencyNames.optString(kode, "Unknown Currency");
            holder.namaTextView.setText(nama);

        } catch (JSONException e) {
            Log.e("*tw*", "JSON error: " + e.getMessage());
        }
    }


//    @Override
//    public void onBindViewHolder(@NonNull ForexViewHolder holder, int position) {
//        try{
//            String kode = _names.get(position).toString();
//            holder.kodeTextView.setText(kode);
//
//            double kurs = _rates.getDouble(kode);
//            double USD = _rates.getDouble("USD");
//            double IDR = _rates.getDouble("IDR");
//
//            double baseIDR = USD/kurs*IDR;
//
//            DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");
//            String kurs_2 = decimalFormat.format(baseIDR);
//            holder.kursTextView.setText(kurs_2);
//        } catch (JSONException e){
//            Log.e("*tw*", e.getMessage());
//            return;
//        }
//    }

    @Override
    public int getItemCount() {
        return _names.length();
    }
}
