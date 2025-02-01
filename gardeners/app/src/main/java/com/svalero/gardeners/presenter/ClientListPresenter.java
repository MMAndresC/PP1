package com.svalero.gardeners.presenter;

import com.svalero.gardeners.contract.ClientListContract;
import com.svalero.gardeners.domain.Client;
import com.svalero.gardeners.model.ClientListModel;

import java.util.ArrayList;
import java.util.List;

public class ClientListPresenter implements ClientListContract.Presenter, ClientListContract.Model.OnLoadClientsListener {

    private ClientListContract.View view;
    private ClientListContract.Model model;

    public ClientListPresenter(ClientListContract.View view) {
        this.view = view;
        model = new ClientListModel();
    }

    @Override
    public void loadClients() {
        model.loadClients(this);

    }


    @Override
    public void onLoadClientsSuccess(ArrayList<Client> clientList) {
        view.listClients(clientList);

    }

    @Override
    public void onLoadClientsError(String message) {
        view.showErrorMessage(message);

    }


}
