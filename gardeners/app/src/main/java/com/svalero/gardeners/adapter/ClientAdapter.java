package com.svalero.gardeners.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.gardeners.DetailActivityView;
import com.svalero.gardeners.R;
import com.svalero.gardeners.domain.Client;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientHolder> {

    private List<Client> clientList;

    public ClientAdapter(List<Client> clientList) {
        this.clientList = clientList;
    }


    @NonNull
    @Override
    public ClientAdapter.ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientsview_item, parent, false);
        return new ClientHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.ClientHolder holder, int position) {
        holder.name.setText(clientList.get(position).getName());
        holder.phone.setText(clientList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class ClientHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phone;

        public ClientHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            phone = itemView.findViewById(R.id.item_phone);

            itemView.setOnClickListener(view -> {
                long clientId = clientList.get(getAdapterPosition()).getId();
                Intent intent = new Intent(itemView.getContext(), DetailActivityView.class);
                intent.putExtra("clientId", clientId);
                    startActivity(itemView.getContext(), intent, null);

            });
        }
    }
}
