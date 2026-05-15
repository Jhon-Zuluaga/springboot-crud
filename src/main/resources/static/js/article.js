const BASE_URL = "http://localhost:8080/api/article";

function listar() {
    $.get(BASE_URL, function(data) {
        mostrarTabla(data);
    }).fail(() => Swal.fire("Error", "The products could not be obtained", "error"));
}

function buscarId() {
    Swal.fire({
        title: "Search Product",
        input: "number",
        inputLabel: "Enter the ID",
        showCancelButton: true,
        confirmButtonText: "Search"
    }).then(result => {
        if (result.isConfirmed && result.value) {
            $.get(BASE_URL + "/" + result.value, function(data) {
                mostrarTabla([data]);
            }).fail(() => Swal.fire("Error", "Product not found", "error"));
        }
    });
}

function mostrarInsertar() {
    $("#formularios").html(`
        <h4>Add Product</h4>
        <input type="text" id="insName" placeholder="Name"><br>
        <input type="number" id="insPrice" placeholder="Price"><br>
        <input type="number" id="insStock" placeholder="Stock"><br>
        <input type="number" id="insSupplierId" placeholder="ID Supplier"><br>
        <input type="number" id="insStoreId" placeholder="ID Store"><br>
        <button class="btn-orange" onclick="insertar()">Save</button>
    `);
}

function insertar() {
    const dto = {
        name: $("#insName").val(),
        price: parseFloat($("#insPrice").val()),
        stock: parseInt($("#insStock").val()),
        supplierId: parseInt($("#insSupplierId").val()),
        storeId: parseInt($("#insStoreId").val())
    };
    $.ajax({
        url: BASE_URL,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Added product", "success");
            mostrarTabla([data]);
        },
        error: () => Swal.fire("Error", "Could not be added", "error")
    });
}

function mostrarActualizar() {
    $("#formularios").html(`
        <h4>Update Product</h4>
        <input type="number" id="updId" placeholder="Product ID"><br>
        <input type="text" id="updName" placeholder="New Name"><br>
        <input type="number" id="updPrice" placeholder="New Price"><br>
        <input type="number" id="updStock" placeholder="New stock"><br>
        <input type="number" id="updSupplierId" placeholder="ID Supplier"><br>
        <input type="number" id="updStoreId" placeholder="ID Store"><br>
        <button class="btn-green" onclick="actualizar()">Update</button>
    `);
}

function actualizar() {
    const id = $("#updId").val();
    const dto = {
        name: $("#updName").val(),
        price: parseFloat($("#updPrice").val()),
        stock: parseInt($("#updStock").val()),
        supplierId: parseInt($("#updSupplierId").val()),
        storeId: parseInt($("#updStoreId").val())
    };
    $.ajax({
        url: BASE_URL + "/" + id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Updated Product", "success");
            mostrarTabla([data]);
        },
        error: () => Swal.fire("Error", "Could not be updated", "error")
    });
}

function mostrarEliminar() {
    $("#formularios").html(`
        <h4>Deleted Product</h4>
        <input type="number" id="delId" placeholder="ID Product"><br>
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
                <th>Price</th>
                <th>Stock</th>
                <th>Supplier ID</th>
                <th>Store ID</th>
            </tr>
    `;
    data.forEach(a => {
        html += `
            <tr>
                <td>${a.id}</td>
                <td>${a.name}</td>
                <td>${a.price}</td>
                <td>${a.stock}</td>
                <td>${a.supplierId}</td>
                <td>${a.storeId}</td>
            </tr>
        `;
    });
    html += "</table>";
    $("#tablaResultados").html(html);
}