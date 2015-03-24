<%@ page import="daveapp.Dave" %>



<div class="fieldcontain ${hasErrors(bean: daveInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="dave.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${daveInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: daveInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="dave.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="status" type="number" value="${daveInstance.status}" required=""/>

</div>

