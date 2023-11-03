
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Toy Kingdom</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/grid.css">
        <link rel="stylesheet" href="./assets/css/main.css">
        <link rel="stylesheet" href="./assets/css/responsive.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="script.js"></script>
 
    </head>
    <body>
        <div class="app">
            <!-- Header --> 
            <jsp:include page="header.jsp"/>

            <div class="app__container">
                <div class="grid wide">
                    <div class="row sm-gutter">
                        <div class="col l-2 m-0 c-0">
                            <!-- Category -->       
                            <jsp:include page="category.jsp"/>
                        </div>

                        <div class="col l-10 m-12 c-12">
                            <!-- Home filter -->                    
                            <jsp:include page="home-product.jsp"/>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->       
            <jsp:include page="footer.jsp"/>
    </body>
</html>