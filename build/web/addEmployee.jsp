<%@page import="com.exavalu.models.Role"%>
<%@page import="com.exavalu.services.RoleService"%>
<%@page import="com.exavalu.services.DepartmentService"%>
<%@page import="com.exavalu.models.Department"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.84.0">
        <title>Add Employee</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sign-in/">
        <link href="menu.jsp">


        <!-- Bootstrap core CSS -->


        <!-- Favicons -->
        <link rel="apple-touch-icon" href="/docs/5.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
        <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
        <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
        <link rel="manifest" href="/docs/5.0/assets/img/favicons/manifest.json">
        <link rel="mask-icon" href="/docs/5.0/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
        <link rel="icon" href="/docs/5.0/assets/img/favicons/favicon.ico">
        <meta name="theme-color" content="#7952b3">


        <!--//link -->
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/menu_css.css" rel="stylesheet">
        <link href="css/signin.css" rel="stylesheet">
        <link href="css/product.css" rel="stylesheet">

    </head>
    <body class="text-center">

 
        <main class="form-signin">

            <c:set var="emp" value="${requestScope.Emp}"/>
            <form action="AddEmployee" method="Post">

              
               

                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="first Name" name="firstName" required>
                    <label for="floatingInput">First Name<span  style="color:red"> *</span></label>
                </div>
                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="last Name" name="lastName" required>
                    <label for="floatingInput">Last Name<span  style="color:red"> *</span></label>
                </div>
                <div class="form-floating">

                    <input type="input" pattern=".{10}" class="form-control" id="floatingInput" placeholder="phone" name="phone" oninput="check(this)" title="enter 10 digit number" required>
                    <label for="floatingInput">Phone<span  style="color:red"> *</span></label>

                </div>


                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="address" name="address" required>
                    <label for="floatingInput">Address<span  style="color:red"> *</span></label>
                </div>
                <div class="form-floating">
                    <select name="gender" class="form-select" id="gender" required>
                        <option value="">Gender<span  style="color:red"> *</span></option>
                        <option value="Male">Male</option>
                        <option value="Female">Female </option>
                    </select>
                </div>

                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="age" name="age" required>
                    <label for="floatingInput">Age<span  style="color:red"> *</span></label>
                </div>


                <c:set var="deptList" value="${DepartmentService.getAllDepartment()}"/>
                <c:set var="roleList" value="${RoleService.getAllRole()}"/>


                <div class="form-floating">

                    <select name="departmentId" class="form-select" id="departmentId" required>
                        <option value="">Department<span  style="color:red"> *</span></option>


                        <c:forEach items="${deptList}" var="dept">
                            <option value=${dept.getDepartmentId()}> ${dept.getDepartmentName()} </option>
                        </c:forEach>

                    </select>
                </div>
                <div class="form-floating">

                    <select name="roleId" class="form-select" id="roleId" required>
                        <option value="">Role<span  style="color:red"> *</span> </option>

                        <c:forEach items="${roleList}" var="role">
                            <option value=${role.getRoleId()}> ${role.getRoleName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="basic salary " name="basicSalary" required>
                    <label for="floatingInput">Basic Salary <span  style="color:red"> *</span></label>
                </div>
                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" placeholder="car Allowance" name="carAllowance" value="0.0">
                    <label for="floatingInput">car Allowance </label>
                </div>
                <div id="btm">
                    <button class="w-100 btn btn-lg btn-primary" type="submit" >Add Employee</button>
                </div>

                <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
            </form>
        </main>
    </body>
</html>