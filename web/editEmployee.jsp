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
        <title>Employee Management</title>      
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/signin.css" rel="stylesheet">  
        <link rel="stylesheet" href="css/product.css"/>
        <link rel="stylesheet" href="css/menu_css.css"/>

        <!-- Custom styles for this template -->
    </head>
    <body>
            <main class="form-signin w-100 m-auto">


            <c:set var="emp" value="${sessionScope.Emp}"/>

            <c:set var="deptList" value="${DepartmentService.getAllDepartment()}"/>
            <c:set var="roleList" value="${RoleService.getAllRole()}"/>
            

            <form action="SaveEmployees" method="Post">

                <img class="mb-4" src="images/logo.PNG" alt="" width="300" height="100">
                <h1 class="h3 mb-3 fw-normal">Please edit employee data</h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="emplyee id" name="employeeId" value=${emp.getEmployeeId()} readonly>
                    <label for="floatingInput">Employee Id</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="first name" name="firstName" value=${emp.getFirstName()}>
                    <label for="floatingInput">First Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="last name" name="lastName" value=${emp.getLastName()}>
                    <label for="floatingInput">Last Name</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="address" name="address" value=${emp.getAddress()}>
                    <label for="floatingInput">Address</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="phone" name="phone" value=${emp.getPhone()}>
                    <label for="floatingInput">Phone</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="gender" name="gender" value=${emp.getGender()}>
                    <label for="floatingInput">Gender</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="age" name="age" value=${emp.getAge()}>
                    <label for="floatingInput">Age</label>
                </div>
                <div class=form-floating">


                    <select name="departmentId" class="form-select" id="departmentId">
                        <option value="0">Select a Department</option>
                        <c:set var="selectedDept" value="${emp.getDepartmentName()}"/>
                        <c:forEach var="dept" items="${deptList}">
                            <option value="${dept.getDepartmentId()}"  <c:if test="${dept.getDepartmentName() == selectedDept}"> selected </c:if>>${dept.getDepartmentName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-floating">
                    <select name="roleId" class="form-select" id="roleId">
                        <option value="0">Select a Role</option>
                        <c:set var="selectedRole" value="${emp.getRoleName()}"/>

                        <c:forEach var="role" items="${roleList}">

                            <option value="${role.getRoleId()}"
                                    <c:if test="${role.getRoleName ()== selectedRole}">selected  </c:if>
                                    >${role.getRoleName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="basicSalary" name="basicSalary" value=${emp.getBasicSalary()}>
                    <label for="floatingInput">Basic Salary</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="carAllowance" name="carAllowance" value=${emp.getCarAllowance()}>
                    <label for="floatingInput">Car Allowance</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Save</button>

            </form>
        </main>
    </body>
</html>

