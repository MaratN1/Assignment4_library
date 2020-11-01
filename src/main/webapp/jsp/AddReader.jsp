<%@include file="Header.jsp" %>

<div>
    <input type="text" id="fullname" placeholder="Enter full name"> <br><br>
    <input type="text" id="group" placeholder="Enter group"> <br><br>
    <button onclick="addClicked()">Add</button>
</div>

<script>
    function addClicked(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/addReader",
            async: true,
            data: {
                "fullname": $("#fullname").val(),
                "group":$("#group").val(),

            },
            success: function(){
                window.location.href = "<%=request.getContextPath()%>/readers";
            }
        });
    }

</script>
<%@include file="Footer.jsp" %>