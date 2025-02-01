<?php
require_once "config.php";
require_once "models/Gardener.php";

class GardenerController {
    public static function getAllGardeners() {
        $response = file_get_contents(API_URL . "/gardeners");
        $gardenersArray = json_decode($response, true);
        $gardeners = [];

        foreach ($gardenersArray as $data) {
            $gardeners[] = new Gardener($data);
        }

        return $gardeners;
    }
}
?>

