package com.svalero.gardeners.presenter;

import com.svalero.gardeners.contract.RegisterClientContract;
import com.svalero.gardeners.domain.Client;
import com.svalero.gardeners.model.RegisterClientModel;

public class RegisterClientPresenter implements RegisterClientContract.Presenter, RegisterClientContract.Model.OnRegisterClientListener{

    private RegisterClientContract.Model model;
    private RegisterClientContract.View view;

    public RegisterClientPresenter(RegisterClientContract.View view) {
        this.model = new RegisterClientModel();
        this.view = view;
    }

    @Override
    public void registerClient(Client client) {
        if (client.getName().isEmpty()) {
            view.showErrorMessage("The mark field cannot be empty");
            return;
        }

        model.registerClient(client, this);

    }

    @Override
    public void onRegisterClientSuccess(Client registeredClient) {
        view.showSuccessMessage("Client correctly registered with the identifier" + registeredClient.getId());

    }

    @Override
    public void onRegisterClientError(String message) {
        view.showErrorMessage(message);

    }
}
