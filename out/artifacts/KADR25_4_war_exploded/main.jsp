<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 10.01.2020
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
    <jsp:include page="WEB-INF/clientPages/static/headerLinks.jsp"></jsp:include>
    <style>
        .nav-link {
            color: white !important;
        }

        .allCatList {
            width: 50% !important;
            transition: .5s;
        }

        .allCatList:hover {
            text-decoration: underline !important;
        }
    </style>
</head>
<body>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
        src="https://connect.facebook.net/az_AZ/sdk.js#xfbml=1&version=v5.0"></script>
<%----%>
<%----%>
<header>
    <div id="myDesktopNav" class="header-top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6 col-6 header-top-left no-padding">
                    <ul>
                        <li><a href="#"><i class="fab fa-google"></i></a></li>
                        <li><a href="#"><i class="fab fa-youtube"></i></a></li>
                        <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                        <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                    </ul>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-6 header-top-right no-padding">
                    <ul>
                        <li><a href="tel:+440 012 3654 896"><span class="lnr lnr-phone-handset"></span><span>+440 012 3654 896</span></a>
                        </li>
                        <li><a href="mailto:support@colorlib.com"><span class="lnr lnr-envelope"></span><span>support@colorlib.com</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="logo-wrap">
        <div class="container">
            <div class="row justify-content-between align-items-center">
                <div class="col-lg-4 col-md-4 col-sm-12 logo-left no-padding">
                    <a href="index.html">
                        <img class="img-fluid" src="img/logo.png" alt="">
                    </a>
                </div>
                <div class="col-lg-8 col-md-8 col-sm-12 logo-right no-padding ads-banner">
                    <img class="img-fluid" src="img/banner-ad.jpg" alt="">
                </div>
            </div>
        </div>
    </div>
    <div style="display: none" class="container-fluid main-menu" id="main-menu">
        <div class="row align-items-center justify-content-between">
            <nav id="nav-menu-container">
                <ul id="cliesntNav" class="nav-menu">
                </ul>
            </nav><!-- #nav-menu-container -->
            <div style="display: none" class="navbar-right">
                <form class="Search">
                    <input type="text" class="form-control Search-box" name="Search-box" id="Search-box"
                           placeholder="Search">
                    <label for="Search-box" class="Search-box-label">
                        <span class="lnr lnr-magnifier"></span>
                    </label>
                    <span class="Search-close">
								<span class="lnr lnr-cross"></span>
							</span>
                </form>
            </div>
        </div>
    </div>
</header>
<%----%>
<%----%>


<!-- Nav -->
<nav style="background-color: #04091e !important;" class="navbar navbar-expand-lg navbar-dark bg-dark myNav">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul id="clientNav" class="navbar-nav">

        </ul>
    </div>
    <div class="fb-like" data-href="https://developers.facebook.com/docs/plugins/" data-width="" data-layout="button"
         data-action="like" data-size="large" data-share="true"></div>
</nav>
<!-- Nav -->

<%--Main Page--%>
<div class="site-main-container">


    <!-- Slide -->
    <section id="slide"></section>
    <!-- Slide -->

    <%--Breadcumb--%>
    <section id="breadcumb"></section>
    <%--Breadcumb--%>


    <!-- Start Post Area -->
    <section class="latest-post-area pb-120">
        <div class="container no-padding">
            <div class="row">


                <%--Context--%>
                <div id="context" class="col-lg-8 post-list">

                    <!-- Start Post Area -->
                    <div id="lastPosts"></div>
                    <div id="postsFromCurrentDate"></div>
                    <div id="oneCategory"></div>
                    <div id="oneNews"></div>
                    <div id="searchedData"></div>
                    <!-- End Post Area -->

                    <!-- Start banner-ads Area -->
                    <div class="col-lg-12 ad-widget-wrap mt-30 mb-30">
                        <img class="img-fluid" src="img/banner-ad.jpg" alt="">
                    </div>
                    <!-- End banner-ads Area -->

                    <!-- Start popular-post Area -->
                    <div id="mostViewed"></div>
                    <!-- End popular-post Area -->

                </div>
                <%--Context--%>


                <%--SideBar--%>
                <div class="col-lg-4">
                    <div class="sidebars-area">
                        <div class="single-sidebar-widget most-popular-widget">
                            <h6 class="title">Digər Kateqoriyalar</h6>
                            <ul id="otherCategories" class="social-list">

                            </ul>
                        </div>
                        <div class="single-sidebar-widget editors-pick-widget">
                            <h6 class="title">Tarixdə bugün</h6>
                            <div id="categoryDate" class="editors-pick-post">

                            </div>
                        </div>
                        <div class="single-sidebar-widget ads-widget">
                            <img class="img-fluid" src="img/sidebar-ads.jpg" alt="">
                        </div>
                        <div class="single-sidebar-widget social-network-widget">
                            <h6 class="title">Sosial Şəbəkələrimiz</h6>
                            <ul class="social-list">
                                <li class="d-flex justify-content-between align-items-center yt">
                                    <div class="icons d-flex flex-row align-items-center">
                                        <i class="fab fa-google" aria-hidden="true"></i>
                                    </div>
                                    <a href="#">Kadr <i class="far fa-hand-point-right"></i> Gmail</a>
                                </li>
                                <li class="d-flex justify-content-between align-items-center fb">
                                    <div class="icons d-flex flex-row align-items-center">
                                        <i class="fab fa-facebook-f"></i>
                                    </div>
                                    <a href="#">Kadr 25 <i class="far fa-hand-point-right"></i> Facebook</a>
                                </li>
                                <li class="d-flex justify-content-between align-items-center tw">
                                    <div class="icons d-flex flex-row align-items-center">
                                        <i class="fab fa-instagram" aria-hidden="true"></i>
                                    </div>
                                    <a href="#">Kadr 25 <i class="far fa-hand-point-right"></i> Instagram</a>
                                </li>
                                <li class="d-flex justify-content-between align-items-center yt">
                                    <div class="icons d-flex flex-row align-items-center">
                                        <i class="fab fa-youtube" aria-hidden="true"></i>
                                    </div>
                                    <a href="#">Kadr <i class="far fa-hand-point-right"></i> Youtubeda</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--SideBar--%>


            </div>
        </div>
    </section>
    <!-- End Post Area -->


</div>
<%--Main Page--%>


<!-- Footer -->
<jsp:include page="WEB-INF/clientPages/static/footer.jsp"></jsp:include>
<!-- Footer -->

<%--FooterLinks--%>
<jsp:include page="WEB-INF/clientPages/static/footerLinks.jsp"></jsp:include>
<%--FooterLinks--%>

</body>
</html>