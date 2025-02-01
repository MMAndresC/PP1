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
    <h2 class="mb-4">Clients List</h2>

    <?php if (empty($clients)): ?>
        <p class="text-danger">Error loading clients. Test API connection</p>
    <?php else: ?>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Garden size</th>
                <th>Contract end</th>
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
