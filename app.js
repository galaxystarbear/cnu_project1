document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById('reservationForm');
    const timeInput = document.getElementById('time');
    const nameInput = document.getElementById('name');
    const scheduleTable = document.getElementById('scheduleTable').querySelector('tbody');

    const toggleHourButton = document.getElementById('toggleHourButton');
    const toggleMinuteButton = document.getElementById('toggleMinuteButton');

    const hourButtonsContainer = document.getElementById('hourButtons');
    const minuteButtonsContainer = document.getElementById('minuteButtons');
    const displayTime = document.getElementById('displayTime');
    
    let selectedHour = null;
    let selectedMinute = null;
    let reservations = {}; // 시간 → 이름
    const timeSlots = generateTimeSlots("00:00", "24:00", 30);

    const API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im93YWhqaW56aWZxdG5vdXRqcHdhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjYzMDk4ODAsImV4cCI6MjA4MTg4NTg4MH0.ZJnL0SnOKCRiRwsg46lqTM1POcZecv_43Eraq4dbXHE";
    const url = "https://owahjinzifqtnoutjpwa.supabase.co/rest/v1/reservation_time?select=*";

    getItem();
    initializeUIAndListeners()

    console.log(today);
    function getItem(){
        fetch(url, {
            headers: {
                apikey: API_KEY,
                Authorization: `Bearer ${API_KEY}`,
            },
            })
            .then(async (res) => {
                console.log("status =", res.status);
                const text = await res.text();
                console.log("raw =", text); // ✅ 여기서 [] 인지, 데이터가 있는지 바로 보임
                return JSON.parse(text);
            })
            .then((data) => {
                data.forEach((item) => {
                    const now = new Date();
                    const today =
                      `${now.getFullYear()}-${String(now.getMonth()+1).padStart(2,'0')}-${String(now.getDate()).padStart(2,'0')}`;
                    
                    console.log(item.date===today);
                    if(item.date===today){
                        const name=item.name;
                        console.log("time =", item.time);
                        reservations[item.time]=name;
                        renderSchedule();
                        console.log(reservations);
                    }
                    
                });
                renderSchedule();
            })
            
            .catch(console.error);
    }

    form.addEventListener('submit', function (e) {
        e.preventDefault();//submit의 특징인 새로고침 막기

        //받은 time, name 쓰기 쉬운 상태로 바꾸기
        const time = timeInput.value.substring(0,5);
        const name = nameInput.value;
        console.log(timeInput);

        if (!timeSlots.includes(time)) {
            alert("예약 가능한 시간이 아닙니다.");
            return;
        }

        if (reservations[time]) { 
            alert("이미 예약된 시간입니다.");
            return;
        }


        getItem();
        form.reset();


    });
    //넣는 구조랑 받았을 때 넣을수 있는 구조로 바꾸기

    function generateTimeSlots(start, end, intervalMinutes) {
        const slots = [];
        let [start_hour, start_minute] = start.split(":").map(Number);
        const [end_hour, end_minute] = end.split(":").map(Number);

        while (start_hour < end_hour || (start_hour === end_hour && start_minute < end_minute)) {
            slots.push(`${String(start_hour).padStart(2, '0')}:${String(start_minute).padStart(2, '0')}`);
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
        timeSlots.forEach(time => {
            const row = document.createElement('tr');
            const timeCell = document.createElement('td');
            const statusCell = document.createElement('td');

            timeCell.textContent = time;

            if (reservations[time]) {
                statusCell.textContent = reservations[time];
                row.classList.add('reserved');
            } else {
                statusCell.textContent = '(비어 있음)';
                row.classList.add('available');
            }

            row.appendChild(timeCell);
            row.appendChild(statusCell);
            scheduleTable.appendChild(row);
        });
    }

    function initializeUIAndListeners() {
        
        function toggleButtonGroups(groupToShow) {
            if (groupToShow === 'hour') {
                hourButtonsContainer.classList.remove('hidden');
                minuteButtonsContainer.classList.add('hidden');
            } else if (groupToShow === 'minute') {
                hourButtonsContainer.classList.add('hidden');
                minuteButtonsContainer.classList.remove('hidden');
            } else {
                hourButtonsContainer.classList.add('hidden');
                minuteButtonsContainer.classList.add('hidden');
            }
        }
        
        function updateTimeInput() {
            toggleMinuteButton.disabled = false;
            const displayHour = selectedHour ? selectedHour : '--';
            const displayMinute = selectedMinute ? selectedMinute : '--';
            const displayTimeString = `${displayHour}:${displayMinute}`;

            displayTime.textContent = `선택된 시간: ${displayTimeString}`;

            if (selectedHour && selectedMinute) {
                timeInput.value = `${selectedHour}:${selectedMinute}`; 
                timeInput.setCustomValidity(''); // 유효성 통과
            } else {
                timeInput.value = ''; 
                timeInput.setCustomValidity('예약 시간(시, 분)을 모두 선택해 주세요.'); // 유효성 실패
            }
        }

        toggleButtonGroups(null);
        timeInput.setCustomValidity('예약 시간(시, 분)을 모두 선택해 주세요.');
        updateTimeInput();
        
        toggleHourButton.addEventListener('click', function() {
            if (!hourButtonsContainer.classList.contains('hidden')) {
                toggleButtonGroups(null); 
            } else {
                toggleButtonGroups('hour'); 
            }
        });

        toggleMinuteButton.addEventListener('click', function() {
            if (!minuteButtonsContainer.classList.contains('hidden')) {
                toggleButtonGroups(null); 
            } else {
                toggleButtonGroups('minute');
            }
        });

        for (let h = 1; h <= 24; h++) {
            const button = document.createElement('button');
            button.type = 'button';
            button.textContent = `${h}시`;
            button.dataset.hour = h.toString().padStart(2, '0');
            button.classList.add('time-button', 'hour-button');
            
            button.addEventListener('click', function() {
                document.querySelectorAll('.hour-button.selected').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected'); 
                selectedHour = this.dataset.hour;
                updateTimeInput();
            });
            hourButtonsContainer.appendChild(button);
        }

        ['00', '30'].forEach(minute => {
            const button = document.createElement('button');
            button.type = 'button';
            button.textContent = `${minute}분`;
            button.dataset.minute = minute;
            button.classList.add('time-button', 'minute-button');
            
            button.addEventListener('click', function() {
                document.querySelectorAll('.minute-button.selected').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected'); 
                selectedMinute = this.dataset.minute;
                updateTimeInput();
            });
            minuteButtonsContainer.appendChild(button);
        });
        
        return { updateTimeInput };
    }

    // function setupReservationDataAndSubmit() {
        
    //     reservations = {};
    //     renderSchedule();

    //     // submit을 제출한 경우 (리다이렉트/페이지 제출을 위해 e.preventDefault()는 실패 시에만 사용)
    //     form.addEventListener('submit', function (e) {
            
    //         // 받은 time 쓰기 쉬운 상태로 바꾸기
    //         const time = timeInput.value.substring(0, 5);

    //         if (!form.checkValidity()) {
    //              // timeInput에 설정된 CustomValidity를 통해 시간 선택 여부 확인
    //              e.preventDefault(); 
    //              alert("예약 시간(시, 분)을 모두 선택/입력해 주세요.");
    //              return;
    //         }

    //         if (!timeSlots.includes(time)) {
    //             e.preventDefault();
    //             alert("예약 가능한 시간이 아닙니다.");
    //             return;
    //         }

    //         if (reservations[time]) { 
    //             e.preventDefault();
    //             alert("이미 예약된 시간입니다.");
    //             return;
    //         }
            
    //         // 폼 제출 허용 (e.preventDefault()를 호출하지 않음)
    //         // 서버는 이제 POST 요청을 받고, 예약 처리 후 리다이렉트를 수행해야 합니다.
    //     });
    // }
    
    initializeUIAndListeners();
});