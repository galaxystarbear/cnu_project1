document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById('reservationForm');
  const timeInput = document.getElementById('time');
  const nameInput = document.getElementById('name');
  const scheduleTable = document.getElementById('scheduleTable').querySelector('tbody');

  const reservations = {}; // 시간 → 이름

  // 00:00 ~ 23:30, 30분 간격 시간표 생성
  const timeSlots = generateTimeSlots("00:00", "24:00", 30);
  renderSchedule();

form.addEventListener('submit', function (e) {
  e.preventDefault();
  const time = timeInput.value.substring(0,5);
  const name = nameInput.value;

  if (!timeSlots.includes(time)) {
    alert("예약 가능한 시간이 아닙니다.");
    return;
  }

  if (reservations[time]) { 
    alert("이미 예약된 시간입니다.");
    return;
  }

  reservations[time] = name;
  renderSchedule();
  form.reset();
});

function generateTimeSlots(start, end, intervalMinutes) {
  const slots = [];
  let [sh, sm] = start.split(":").map(Number);
  const [eh, em] = end.split(":").map(Number);

  while (sh < eh || (sh === eh && sm < em)) {
    slots.push(`${String(sh).padStart(2, '0')}:${String(sm).padStart(2, '0')}`);
    sm += intervalMinutes;
    if (sm >= 60) {
      sh += 1;
      sm -= 60;
    }
  }
  return slots;
}


function renderSchedule() {
  scheduleTable.innerHTML = '';
  timeSlots.forEach(time => {
    const row = document.createElement('tr');
    const timeCell = document.createElement('td');
    const statusCell = document.createElement('td');

    timeCell.textContent = time;

    if (reservations[time]) {
      statusCell.textContent = reservations[time]; // 예약자 이름만 표시
      row.classList.add('reserved');
    } else {
      statusCell.textContent = '(비어 있음)'; // 예약되지 않은 상태
      row.classList.add('available');
    }

    row.appendChild(timeCell);
    row.appendChild(statusCell);
    scheduleTable.appendChild(row);
  });
}
});
