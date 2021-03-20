<div class="container">

	<div class="row">

		<!-- This space is used to display sidebar navigation -->
		<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>

			<!-- This means that when this page gets loaded it will include sidebar.jsp file
		and in that file we have ${categories} and the value of this component is coming
		from the PageController class (because of mapping into "categories" object-->

		</div>

		<!-- This space is used to display (all) actual products -->
		<div class="col-md-9">

			<!-- Adding breadcrumb component -->

			<div class="row">

				<div class="col-lg-12">

					<c:if test="${userClickAllProducts == true}">
					
						<script>
						
							window.categoryId = '';
						</script>
					
						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>

						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts == true}">
					
						<script>
						
							window.categoryId = '${category.id}';
						</script>
					
						<ol class="breadcrumb">

							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>

						</ol>
					</c:if>

				</div>



			</div>


			<div class="row">
			
				<div class="col-xs-12">
				
				
					<table id="productListTable" class="table table-striped table-borderd">
					
					
						<thead>
							
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Quantity Available</th>
								<th></th>
							
							</tr>
						
						</thead>
					
						
						<tfoot>
							
							<tr>
								<th></th>
								<th>Name</th>
								<th>Brand</th>
								<th>Price</th>
								<th>Quantity Available</th>
								<th></th>
							
							</tr>
						
						</tfoot>
						
					</table>
				
				
				</div>
			
			
			
			
			</div>


		</div>


	</div>









</div>