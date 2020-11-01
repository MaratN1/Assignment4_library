<%@include file="Header.jsp" %>
<div id="readersBlock">

    </div>
    <script>
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "<%=request.getContextPath() %>/readers",
            success: function(result){
               console.log(result)
            }
        });
    });
    </script>

<%@include file="Footer.jsp" %>

