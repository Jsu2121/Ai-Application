<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
    <!-- in page style sheet -->
    <style>
        body {
            overflow-x: hidden;
            background-color: lightgray;

        }

        header {
            font-size: 50px;
            border-bottom: black 2px solid;
            color: black;
        }
        /* clickable title */
        .title, .title a{
            font-size: 40px;
            list-style: none;
            color: black;
            text-decoration: none;
        }
        .title:hover{
            transform: scale(1.05);
        }
        /* profile icon */
        .profile {
            font-size: 50px;
            text-align: right;
            justify-self: right;
        }

        .menu {
            font-size: 50px;
            text-align: center;
            justify-self: center;
            width: 228px;
        }
        /* History and Favorites Tabs Button */
        .menu-btns{
            text-decoration: none;
            justify-self: center;
            justify-content: center;
            text-align: center;
        }

        /* history cards */
        .history-item {
            display: flex;
            flex-direction: column;
            list-style-type: none;
            background: white;
            border-radius: 10px;
            padding: 10px;
            margin-top: 10px;
        }

        /* Results Blocks */
        .result-card{
            background: white;
            border-radius: 10px;
        }

        .history-btn{
            text-decoration: none;
            color: black;
        }
        /* orange favorite button */
        .favorite-button {
            display: flex;
            float: right;
            border: 2px solid orange;
        }
        /* inventory button */
        .inventory {
            margin-top: auto;
            padding: 20px;
            justify-self: center;
            text-align: center;

        }
        /* sidebar button */
        .sidebar {
            width: 250px;
            transition: all 0.3s;
            background-color: lightgray;
            color: black;
            border-right: black 2px solid;
        }
        .sidebar.collapsed {
            margin-left: -250px;
        }
        .content {
            transition: margin-left 0.3s;
            margin-left: 250px;
        }
        .content.expanded {
            margin-left: 0;
        }
        .sidebar .nav-link {
            color: black;
        }
        .sidebar .nav-link:hover {
            background-color: lightgray;
        }

        header.navbar{
            background-color: lightgray;
            color: black;
        }

        /* Hide the delete icon for tables by default */
        .delete-icon {
            opacity: 0;
        }

        /* Show the delete icon when the parent row is hovered */
        tr:hover .delete-icon {
            opacity: 1;
        }
        th, td {
            font-size: large;
        }


    </style>
</head>

<body>

<!-- Header -->
<header class="navbar sticky-top d-none d-sm-flex bg-success">
    <div class="container-fluid">
        <!-- Hamburger button -->
        <button class="btn btn-outline-dark me-2 " id="toggleSidebar">&#9776;</button>
        <div class="title">
            <img src="images/logo.png" height="40">
            <a href="homePage.html">Recipe Maker</a>
        </div>
        <div class="profile">
            <a href="#"><i class="bi bi-person-circle text-dark" ></i></a>
        </div>
    </div>
</header>

<!-- Sidebar -->
<div id="sidebar" class="position-absolute h-100 sidebar p-3">
    <div class="menu">
        <h1 class="mb-0">Menu</h1>
    </div>
    <!--    change active over to favorites in favorites.html-->
    <div class="menu-btns">
        <div class="btn-group" role="group" aria-label="Navigation button group">
            <a href="resultsPage.html" class="btn btn-outline-secondary btn-lg active">History</a>
            <a href="favoritesPage.html" class="btn btn-outline-secondary btn-lg">Favorites</a>
        </div>
    </div>


    <h6 class="mt-3">Recent Recipes</h6>
    <ul class="history-item">
        <li>
            <a class="nav-link" href="#"><p>Recipe Name 1:<br>Date: xx-xx-xxxx<br>Main Ingredients</p></a>
        </li>
        <li>
            <a class="nav-link" href="#"><p>Recipe Name 2:<br>Date: xx-xx-xxxx<br>Main Ingredients</p></a>
        </li>
        <li>
            <a class="nav-link" href="#"><p>Recipe Name 3:<br>Date: xx-xx-xxxx<br>Main Ingredients</p></a>
        </li>
        <li>
            <a class="nav-link" href="#"><p>Recipe Name 4:<br>Date: xx-xx-xxxx<br>Main Ingredients</p></a>
        </li>
    </ul>
    <div class="inventory">
        <a class="btn btn-success btn-lg" href="inventoryPage.html" role="button">Inventory</a>
    </div>
</div>

<main id="content" class="content p-4 d-flex flex-column justify content-center align-items-center">
    <h1>Ingredients</h1>
    <form id="inventoryForm" action="inventory" method="post">
        <table id="ingredientsTable" class="table table-hover table-bordered table-striped">
            <thead>
            <tr>
                <th></th>
                <th>Ingredient</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody id="ingredientsBody">
            <tr>
                <td>
            <span class="delete-icon">
                <i class="text-danger fas fa-times">X</i>
            </span>
                </td>
                <td><input type="text" name="ingredient1" class="form-control" placeholder="Enter ingredient"></td>
                <td><input type="text" name="quantity1" class="form-control" placeholder="Enter quantity"></td>

            </tr>
            <!--    <tr>-->
            <!--        <td>-->
            <!--        <span class="delete-icon">-->
            <!--          <i class="text-danger fas fa-times">X</i> &lt;!&ndash; Font Awesome X icon &ndash;&gt;-->
            <!--        </span>-->
            <!--      </td>-->
            <!--      <td>Item 2</td>-->
            <!--      <td>Quantity for Item 2</td>-->
            <!--    </tr>-->
            <!--    <tr>-->
            <!--        <td>-->
            <!--        <span class="delete-icon">-->
            <!--          <i class="text-danger fas fa-times">X</i> &lt;!&ndash; Font Awesome X icon &ndash;&gt;-->
            <!--        </span>-->
            <!--      </td>-->
            <!--      <td>Item 3</td>-->
            <!--      <td>Quantity for Item 3</td>-->
            <!--    </tr>-->
            <!--    <tr>-->
            <!--        <td>-->
            <!--        <span class="delete-icon">-->
            <!--          <i class="text-danger fas fa-times">X</i> &lt;!&ndash; Font Awesome X icon &ndash;&gt;-->
            <!--        </span>-->
            <!--      </td>-->
            <!--      <td>Item 4</td>-->
            <!--      <td>Quantity for Item 4</td>-->
            <!--    </tr>-->

            <!--    <tr>-->
            <!--        <td>-->
            <!--        <span class="delete-icon">-->
            <!--          <i class="text-danger fas fa-times">X</i> &lt;!&ndash; Font Awesome X icon &ndash;&gt;-->
            <!--        </span>-->
            <!--      </td>-->
            <!--      <td>Item 5</td>-->
            <!--      <td>Quantity for Item 5</td>-->
            <!--    </tr>-->

            <!--    <tr>-->
            <!--        <td>-->
            <!--        <span class="delete-icon">-->
            <!--          <i class="text-danger fas fa-times">X</i> &lt;!&ndash; Font Awesome X icon &ndash;&gt;-->
            <!--        </span>-->
            <!--      </td>-->
            <!--      <td>Item 6</td>-->
            <!--      <td>Quantity for Item 6</td>-->
            <!--    </tr>-->

            </tbody>
        </table>
        <button type="button" id="addRow" class="btn btn-primary">Add Item</button>
        <button type="submit" class="btn btn-success">Save Inventory</button>
    </form>


</main>

<script>
    const sidebar = document.getElementById("sidebar");
    const content = document.getElementById("content");

    function handleResize() {
        // Collapse sidebar on small screens < 576 or small devices
        if (window.innerWidth < 576) {
            sidebar.classList.add("collapsed");
            content.classList.add("expanded");
        } else {
            sidebar.classList.remove("collapsed");
            content.classList.remove("expanded");
        }
    }

    // Run on load
    handleResize();

    // Run on window resize
    window.addEventListener("resize", handleResize);

    // Burger button toggle
    const toggleBtn = document.getElementById("toggleSidebar");
    const toggleBtnMobile = document.getElementById("toggleSidebarMobile");

    function toggleSidebar() {
        sidebar.classList.toggle("collapsed");
        content.classList.toggle("expanded");
    }

    if (toggleBtn) toggleBtn.addEventListener("click", toggleSidebar);
    if (toggleBtnMobile) toggleBtnMobile.addEventListener("click", toggleSidebar);
</script>

<!--add ingredient script-->
<script>
    let ingredientCount = 1;
    document.getElementById("addRow").addEventListener("click", function(){
        ingredientCount++;
        const tableBody = document.getElementById("ingredientsBody");

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>
                <span class="delete-icon">
                    <i class="text-danger fas fa-times delete-btn">X</i>
                </span>
            </td>
            <td><input type="text" name="ingredient${ingredientCount}" class="form-control" placeholder="Enter ingredient"></td>
            <td><input type="text" name="quantity${ingredientCount}" class="form-control" placeholder="Enter quantity"></td>
        `;
        tableBody.appendChild(row);
    });

    // handle delete button click
    document.getElementById("ingredientsBody").addEventListener("click", function(event){
        if(event.target && event.target.classList.contains("delete-btn")){
            event.target.closest("tr").remove();
        }
    });
</script>

</body>
</html>