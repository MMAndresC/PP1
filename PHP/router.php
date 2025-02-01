<?php
/*maneja solicitudes de la pagina , mostrando la vista requerida dependiendo del parametro pasado en la url*/

$request = isset($_GET['view']) ? $_GET['view'] : '';

if ($request == 'clients') {
    require_once "views/clients.php";
} elseif ($request == 'gardeners') {
    require_once "views/gardeners.php";
} else {
    echo "<p class='text-center text-danger'>PAGE NOT FOUND!</p>";
}
?>
