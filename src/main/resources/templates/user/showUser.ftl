<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title} </title>
        
       	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="/js/showUser.js" ></script>
    </head>
    
    <body>
        
         <table id="userTable" class="table table-bordered table-striped">
		  <thead>
			  <tr>
				<th>id</th>
				<th>name</th>
				<th>desc</th>
				<th>state</th>
				<th>createDate</th>
				<th>lastLoginDate</th>
				<th>type</th>
			  </tr>
		  </thead>
		  
		  <tbody>
		  	 <#list users as user >
 	 		  	<tr>
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>${user._desc }</td>
					<td>${user.state }</td>
					<td>${user.createDate }</td>
					<td>${user.lastLoginDate }</td>
					<td>${user.type }</td>
				</tr>
		  	</#list>
		  </tbody>
		  
		</table>
        
    </body>
    
</html>