<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.104.2">
        <title>Sign up - Employee Management</title>



        <meta name="theme-color" content="#712cf9">


        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/menu_css.css" rel="stylesheet">

    </head>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script>
                        function fetchContent(selectedId, targetId) {
                            //alert("'#"+selectedId.name+"'");
                            $.ajax({
                                url: 'PreSignUp',
                                data: {
                                    [selectedId]: $("#" + selectedId).val()
                                },
                                success: function (responseText) {
                                  //  alert(responseText);
                                    $("#" + targetId).html(responseText);
                                }
                            });
                        }
    </script>

    <body class="text-center">


        <main class="form-signin w-100 m-auto">

            <c:if test="${SignUpFailureMsg!=null}">
                <div class="alert alert-danger msg_style" role="alert">
                    <c:out value="${SignUpFailureMsg}"/>
                </div>
            </c:if>
            <form action="PreSignUp" id="signupForm" method="Post">
                <img class="mb-4" src="images/flower-logo.jpg" alt="" width="200" height="200">
                <h1 class="h3 mb-3 fw-normal">Please Sign In</h1>

                <div class="form-floating">
                    <input type="emailAddress" class="form-control" id="floatingInput" placeholder="name@example.com" name="emailAddress"  value="${User.getEmailAddress()}" required>
                    <label for="floatingInput">Email address<span  style="color:red"> *</span></label>
                </div>

                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" value="${User.getPassword()}" required>
                    <label for="floatingPassword">Password<span  style="color:red"> *</span></label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="firstName" placeholder="first name" name="firstName" value="${User.getFirstName()}" required>
                    <label for="firstName">First Name<span  style="color:red"> *</span></label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="lastName" placeholder="last name" name="lastName" value="${User.getLastName()}" required>
                    <label for="firstName">Last Name<span  style="color:red"> *</span></label>
                </div>



                <div class="form-floating">
<!-- fetchcontent(source,destination)-->
                    <select name="countryCode" class="form-select" id="countryCode" onchange="fetchContent('countryCode', 'stateCode')" required>
                        <option value="">Select a Country <span  style="color:red"> *</span></option>


                        <c:forEach items="${CountryList}" var="country">
                            <option value="${country.getCountryCode()}"<c:if test="${country.getCountryCode()==User.getCountryCode()}">
                                    selected 
                                </c:if>>


                                ${country.getCountryName()} 
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-floating">

                    <select name="stateCode" class="form-select" id="stateCode"  required  onchange="fetchContent('stateCode', 'districtCode')">
                        <option value="">Select a state <span  style="color:red"> *</span></option>     
                    </select>
                </div>

                <div class="form-floating">

                    <select name="districtCode" class="form-select" id="districtCode" required >
                        <option value="">Select a district <span  style="color:red"> *</span></option>

                   
                    </select>
                </div>

                <div class="checkbox mb-3">

                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign Up</button>
                <a href="landingPage.jsp">
                    <button type="button" class="w-100 btn btn-lg btn-warning">Cancel</button>
                </a>
                <p class="mt-5 mb-3 text-muted">&copy; 2017?2022</p>
            </form>
        </main>



    </body>
</html>
