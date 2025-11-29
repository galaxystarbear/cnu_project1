document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById('reservationForm');
  const timeInput = document.getElementById('time');
  const nameInput = document.getElementById('name');
  const scheduleTable = document.getElementById('scheduleTable').querySelector('tbody');

  reservations = {}; // 시간 → 이름

  // 00:00 ~ 23:30, 30분 간격 시간표 생성
  const timeSlots = generateTimeSlots("00:00", "24:00", 30);
  renderSchedule();

//submit을 제출한 경우 
///////////////////////////////////////////////////////////////////
//데이터 베이스에서 받아서 reservation 초기화하고 넣기
form.addEventListener('submit', function (e) {
  e.preventDefault();//submit의 특징인 새로고침 막기

  //받은 time, name 쓰기 쉬운 상태로 바꾸기
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
  reservations={};
  //resrvations에 추가
  fetch("http://localhost:8080/api/items")
    .then(res=> res.json())
    .then(data=>{
      data.forEach(item => {
        reservations[item.time]=item.name;
      })
    })
  renderSchedule(); // 추가한 기반으로 다시 테이블 생성
  form.reset();
});
//////////////////////////////////////////////////////////////////////////////////
function generateTimeSlots(start, end, intervalMinutes) {
  const slots = [];
  let [start_hour, start_minute] = start.split(":").map(Number);
  const [end_hour, end_minute] = end.split(":").map(Number);

  while (start_hour < end_hour || (start_hour === end_hour && start_minute < end_minute)) {
    // 문자열로 만들어서 slot에 추가
    slots.push(`${String(start_hour).padStart(2, '0')}:${String(start_minute).padStart(2, '0')}`);// padStart: 2자리로 바꾸기 9->09

    start_minute += intervalMinutes;
    if (start_minute >= 60) {
      start_hour += 1;
      start_minute -= 60;
    }
  }
  return slots;
}


function renderSchedule() {
  scheduleTable.innerHTML = '';
  //timeslots에 있는 것을 각각 time에 넣어서 확인 후 테이블에 한 row씩 추가
  timeSlots.forEach(time => {
    const row = document.createElement('tr');
    const timeCell = document.createElement('td');
    const statusCell = document.createElement('td');

    timeCell.textContent = time;

    if (reservations[time]) { //예약 확인
      statusCell.textContent = reservations[time]; // 예약자 이름 넣기
      row.classList.add('reserved'); //css에 있는 reserved라는 클래스 추가
    } else {
      statusCell.textContent = '(비어 있음)'; // 예약되지 않은 상태
      row.classList.add('available'); //css에 있는 available라는 클래스 추가
    }

    row.appendChild(timeCell); //row에 넣기
    row.appendChild(statusCell);
    scheduleTable.appendChild(row); //보여지는 실제 테이블에 만든 row 넣기
  });
}

});
