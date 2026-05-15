const BASE_URL = "http://localhost:8080/api/sale";

function listar() {
    $.get(BASE_URL, function(data) {
        mostrarTabla(data);
    }).fail(() => Swal.fire("Error", "The sales could not be obtained", "error"));
}

function buscarId() {
    Swal.fire({
        title: "Search Sale",
        input: "number",
        inputLabel: "Enter the ID",
        showCancelButton: true,
        confirmButtonText: "Search"
    }).then(result => {
        if (result.isConfirmed && result.value) {
            $.get(BASE_URL + "/" + result.value, function(data) {
                mostrarTabla([data]);
            }).fail(() => Swal.fire("Error", "Sale not found", "error"));
        }
    });
}

function mostrarInsertar() {
    $("#formularios").html(`
        <h4>Add Sale</h4>
        <input type="number" id="insTotal" placeholder="Total"><br>
        <input type="text" id="insClient" placeholder="Client"><br>
        <input type="date" id="insDate" placeholder="Date"><br>
        <input type="text" id="insArticles" placeholder="Article IDs (e.g: 1,2,3)"><br>
        <button class="btn-orange" onclick="insertar()">Save</button>
    `);
}

function insertar() {
    const articleIds = $("#insArticles").val()
        .split(",")
        .map(id => parseInt(id.trim()))
        .filter(id => !isNaN(id)); // elimina valores vacíos o inválidos

    const dto = {
        client: $("#insClient").val(),
        date: $("#insDate").val(),
        articleIds: articleIds
    };

    $.ajax({
        url: BASE_URL,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(dto),
        success: data => {
            Swal.fire("Éxito", "Added sale", "success");
            mostrarTabla([data]);
        },
        error: (xhr) => {
            console.log(xhr.responseText);
            Swal.fire("Error", "Could not be added", "error");
        }
    });
}

function mostrarEliminar() {
    $("#formularios").html(`
        <h4>Sale Product</h4>
        <input type="number" id="delId" placeholder="ID Sale"><br>
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
                <th>Total</th>
                <th>Client</th>
                <th>Date</th>
            </tr>
    `;
    data.forEach(a => {
        html += `
            <tr>
                <td>${a.id}</td>
                <td>${a.total}</td>
                <td>${a.client}</td>  
                <td>${a.date}</td>
            </tr>
        `;
    });
    html += "</table>";
    $("#tablaResultados").html(html);
}