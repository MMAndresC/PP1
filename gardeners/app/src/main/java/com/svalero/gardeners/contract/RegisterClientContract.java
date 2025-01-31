package com.svalero.gardeners.contract;

import com.svalero.gardeners.domain.Client;

public interface RegisterClientContract {

    interface Model {
        interface OnRegisterClientListener {
            void onRegisterClientSuccess(Client registeredClient);
            void onRegisterClientError(String message);
        }
        void registerClient(Client client, OnRegisterClientListener listener);

    }

    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void registerClient(Client client);
    }
}
