<?php
require_once __DIR__ . "/../controllers/GardenerController.php";

// Obtener clientes de la API
$gardeners = GardenerController::getAllGardeners();

// Manejo de error si la API no responde
if (!is_array($gardeners)) {
    $gardeners = [];
}
?>

<div class="container mt-5">
    <h2 class="mb-4">Listado de Gardeners</h2>

    <?php if (empty($gardeners)): ?>
        <p class="text-danger">No se pudieron cargar los gardeners. Verifica la conexión con la API.</p>
    <?php else: ?>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Hire Date</th>
                <th>License Plate</th>
                <th>Self employed</th>
            </tr>
            </thead>
            <tbody>
            <?php foreach ($gardeners as $gardener) { ?>
                <tr>
                    <td><?= htmlspecialchars($gardener->id) ?></td>
                    <td><?= htmlspecialchars(isset($gardener->name) ?$gardener->name : '')  ?></td>
                    <td><?= htmlspecialchars(isset($gardener->phone) ?$gardener->phone : '')  ?></td>
                    <td><?= htmlspecialchars(isset($gardener->address) ?$gardener->address : '')  ?></td>
                    <td><?= htmlspecialchars(isset($gardener->hireDate) ?$gardener->hireDate : '')  ?></td>
                    <td><?= htmlspecialchars(isset($gardener->licensePlate) ?$gardener->licensePlate : '')  ?></td>
                    <td><?= $gardener->selfEmployed ? 'Sí' : 'No' ?></td>
                </tr>
            <?php } ?>
            </tbody>
        </table>
    <?php endif; ?>
</div>
