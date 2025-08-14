console.log("visit records");
function deleteVisitRecord(id) {
    Swal.fire({
        title: "Do you want to delete the visit record?",
        showCancelButton: true,
        confirmButtonText: "Delete",
        cancelButtonText: "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {
            fetch(`/user/visit/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        Swal.fire("Deleted!", "The visit record has been deleted.", "success")
                            .then(() => window.location.reload()); // Refresh to update table
                    } else {
                        Swal.fire("Error", "Failed to delete the visit record.", "error");
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    Swal.fire("Error", "An error occurred while deleting the visit record.", "error");
                });
        }
    });
}