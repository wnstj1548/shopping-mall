<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title>nhn아카데미 shopping mall 회원가입</title>

</head>
<body>
<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/registerAction.do">

            <h1 class="h3 mb-3 fw-normal">회원 가입</h1>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name" placeholder="성명" required>
                <label for="user_name">성명</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디" required>
                <label for="user_id">회원아이디</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" placeholder="비밀번호" required>
                <label for="user_password">비밀번호</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password2" class="form-control" id="user_password2" placeholder="비밀번호 확인" required>
                <label for="user_password">비밀번호 확인</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생년월일(20000614)" required>
                <label for="user_birth">생년월일(20000614)</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Register</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>
        </form>
    </div>
</div>
</body>
</html>
