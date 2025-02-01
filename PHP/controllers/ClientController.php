<?php
require_once "config.php";
require_once "models/Client.php";

class ClientController {
    public static function getAllClients() {
        $response = file_get_contents(API_URL . "/clients");
        $clientsArray = json_decode($response, true);
        $clients = [];

        foreach ($clientsArray as $data) {
            $clients[] = new Client($data);
        }

        return $clients;
    }
}
?>

