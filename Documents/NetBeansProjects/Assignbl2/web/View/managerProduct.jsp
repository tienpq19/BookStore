<%-- 
    Document   : managerProduct
    Created on : Mar 15, 2024, 10:04:51 PM
    Author     : TienP
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap Simple Data Table</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>        

        <link rel="stylesheet" href="css/modal.css">

    </head>

    <jsp:include page='header.jsp'/>



    <div class="container-cate">
        <div class='row'>
            <div class="category-left">
                <ul>             
                    <li><a href="ManagerProduct" class="category-left-li">Product Management</a></li>
                    <li><a href="recordTrans" class="category-left-li">Transaction Records</a></li>
                    <li><a href="chart" class="category-left-li">Data</a></li>
                </ul>
            </div>
            <div class='category-right' >
                <div class="container-xl">
                    <div class="table-responsive">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-8"><h2>Book <b>Manager</b></h2></div>
                                    <div class="col-sm-4">
                                        <div class="search-container">
                                            <form action="SearchManager" method="GET">
                                                <input type="text" placeholder="Search..." name="keyword">
                                                <select name="category">
                                                    <option value="0">All Categories</option>
                                                    <c:forEach items="${requestScope.bookCate}" var="cate">
                                                        <option value="${cate.getCateID()}">${cate.getNameCate()}</option>
                                                    </c:forEach>
                                                </select>
                                                <button type="submit">Search</button>
                                            </form>
                                        </div>
                                        <div>
                                            <a href="#insertBooks" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Book</span></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>BookID</th>
                                        <th>Book Name <i class="fa fa-sort"></i></th>
                                        <th>Author</th>
                                        <th>Image <i class="fa fa-sort"></i></th>
                                        <th>Price</th>
                                        <th>Category <i class="fa fa-sort"></i></th>
                                        <th>Quantity</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.books}" var="book">
                                        <tr>
                                            <td>${book.getId()}</td>
                                            <td>${book.getName()}</td>
                                            <td>${book.getAuthor()}</td>
                                            <td><img src="${book.getImage()}" id="imageElementId" alt="Book Image" /></td>

                                            <td>${book.getPrice()}</td>
                                            <td>${book.getCategory().getNameCate()}</td>
                                            <td>${book.getQuantity()}</td>
                                            <td>
                                                <a href="#" class="edit" data-toggle="modal" data-target="#editBooksModal" onclick="handleEditClick(${book.getId()})"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>                                               
                                                <a href="#" class="delete" data-toggle="modal" data-target="#deleteBooksModal" onclick="handleDeleteClick(${book.getId()})"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                            </td>

                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                        </div>
                    </div>  
                </div>
            </div>
        </div>
    </div>



    <!-- Delete Modal HTML -->
    <div id="deleteBooksModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="deleteBookForm" action="delete" method="post">
                    <input type="hidden" id="deleteBookId" name="bookId" value="deleteBookId">
                    <div class="modal-header">						
                        <h4 class="modal-title">Delete Book</h4>
                    </div>
                    <div class="modal-body">					
                        <p>Are you sure you want to delete this record?</p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" name="delete" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>  
    <!-- Edit Modal HTML -->
    <div id="insertBooks" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="insert" method="post" enctype="multipart/form-data">
                    <div class="modal-header">						
                        <h4 class="modal-title">Edit Employee</h4>					
                    </div>
                    <div class="modal-body">		                       
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Image</label>
                            <input type="file" name="image" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="text" name="price" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Author</label>
                            <input type="text" name="author" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Publisher</label>
                            <input type="text" name="publisher" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Page</label>
                            <input type="text" name="page" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Language</label>
                            <input type="text" name="language" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Size</label>
                            <input type="text" name="size" class="form-control" required>
                        </div>	
                        <div class="form-group">
                            <label>Description</label>
                            <textarea type="text" name="des" class="form-control" required></textarea>
                        </div>	
                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="text" name="Quantity" class="form-control" required>
                        </div>	
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category">

                                <c:forEach items="${requestScope.bookCate}" var="cate">
                                    <option value="${cate.getCateID()}">${cate.getNameCate()}</option>
                                </c:forEach>
                            </select>
                        </div>	

                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->
    <div id="editBooksModal" class="modal fade">  
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="edit" method="post" enctype="multipart/form-data">
                    <div class="modal-header">						
                        <h4 class="modal-title">Edit Employee</h4>					
                    </div>
                    <div class="modal-body">		
                        <input type="hidden" id="editBookId" name="bookId" value="editBookId">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Image</label>
                            <input type="file" name="image" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="text" name="price" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Author</label>
                            <input type="text" name="author" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Publisher</label>
                            <input type="text" name="publisher" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Page</label>
                            <input type="text" name="page" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Language</label>
                            <input type="text" name="language" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Size</label>
                            <input type="text" name="size" class="form-control" required>
                        </div>	
                        <div class="form-group">
                            <label>Description</label>
                            <textarea type="text" name="des" class="form-control" required></textarea>
                        </div>	
                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="text" name="Quantity" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category">

                                <c:forEach items="${requestScope.bookCate}" var="cate">
                                    <option value="${cate.getCateID()}">${cate.getNameCate()}</option>
                                </c:forEach>
                            </select>
                        </div>	

                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->

    <footer>
        <jsp:include page='footer.jsp'/>
    </footer>
    <script>
          
        // Function to handle delete button click
        function handleDeleteClick(bookId) {
            // Set the book ID in a hidden input field in the delete modal form
            document.getElementById("deleteBookId").value = bookId;
        }
        function handleEditClick(bookId) {
            // Set the book ID in a hidden input field in the delete modal form
            document.getElementById("editBookId").value = bookId;
        }
    </script>
</html>
