<%@include file="Header.jsp" %>

<div id="oldInformation">
    <p>Name: </p>
    <p id="name"></p>

    <p>Author</p>
    <p id="authorFullName"></p>

    <p>Count of copies</p>
    <p id="countOfCopies"></p>
</div>

<br><br>

<div>

    <p>New name:</p>
    <input type="text" id="newName"> <br><br>

    <p>New author name:</p>
    <input type="text" id="newAuthorFullName"> <br><br>

    <p>New count of copies:</p>
    <input type="text" id="newCountOfCopies"> <br><br>

    <button onclick="changeClicked()">Change</button>
</div>


<script>
    $(document). ready(function (){
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath() %>/bookInfo",
            success: function(result){
                console.log(result);

                    $("#name").append(result['name']);
                    $("#authorFullName").append(result['authorFullName']);
                    $("#countOfCopies").append(result['countOfCopies']);

            }
        });
    });

    function changeClicked(){
            $.ajax({
                type: "POST",
                url: "<%=request.getContextPath() %>/changeBook",
                data: {
                    "newName": $("#newName").val(),
                    "newAuthorFullName": $("#newAuthorFullName").val(),
                    "newCountOfCopies": $("#newCountOfCopies").val(),
                },
                success: function (result) {
                    window.location.href = "<%=request.getContextPath() %>/bookServlet"

                }
            });
        }
</script>



<%@include file="Footer.jsp" %>