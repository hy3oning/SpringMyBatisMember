<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
.container {
	width: 500px;
	margin: 20px auto;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], textarea {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

textarea {
	height: 200px;
	resize: vertical;
}

.btn-area {
	text-align: right;
}

button {
	padding: 10px 20px;
	cursor: pointer;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
}
</style>
</head>
<body>

	<div class="container">
		<h2>회원가입</h2>
		<form action="/member/insert" method="post">
			<div class="form-group">
				<label for="id">아이디</label> <input type="text" id="id" name="id"
					placeholder="아이디를 입력하세요" required>
			</div>

			<div class="form-group">
				<label for="pw">패스워드</label> <input type="password" id="pw"
					name="pw" placeholder="패스워드" required>
			</div>

			<div class="form-group">
				<label for="name">이름</label> <input type="text" id="name"
					name="name" placeholder="이름을 입력하세요" required>
			</div>
			<div class="form-group">
				<label for="coin">등록코인</label> <input type="number" id="coin"
					name="coin" placeholder="숫자만 입력하세요">
			</div>

			<div class="btn-area">
				<button type="submit">등록하기</button>
				<button type="reset" style="background-color: #6c757d;">취소</button>
			</div>
		</form>
	</div>

</body>
</html>