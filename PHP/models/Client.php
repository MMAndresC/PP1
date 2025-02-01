<?php
class Client {
    public $id;
    public $name;
    public $phone;
    public $address;
    public $gardenSize;
    public $contractEnd;
    public $vip;

    public function __construct($data) {
        $this->id = $data['id'];
        $this->name = $data['name'];
        $this->phone = $data['phone'];
        $this->address = $data['address'];
        $this->gardenSize = $data['gardenSize'];
        $this->contractEnd = $data['contractEnd'];
        $this->vip = $data['vip'];
    }
}
?>

