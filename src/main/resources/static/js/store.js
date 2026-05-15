const BASE_URL = "http://localhost:8080/api/store";

function listar() {
    $.get(BASE_URL, function(data) {
        mostrarTabla(data);
    }).fail(() => Swal.fire("Error", "The stores could not be obtained", "error"));
}

function buscarId() {
    Swal.fire({
        title: "Search Store",
        input: "number",
        inputLabel: "Enter the ID",
        showCancelButton: true,
        confirmButtonText: "Search"
    }).then(result => {
        if (result.isConfirmed && result.value) {
            $.get(BASE_URL + "/" + result.value, function(data) {
                mostrarTabla([data]);
            }).fail(() => Swal.fire("Error", "Store not found", "error"));
        }
    });
}

function mostrarInsertar() {
    $("#formularios").html(`
        <h4>Add Store</h4>
        <input type="text" id="insName" placeholder="Name"><br>
        <input type="text" id="insLocation" placeholder="Location"><br>
        <input type="number" id="insCapacity" placeholder="Capacity"><br>
        <button class="btn-orange" onclick="insertar()">Save</button>
    `);
}

function insertar() {
    const dto = {
        name: $("#insName").val(),
        location: $("#insLocation").val(),
        capacity: parseInt($("#insCapacity").val()),
    };
    $.ajax({
        url: BASE_URL,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Added store", "success");
            mostrarTabla([data]);
        },
        error: () => Swal.fire("Error", "Could not be added", "error")
    });
}

function mostrarActualizar() {
    $("#formularios").html(`
        <h4>Update Store</h4>
        <input type="number" id="updId" placeholder="Store ID"><br>
        <input type="text" id="insName" placeholder="Name"><br>
        <input type="text" id="insLocation" placeholder="Location"><br>
        <input type="number" id="insCapacity" placeholder="Capacity"><br>
        <button class="btn-green" onclick="actualizar()">Update</button>
    `);
}

function actualizar() {
    const id = $("#updId").val();
    const dto = {
        name: $("#insName").val(),
        location: $("#insLocation").val(),
        capacity: parseInt($("#insCapacity").val()),
    };
    $.ajax({
        url: BASE_URL + "/" + id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Updated Store", "success");
            mostrarTabla([data]);
        },
        error: () => Swal.fire("Error", "Could not be updated", "error")
    });
}

function mostrarEliminar() {
    $("#formularios").html(`
        <h4>Deleted Store</h4>
        <input type="number" id="delId" placeholder="ID Store"><br>
        <button class="btn-gray" onclick="eliminar()">Delete</button>
    `);
}

function eliminar() {
    const id = $("#delId").val();
    $.ajax({
        url: BASE_URL + "/" + id,
        method: "DELETE",
        success: msg => Swal.fire("Éxito", msg, "success"),
        error: () => Swal.fire("Error", "Could not be deleted", "error")
    });
}

function mostrarTabla(data) {
    let html = `
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Location</th>
                <th>Capacity</th>
            </tr>
    `;
    data.forEach(a => {
        html += `
            <tr>
                <td>${a.id}</td>
                <td>${a.name}</td>
                <td>${a.location}</td>
                <td>${a.capacity}</td>
            </tr>
        `;
    });
    html += "</table>";
    $("#tablaResultados").html(html);
}