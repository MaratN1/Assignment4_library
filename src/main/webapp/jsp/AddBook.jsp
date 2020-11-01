<%@include file="Header.jsp" %>


<div>
    <input type="text" id="name" placeholder="Enter book name"><br><br>
    <input type="text" id="authorFullName" placeholder="Enter book author full name"><br><br>
    <input type="number" id="countOfCopies" placeholder="Enter count of copies"><br><br>
    <button onclick="addClicked()">Add</button>
</div>

<script>
    function addClicked(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/addBook",
            async: true,
            data: {
                "name": $("#name").val(),
                "authorFullName":$("#authorFullName").val(),
                "countOfCopies": $("#countOfCopies").val(),

            },
            success: function(){
                window.location.href = "<%=request.getContextPath()%>/bookServlet";
            }
        });
    }
</script>

<%@include file="Footer.jsp" %>