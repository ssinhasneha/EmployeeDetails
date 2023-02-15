<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<option value="">Select a district <span  style="color:red"> *</span></option>


<c:forEach items="${DistrictList}" var="district">
    <option value="${district.getDistrictCode()}"<c:if test="${district.getDistrictCode()==User.getDistrictCode()}">
            selected 
        </c:if>>


        ${district.getDistrictName()} 
          </option>
        </c:forEach>
