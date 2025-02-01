<?php
class Gardener {
    public $id;
    public $name;
    public $phone;
    public $address;
    public $hireDate;
    public $licensePlate;
    public $selfEmployed;

    public function __construct($data) {
        $this->id = $data['id'];
        $this->name = $data['name'];
        $this->phone = $data['phone'];
        $this->address = $data['address'];
        $this->hireDate = $data['hireDate'];
        $this->licensePlate = $data['licensePlate'];
        $this->selfEmployed = $data['selfEmployed'];
    }
}
?>

