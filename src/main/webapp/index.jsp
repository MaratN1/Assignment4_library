<html>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>

<div id="registrationBlock">
    <input type="text" id="loginInputRegistration" placeholder="Enter login"><br><br>
    <input type="text" id="fullNameInputRegistration" placeholder="Enter full name"><br><br>
    <input type="password" id="passwordInputRegistration" placeholder="Enter password"><br><br>
    <button onclick="registerClicked()">Register</button>
</div>

<div id="loginBlock">
    <input type="text" id="loginInputLogin" placeholder="Enter login"><br><br>
    <input type="password" id="passwordInputLogin" placeholder="Enter password"><br><br>
    <button onclick="loginClicked()">Log in</button>

</div>

<h1 id="messageText>"></h1>

<script>
    function registerClicked(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/registration",
            async: true,
            data: {
                "loginInput": $("#loginInputRegistration").val(),
                "fullNameInput":$("#fullNameInputRegistration").val(),
                "passwordInput":$("#passwordInputRegistration").val() ,
            },
            success: function(){
              $ ("#messageText").append("Registered successfully!")
            }
        });
    }

    function loginClicked(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/login",
            async: true,
            data: {
                "login": $("#loginInputLogin").val(),
                "password":$("#passwordInputLogin").val(),
            },
            success: function(){
                window.location.href = "<%=request.getContextPath()%>/readers";
            }
        });
    }


</script>


</body>
</html>
