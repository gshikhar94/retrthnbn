<!doctype html>
<html>
<body>
<h1>JAX-RS Reserve Book</h1>

<%
String id  = request.getParameter("id");
%>
<form action="rest/library/reserveBook" method="post" >

<p> BookId :<label contenteditable="false">id</label></p>
<p>Book Title:<label contenteditable="false">title</label>
<p>Visitor ID: <input type="text" name="visitorId" /></p>
<p>Reservation Date : <input type="date" name="reservationDate" /></p>
<p>Expiration Date : <input type="date" name="expirationDate" /></p>

<input type="submit" value="Reserve Book" />
</form>

</body>
</html>