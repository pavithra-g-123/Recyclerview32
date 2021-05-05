package com.example.recyclerview_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter< CustomAdapter.CustomViewHolder >{

    ArrayList<ModelClass> data ;
    View.OnClickListener L;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item , parent , false );
        return new CustomViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.sno.setText( ""+data.get(position).getSno() );
        holder.BG.setText( data.get(position).getBG() );
        holder.name.setText( data.get(position).getName() );
        holder.phone_no.setText( ""+data.get(position).getPhone_no() );
        holder.date.setText( data.get(position).getDate() );
        holder.address.setText( data.get(position).getAddress() );
        holder.l.setOnClickListener(L);
        holder.l.setTag(new String[]{data.get(position).getID(),data.get(position).getBG()});
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView address , date , phone_no , BG , sno , name ;
        Button l;
        CustomViewHolder( View itemView )
        {
            super(itemView);
            name = itemView.findViewById(R.id.textView2) ;
            address = itemView.findViewById(R.id.textView4) ;
            date = itemView.findViewById(R.id.date);
            phone_no = itemView.findViewById(R.id.textView3) ;
            sno = itemView.findViewById(R.id.textView) ;
            BG = itemView.findViewById(R.id.Blood ) ;
            l=itemView.findViewById(R.id.button);
        }
    }
}

