<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        h1{
            color: rgb(40, 103, 128);
            font-size: 80px;
            font-size: 2rem 
        }
        body{
            text-align: left;
            padding: 50px;
            overflow-y: scroll;
        }
        input{
            padding: 4px 6px;
            margin: 10px 0;
        }
        label{
            display: inline-block;
            width: 80px;
            text-align: center;
            font-weight: bold;
            margin-right: 10px;
            padding: 3px;
            background-color: rgb(180, 217, 209);
            border: 1px solid #000;
        }
        .page-header{
            border-bottom: 4px solid #333;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .reserved { 
            background-color: #ffe0e0;
            font-size: 2rem;
        }
        .available { 
            background-color: #f0f0f0; 
            color: #000000ff;
            font-size: 2rem;
        }
        #container {
            margin-left: auto;
            margin-right: auto;
            max-width: 600px;
            max-height: 400px;     
            overflow-y: auto;  
            border: 1px solid #ccc;
            margin-top: 10px;
            text-align: center;
        }
        footer{
            border-top: 4px solid #333;
            padding: 10px;
            margin: 20px;
        }
        #scheduleTable {
            width: 100%;
            text-align: center;
        }
    </style>
</head>
<body>
    <header class="page-header">
        <h1 >이름</h1>
    </header>
    <main>
        <div id="container">
            <h2>예약 현황표</h2>
            <table id="scheduleTable">
                <thead>
                    <tr><th>시간</th><th>상태</th></tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </main>
    <footer>
        <h1>예약하기</h1>
            <form id="reservationForm">
                <label for="time">예약 시간:</label>
                <input type="time" id="time" step="1800" required>
                <label for="name">이름:</label>
                <input type="text" id="name" required>
                <button type="submit">예약</button>
            </form>
            <script src="app.js"></script>
    </footer>
    
</body>
</html>
