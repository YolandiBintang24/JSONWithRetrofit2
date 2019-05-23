package com.example.percobaan;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KontakAdapter extends RecyclerView.Adapter<KontakAdapter.KontakViewHolder> {

    private List<Kontak> dataList;


    @Override
    public KontakAdapter.KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_contact, parent, false);
        return new KontakAdapter.KontakViewHolder(view);
    }
    @Override
    public void onBindViewHolder(KontakAdapter.KontakViewHolder holder, int position) {
        //holder.txtId.setText(dataList.get(position).getIdContact());
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.txtAlamat.setText(dataList.get(position).getAddres());
        holder.txtNohp.setText(dataList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class KontakViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtName,txtEmail, txtAlamat,txtNohp;


        public KontakViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.textId);
            txtName = (TextView) itemView.findViewById(R.id.textName);
            txtEmail = (TextView) itemView.findViewById(R.id.textEmail);
            txtAlamat = (TextView)itemView.findViewById(R.id.textAlamat);
            txtNohp = (TextView)itemView.findViewById(R.id.textNohp);


        }


    }

    public void setContacts(List<Kontak> contacts){
        this.dataList = contacts;
        notifyDataSetChanged();
    }

    public Kontak getKontakAt(int position){
        return dataList.get(position);
    }
}
