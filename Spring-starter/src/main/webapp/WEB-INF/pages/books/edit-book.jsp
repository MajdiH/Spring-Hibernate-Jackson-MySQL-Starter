<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Edit Book</title>
<link href="../../resources/css/main.css" rel="stylesheet"
	type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('#editBookForm')
								.submit(
										function(event) {

											var title = $('#title').val();
											var description = $('#description')
													.val();
											var price = $('#price').val();
											var author = $('#author').val();
											var json = {
												"title" : title,
												"description" : description,
												"price" : price,
												"author" : author
											};

											$
													.ajax({
														url : $("#editBookForm")
																.attr("action"),
														data : JSON
																.stringify(json),
														type : "PUT",

														beforeSend : function(
																xhr) {
															xhr
																	.setRequestHeader(
																			"Accept",
																			"application/json");
															xhr
																	.setRequestHeader(
																			"Content-Type",
																			"application/json");
															$(".error")
																	.remove();
														},
														success : function(book) {
															var respContent = "";

															respContent += "<span class='success'>Book was edited: [";
															respContent += book.title
																	+ " : ";
															respContent += book.description
																	+ " : ";
															respContent += book.price
																	+ " : ";
															respContent += book.author
																	+ "]</span>";

															$(
																	"#sBookFromResponse")
																	.html(
																			respContent);
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															var respBody = $
																	.parseJSON(jqXHR.responseText);
															var respContent = "";

															respContent += "<span class='error-main'>";
															respContent += respBody.message;
															respContent += "</span>";

															$(
																	"#sBookFromResponse")
																	.html(
																			respContent);

															$
																	.each(
																			respBody.fieldErrors,
																			function(
																					index,
																					errEntity) {
																				var tdEl = $("."
																						+ errEntity.fieldName
																						+ "-info");
																				tdEl
																						.html("<span class=\"error\">"
																								+ errEntity.fieldError
																								+ "</span>");
																			});
														}
													});

											event.preventDefault();
										});

					});
</script>

</head>
<body>
	<div id="container">
		<h1>Edit Book</h1>
		<div id="sBookFromResponse">
			<p>Here you can edit Book info:</p>
			<div id="sBookFromResponse"></div>
		</div>
		<form:form id="editBookForm" method="PUT" commandName="sBook"
			action="${pageContext.request.contextPath}/books/edit/${sBook.id}.json">
			<table>
				<tbody>
					<tr>
						<td>Title:</td>
						<td><form:input path="title" /></td>
						<td class="title-info"></td>
					</tr>
					<tr>
						<td>description:</td>
						<td><form:textarea path="description" /></td>
						<td class="description-info"></td>
					</tr>
					<tr>
						<td>Price:</td>
						<td><form:input path="price" /></td>
						<td class="price-info"></td>
					</tr>
					<tr>
						<td>Author:</td>
						<td><form:input path="author" /></td>
						<td class="author-info"></td>
					</tr>
					<tr>
						<td><input type="submit" value="Edit" /></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<a href="${pageContext.request.contextPath}/index.html">Home page</a>
	</div>
</body>
</html>