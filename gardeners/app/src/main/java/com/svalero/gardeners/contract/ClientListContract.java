package com.svalero.gardeners.contract;

import com.svalero.gardeners.domain.Client;

import java.util.ArrayList;
import java.util.List;

public interface ClientListContract {

    void loadClients();

    interface Model {
        interface OnLoadClientsListener {
            void onLoadClientsSuccess(ArrayList<Client> clientList);
            void onLoadClientsError(String message);
        }
        void loadClients(OnLoadClientsListener listener);
    }

    interface View {
        void listClients(ArrayList<Client> clientList);
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void loadClients();
    }
}
