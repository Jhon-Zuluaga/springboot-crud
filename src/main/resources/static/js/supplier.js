const BASE_URL = "http://localhost:8080/api/supplier";

function listar() {
    $.get(BASE_URL, function(data) {
        mostrarTabla(data);
    }).fail(() => Swal.fire("Error", "The suppliers could not be obtained", "error"));
}

function buscarId() {
    Swal.fire({
        title: "Search Supplier",
        input: "number",
        inputLabel: "Enter the ID",
        showCancelButton: true,
        confirmButtonText: "Search"
    }).then(result => {
        if (result.isConfirmed && result.value) {
            $.get(BASE_URL + "/" + result.value, function(data) {
                mostrarTabla([data]);
            }).fail(() => Swal.fire("Error", "Supplier not found", "error"));
        }
    });
}

function mostrarInsertar() {
    $("#formularios").html(`
        <h4>Add Suplier</h4>
        <input type="text" id="insName" placeholder="Name"><br>
        <input type="text" id="insPhone" placeholder="Phone"><br>
        <input type="text" id="insEmail" placeholder="Email"><br>
        <input type="text" id="insAddress" placeholder="Address"><br>
        <button class="btn-orange" onclick="insertar()">Save</button>
    `);
}

function insertar() {
    const dto = {
        name: $("#insName").val(),
        email: $("#insEmail").val(),
        phone: $("#insPhone").val(),
        address: $("#insAddress").val()
    };
    $.ajax({
        url: BASE_URL,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Added supplier", "success");
            mostrarTabla([data]);
        },
        error: () => Swal.fire("Error", "Could not be added", "error")
    });
}

function mostrarActualizar() {
    $("#formularios").html(`
        <h4>Update supplier</h4>
        <input type="number" id="updId" placeholder="Supplier ID"><br>
        <input type="text" id="insName" placeholder="Name"><br>
        <input type="text" id="insPhone" placeholder="Phone"><br>
        <input type="text" id="insEmail" placeholder="Email"><br>
        <input type="text" id="insAddress" placeholder="Address"><br>
        <button class="btn-green" onclick="actualizar()">Update</button>
    `);
}

function actualizar() {
    const id = $("#updId").val();
    const dto = {
        name: $("#insName").val(),
        email: $("#insEmail").val(),
        phone: $("#insPhone").val(),
        address: $("#insAddress").val()
    };
    $.ajax({
        url: BASE_URL + "/" + id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Updated Supplier", "success");
            mostrarTabla([data]);
        },

        error: () => Swal.fire("Error", "Could not be updated", console.log(error))
    });
}

function mostrarEliminar() {
    $("#formularios").html(`
        <h4>Deleted Supplier</h4>
        <input type="number" id="delId" placeholder="ID Supplier"><br>
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
                <th>Phone</th>
                <th>Email</th>
                <th>Address</th>
            </tr>
    `;
    data.forEach(a => {
        html += `
            <tr>
                <td>${a.id}</td>
                <td>${a.name}</td>
                <td>${a.phone}</td>
                <td>${a.email}</td>
                <td>${a.address}</td>
            </tr>
        `;
    });
    html += "</table>";
    $("#tablaResultados").html(html);
}