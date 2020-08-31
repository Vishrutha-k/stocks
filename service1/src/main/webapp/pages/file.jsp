<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import Excel sheet here..</title>
</head>
<body>
	<h2>Please upload the Excel file here in .xlsx or .xlx format</h2>
	<form action="importExcel" method="post" enctype="multipart/form-data">
		<input type="file" name="file" accept=".xls,.xlsx"><br>
		<input type="submit" value="Upload file">
	</form>
</body>
</html>