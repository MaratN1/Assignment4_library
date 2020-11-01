<%@include file="Header.jsp" %>

<div id="booksBlock">

</div>

<script>
    $(document). ready(function (){
        $.ajax({
            type: "GET",
            url: "<%=request.getContextPath() %>/books",
            success: function(result){
                for(let i = 0; i < result.length; i++){
                    $("#booksBlock").append("<h3>" + result[i]['name'] + "<a href='<%=request.getContextPath() %>/changeBook?id=" + result[i]['id'] + "'>Change</a>" + "</h3>");                }
            }
        });

    });
</script>

<%@include file="Footer.jsp" %>