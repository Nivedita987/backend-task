// const API_URL = "http://localhost:8080/api/students";
// const authHeader = "Basic " + btoa("admin:admin123");

// // Load students on page load

// fetchStudents();

// document.getElementById("studentForm").addEventListener("submit", function(e) {
//   e.preventDefault();

//   const data = {
//     name: document.getElementById("name").value,
//     email: document.getElementById("email").value,
//     course: {
//       courseId: document.getElementById("courseId").value
//     }
//   };

//   fetch(API_URL, {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//       "Authorization": authHeader
//     },
//     body: JSON.stringify(data)
//   })
//   .then(() => fetchStudents());
// });

// function fetchStudents() {
//   fetch(API_URL, {
//     headers: {
//       "Authorization": authHeader
//     }
//   })
//   .then(res => res.json())
//   .then(data => {
//     const tbody = document.querySelector("#studentsTable tbody");
//     tbody.innerHTML = "";

//     data.forEach(s => {
//       tbody.innerHTML += `
//         <tr>
//           <td>${s.name}</td>
//           <td>${s.email}</td>
//           <td>${s.course.name}</td>
//         </tr>`;
//     });
//   });
// }
// function searchStudent() {
//   const name = document.getElementById("search").value;

//   fetch(`/api/students/search?name=${name}`)
//     .then(res => res.json())
//     .then(data => {
//       const table = document.getElementById("studentsTable");
//       table.innerHTML = "";
//       data.forEach(s => {
//         table.innerHTML += `
//           <tr>
//             <td>${s.name}</td>
//             <td>${s.email}</td>
//             <td>${s.course}</td>
//           </tr>`;
//       });
//     });
// }
// // function deleteStudentfr(id) {
// //   fetch(`/api/students/${id}`, {
// //     method: "DELETE"
// //   })
// //   .then(() => loadStudents());
// // }


const API = "http://localhost:8080/api/students";
const AUTH = "Basic " + btoa("admin:admin123");

function loadStudents() {
  fetch(API, {
    headers: { "Authorization": AUTH }
  })
  .then(res => res.json())
  .then(data => renderTable(data));
}

function renderTable(students) {
  const table = document.getElementById("studentsTable");
  table.innerHTML = "";

  students.forEach(s => {
    table.innerHTML += `
      <tr>
        <td>${s.name}</td>
        <td>${s.email}</td>
        <td>${s.course?.courseName || ""}</td>
        <td>
          <button onclick="deleteStudent(${s.id})">Delete</button>
        </td>
      </tr>
    `;
  });
}

function addStudent() {
  const name = document.getElementById("name").value;
  const email = document.getElementById("email").value;
  const courseId = document.getElementById("course").value;

  if (!name || !email) {
    alert("Name and Email required");
    return;
  }

  fetch(API, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": AUTH
    },
    body: JSON.stringify({
      name: name,
      email: email,
      course: { courseId: courseId }
    })
  })
  .then(res => {
    if (!res.ok) throw new Error("Add failed");
    return res.json();
  })
  .then(() => {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    loadStudents();
  })
  .catch(err => alert(err.message));
}

function deleteStudent(id) {
  fetch(`${API}/${id}`, {
    method: "DELETE",
    headers: { "Authorization": AUTH }
  })
  .then(() => loadStudents());
}

function searchStudents() {
  const name = document.getElementById("searchInput").value;

  fetch(`${API}/search?name=${name}`, {
    headers: { "Authorization": AUTH }
  })
  .then(res => res.json())
  .then(data => renderTable(data));
}
