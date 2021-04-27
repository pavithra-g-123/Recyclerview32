package com.example.recyclerview_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {
    private List<ModelClass> ModelClassList;


    public Adapter(List<ModelClass>modelClassList){
        ModelClassList=modelClassList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);

        return new viewholder(view);}

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int position) {
        int sno=ModelClassList.get(position).getSno();
        String BloodGroup=ModelClassList.get(position).getBloodGroup();
        String name=ModelClassList.get(position).getName();
        int phone_no=ModelClassList.get(position).getPhone_no();
        String address=ModelClassList.get(position).getAddress();
        viewholder.setData(sno,BloodGroup,name,phone_no,address);

    }

    @Override
    public int getItemCount() {
        return ModelClassList.size();
    }
    class  viewholder extends RecyclerView.ViewHolder {
        private TextView sno;
        private TextView BloodGroup;
        private TextView name;
        private TextView phone_no;
        private  TextView address;
        private Button confirm;

        public viewholder(@NonNull View itemView){
            super(itemView);

            sno = (TextView) itemView.findViewById(R.id.textView);
            BloodGroup=(TextView) itemView.findViewById(R.id.Blood);
            name = (TextView) itemView.findViewById(R.id.textView2);
            phone_no = (TextView) itemView.findViewById(R.id.textView3);
            address = (TextView)itemView.findViewById(R.id.textView4);
            confirm=(Button) itemView.findViewById(R.id.button);
        }
        public void setData(int snum,String Sblood,String sname,int sph,String saddress){

            sno.setText(String.valueOf(snum));
            BloodGroup.setText(Sblood);
            name.setText(sname);
            phone_no.setText(String.valueOf(sph));
            address.setText(saddress);
        }
    }
}








