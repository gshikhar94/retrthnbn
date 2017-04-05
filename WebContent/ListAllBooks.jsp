<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Employee Details</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	
	$("button").click(function(event){
		
		var url="rest/library/books";
		$("#results").removeClass("hidden");
		
		  
	     $.getJSON(url,function(jd){
			 
	    	 var row;
	    	 $.each(jd, function(index) {
	    		 row = $('<tr>');
	    		 row.append($('<td>').html(jd[index].id));
	    		 row.append($('<td>').html(jd[index].title));
	    		 row.append($('<td>').html(jd[index].status));
	    		 if(jd[index].status != 'Reserved'){	    			 
	    			 console.log(jd[index].status);
	    		 	row.append($('<td>').html('<button><a href="http://localhost:8080/BookReservationSystem/ReserveBook.jsp?id='+jd[index].id+'&title='+jd[index].title+'">ReserveBook</a></button>'));
	    		 }
	    		 else{
	    			 row.append($('<td>').html('<button disabled>Reserved Book</button>'));
	    		 }
	    		 row.append('</tr>')
	    		 $('#results').append(row);
	    		  	 
	         });
	    	 	
		});
		});
	});

</script>




</head>

<style>
.hidden {
    visibility: hidden;
    
}
</style>

<body>


<h1 style=" text-align: center;">Get Details Of All The Books In Library</h1>


<br>
<h2 >Click To Get Details Of All Books</h2><br>
<button>Book Details</button><br><br>

     <table class="hidden" border=1 id="results" style="width:100%">
     <tr>
          <th>Book id</th>
          <th>Book Title</th>
          <th>Status</th>
          <th>Reserve a Book</th>
        
     </tr>
     </table>                       
                                
   


</body>
</html>