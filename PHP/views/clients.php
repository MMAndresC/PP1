<?php
require_once __DIR__ . "/../controllers/ClientController.php";

// Obtener clientes de la API
$clients = ClientController::getAllClients();

// Manejo de error si la API no responde
if (!is_array($clients)) {
    $clients = [];
}
?>

<div class="container mt-5">
    <h2 class="mb-4">Listado de Clientes</h2>

    <?php if (empty($clients)): ?>
        <p class="text-danger">No se pudieron cargar los clientes. Verifica la conexión con la API.</p>
    <?php else: ?>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Tamaño del Jardín</th>
                <th>Fin de Contrato</th>
                <th>VIP</th>
            </tr>
            </thead>
            <tbody>
            <?php foreach ($clients as $client) { ?>
                <tr>
                    <td><?= htmlspecialchars(isset($client->id) ? $client->id : '') ?></td>
                    <td><?= htmlspecialchars(isset($client->name) ? $client->name : '') ?></td>
                    <td><?= htmlspecialchars(isset($client->phone) ? $client->phone : '')  ?></td>
                    <td><?= htmlspecialchars(isset($client->address) ? $client->address : '')  ?></td>
                    <td><?= htmlspecialchars(isset($client->gardenSize) ? $client->gardenSize : '')  ?>m²</td>
                    <td><?= htmlspecialchars(isset($client->contractEnd) ? $client->contractEnd : '')  ?></td>
                    <td><?= $client->vip ? 'Sí' : 'No' ?></td>
                </tr>
            <?php } ?>
            </tbody>
        </table>
    <?php endif; ?>
</div>
