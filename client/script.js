window.onclick = event => {
    if (event.target == userModal) {
        userModal.style.display = "none"
    }
    if (event.target == updateModal) {
        updateModal.style.display = "none"
    }
    if (event.target == editModal) {
        editModal.style.display = "none"
    }
}
window.onload = () => handleGetUsers(); // Fetch users on page load


const BASE_URL = [
    "http://localhost:8080", //! local
    "https://springboot-server-szhf.onrender.com", //? production
]
const API_URL = BASE_URL[1]
const userModal = document.getElementById("userModal")
const updateModal = document.getElementById("updateModal")
const editModal = document.getElementById("editModal")
const openUserForm = () => (userModal.style.display = "flex")
const openUpdateForm = () => (updateModal.style.display = "flex")
const openEditForm = (user) => {
    document.getElementById("userId").textContent = user.id;
    document.getElementById("editName").value = user.name;
    document.getElementById("editAge").value = user.age;
    document.getElementById("editEmail").value = user.email;

    const editModal = document.getElementById("editModal");
    editModal.style.display = "flex";
}

const closeModal = () => {
    userModal.style.display = "none"
    updateModal.style.display = "none"
    editModal.style.display = "none"
}

const updateTable = users => {
    const tbody = document.getElementById("userTable").getElementsByTagName("tbody")[0]
    tbody.innerHTML = "" // Clear existing rows

    users.forEach(user => {
        const row = tbody.insertRow()
        row.insertCell().textContent = user.id
        row.insertCell().textContent = user.name
        row.insertCell().textContent = user.age
        row.insertCell().textContent = user.email
        const actionsCell = row.insertCell()

        const deleteButton = document.createElement("img")
        deleteButton.width = 20
        deleteButton.src = "https://img.icons8.com/?size=100&id=67884&format=png&color=f32020"
        deleteButton.style.cursor = "pointer"
        deleteButton.onclick = () => handleDeleteUser(user.id)
        actionsCell.appendChild(deleteButton)

        const editButton = document.createElement("img")
        editButton.width = 20
        editButton.src = "https://img.icons8.com/?size=100&id=71201&format=png&color=20b0f3"
        editButton.style.cursor = "pointer"
        editButton.onclick = () => openEditForm(user)
        actionsCell.appendChild(editButton)
    })
}



const handleGetUser = async () => {
    const userId = prompt("Enter user ID:")
    if (!userId) {
        return
    }

    try {
        let response = await fetch(`${API_URL}/users/${userId}`)
        if (!response.ok) {
            throw new Error("User not found with that id")
        }

        let data = await response.json()
        alert(JSON.stringify(data))
    } catch (error) {
        alert(error.message)
    }
}

const handleGetUsers = async () => {
    try {
        let response = await fetch(`${API_URL}/users`)
        let data = await response.json()
        updateTable(data)
    } catch (error) {
        alert(error.message)
    }
}
const resetForm = () => {
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    document.getElementById("email").value = "";
}

const handleAddUser = async (event) => {
    event.preventDefault()
    const name = document.getElementById("name").value
    const age = document.getElementById("age").value
    const email = document.getElementById("email").value

    if (!name || !age || !email) {
        alert("All fields are required!")
        return
    }

    const user = { name, age: parseInt(age), email }

    try {
        let response = await fetch(`${API_URL}/users`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user),
        })

        await response.json()
        alert("User added successfully!")
        document.getElementById("userModal").style.display = "none"
        await handleGetUsers() // Refresh the user list
    } catch (error) {
        alert(error.message)
    }
    resetForm()
}

const handleUpdateUser = async (event) => {
    event.preventDefault()
    const id = document.getElementById("updateId").value
    const name = document.getElementById("updateName").value
    const age = document.getElementById("updateAge").value
    const email = document.getElementById("updateEmail").value

    if (!id || !name || !age || !email) {
        return alert("All the fields to update are required!")
    }

    const user = { id, name, age: age ? parseInt(age) : null, email }

    try {
        let response = await fetch(`${API_URL}/users/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user),
        })

        if (!response.ok) {
            throw new Error("User not found or update failed!")
        }

        await response.json()
        alert("User updated successfully!")
        updateModal.style.display = "none"
        await handleGetUsers() //* Refresh the user list
    } catch (error) {
        alert(error.message)
    }
}

const handleEditUser = async (event) => {
    event.preventDefault();

    const id = document.getElementById("userId").textContent;
    const name = event.target.name.value.trim();
    const age = event.target.age.value.trim();
    const email = event.target.email.value.trim();

    // Validation for ID and at least one field to update
    if (!id || (!name && !age && !email)) {
        return alert("ID and at least one field to update are required!");
    }

    // Create updated user object
    const user = {
        id,
        name: name || undefined,  // Set to undefined if not provided
        age: age ? parseInt(age) : undefined,  // Set to undefined if not provided
        email: email || undefined,  // Set to undefined if not provided
    };

    try {
        // Make the API request to update the user
        let response = await fetch(`${API_URL}/users/${id}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user),
        });

        if (!response.ok) {
            throw new Error("User not found or update failed");
        }

        await response.json();
        alert("User edited successfully!");

        // Optionally refresh the table and close the modal
        closeModal();
        await handleGetUsers(); // Refresh the user list
    } catch (error) {
        alert(error.message);
    }
};

const handleDeleteUser = async id => {
    if (!confirm("Are you sure you want to delete this user?")) {
        return
    }

    try {
        let response = await fetch(`${API_URL}/users/${id}`, { method: "DELETE" })

        if (!response.ok) {
            throw new Error("User not found or delete failed")
        }

        alert("User deleted successfully!")
        await handleGetUsers()
    } catch (error) {
        alert(error.message)
    }
}

const handleDeleteUsers = async () => {
    if (!confirm("Are you sure you want to delete all the users?")) {
        return
    }
    try {
        await fetch(`${API_URL}/users`, { method: "DELETE" })

        alert("All Users deleted successfully!")
        await handleGetUsers()
    } catch (error) {
        alert(error.message)
    }
}
