<%-- 
    Document   : background
    Created on : 14-Feb-2023, 2:57:55 pm
    Author     : sinha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/menu_css.css" rel="stylesheet">
        <link href="css/signin.css" rel="stylesheet">
    </head>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script>
                        function fetchContent(selectedAction, targetId) {
                           
                            $.ajax({
                                url: selectedAction,
                                data: {
                                    [selectedAction]: $("#" + selectedAction).val()
                                },
                                success: function (responseText) {
                                  //  alert(responseText);
                                    $("#" + targetId).html(responseText);
                                }
                            });
                        }
    </script>
    <body class="text-center">
         <jsp:include page="menu.jsp"></jsp:include>
         <div id="content">
              <img src="images/welcome.png"></img>
         </div>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
    </body>
</html>
