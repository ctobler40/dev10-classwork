const CAPSULE_COUNT = 100;

function init() {
    const capsuleContainer = document.getElementById("capsules");
    let html = "";
    for (let i = 0; i < CAPSULE_COUNT; i++) {
<<<<<<< HEAD
        html += `<div>
=======
        html += 
        `<div>
>>>>>>> 5654a1e8fab2a380931346b8498893732bfff910
            <span id="capsuleLabel${i + 1}" class="badge badge-pill badge-success">Capsule #${i + 1}</span>
            &nbsp;<span id="guest${i + 1}">Unoccupied</span>
        </div>`
    }
    capsuleContainer.innerHTML = html;
}

<<<<<<< HEAD
init();
=======
function bookGuest() {
    
}

function updateCapsuleList() {

}

init();
>>>>>>> 5654a1e8fab2a380931346b8498893732bfff910
