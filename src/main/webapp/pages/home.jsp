<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-4">
		<h3 class="mb-4">Report Application</h3>
		<form:form id="searchForm" action="search" modelAttribute="search" method="POST">
			<table class="table table-bordered">
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName" class="form-select">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}" />
						</form:select></td>
					<td>Plan Status:</td>
					<td><form:select path="planStatus" class="form-select">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>
					<td>Gender:</td>
					<td><form:select path="gender" class="form-select">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Start Date:</td>
					<td><form:input type="date" path="planStartDate"
							class="form-control" /></td>
					<td>End Date:</td>
					<td><form:input type="date" path="planEndDate"
							class="form-control" /></td>
				</tr>
				<tr>
					<td><a href="/" class="btn btn-secondary">Reset</a></td>
					<td colspan="4"><input type="submit" value="Search"
						class="btn btn-primary" /></td>
				</tr>
			</table>
		</form:form>

		<hr class="my-4" />

		<table class="table table-striped table-hover">
			<thead class="table-dark">
				<tr>
					<th>S.No</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizen_name}</td>
						<td>${plan.gender}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benefitAmt}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty plans}">
					<tr>
						<td colspan="8" class="text-center">No Records Found</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<hr class="my-4" />

		<div class="mb-3">
			Export:
			<button type="button" onclick="submitFormForExcel()"
				class="btn btn-success btn-sm">Excel</button>
			<button type="button" onclick="submitFormForPdf()"
				class="btn btn-success btn-sm">Pdf</button>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
	<script>
	function submitFormForExcel() {
    var form = document.getElementById('searchForm');
    var originalAction = form.action;
    form.action = 'excel';
    form.submit();
    form.action = originalAction;
}
	function submitFormForPdf() {
	    var form = document.getElementById('searchForm');
	    var originalAction = form.action;
	    form.action = 'pdf';
	    form.submit();
	    form.action = originalAction;
	}
</script>
</body>
</html>