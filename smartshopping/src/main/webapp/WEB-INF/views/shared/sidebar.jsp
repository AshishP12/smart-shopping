<p class="lead">Apni Dukan</p>




<div class="list-group">

	<c:forEach items="${categories}" var="category">     <!-- Each categories is refered using the category variable. It is read as for each categories in category -->
		<a href="${contextRoot}/show/category/${category.id}/products" class="list-group-item" id="a_${category.name}">${category.name}</a>
		 <!-- here we are accessing the property, internally getters are called -->
	</c:forEach>
</div>