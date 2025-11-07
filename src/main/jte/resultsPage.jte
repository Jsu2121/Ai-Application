<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Results</title>
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

        .title, .title a{
            font-size: 40px;
            list-style: none;
            color: black;
            text-decoration: none;
        }
        .title:hover{
            transform: scale(1.05);
        }

        .menu {
            font-size: 50px;
            text-align: center;
            justify-self: center;
            width: 228px;
        }

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

        .profile {
            font-size: 50px;
            text-align: right;
            justify-self: right;
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

        .result-card{
            background: white;
            border-radius: 10px;
        }

        .menu-btns{
            text-decoration: none;
            justify-self: center;
            justify-content: center;
            text-align: center;
        }

        .history-btn{
            text-decoration: none;
            color: black;
        }

        .favorite-button {
            display: flex;
            float: right;
            border: 2px solid orange;
        }

        .inventory {
            margin-top: auto;
            padding: 20px;
            justify-self: center;
            text-align: center;

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
            <a href="home">Recipe Maker</a>
        </div>
        <div class="profile">
            <a href="#"><i class="bi bi-person-circle text-dark" ></i></a>
        </div>
    </div>
</header>

<!-- Sidebar -->
<div id="sidebar" class="position-fixed h-100 sidebar p-3">
    <div class="menu">
        <h1 class="mb-0">Menu</h1>
    </div>
    <!--    change active over to favorites in favorites.html-->
    <div class="menu-btns">
        <div class="btn-group" role="group" aria-label="Navigation button group">
            <a href="results" class="btn btn-outline-secondary btn-lg active">History</a>
            <a href="favorites" class="btn btn-outline-secondary btn-lg">Favorites</a>
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
        <a class="btn btn-success btn-lg" href="inventory" role="button">Inventory</a>
    </div>
</div>

<main id="content" class="content p-4 d-flex flex-column justify content-center align-items-center">
    <p>History:</p>
    <div id="middle">

        <div class="border-bottom border-secondary mb-4 p-2 result-card">
            <h3>Recipe Name 1</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam rerum ipsam veritatis culpa est
                delectus laudantium minima labore sint commodi?</p>
            <button type="button" class="btn btn-secondary expand-button">Expand</button>
            <button type="button" class="btn btn-outline-warning favorite-button">
                <i class="bi bi-star"></i>
                Favorite
            </button>
        </div>

        <div class="border-bottom border-secondary mb-4 p-2 result-card">
            <h3>Recipe Name 2</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam rerum ipsam veritatis culpa est
                delectus laudantium minima labore sint commodi?</p>
            <button type="button" class="btn btn-secondary expand-button">Expand</button>
            <button type="button" class="btn btn-outline-warning favorite-button">
                <i class="bi bi-star"></i>
                Favorite
            </button>
        </div>

        <div class="border-bottom border-secondary mb-4 p-2 result-card">
            <h3>Recipe Name 3</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quam rerum ipsam veritatis culpa est
                delectus laudantium minima labore sint commodi?</p>
            <button type="button" class="btn btn-secondary expand-button">Expand</button>
            <button type="button" class="btn btn-outline-warning favorite-button">
                <i class="bi bi-star"></i>
                Favorite
            </button>
        </div>
    </div>
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
</body>
</html>