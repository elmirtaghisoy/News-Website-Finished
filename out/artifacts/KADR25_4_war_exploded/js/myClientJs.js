function getSlidePosts() {
    $.ajax({
        url: "pc?action=getSlidePosts",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#slide").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getLastPosts() {
    $.ajax({
        url: "pc?action=getLastPosts",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#lastPosts").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getDateCategory() {
    $.ajax({
        url: "pc?action=getDateCategoryForClient",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#categoryDate").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getMostViewedPosts() {
    $.ajax({
        url: "pc?action=getMostViewedPosts",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#mostViewed").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getMainCategories() {
    $.ajax({
        url: "cc?action=getMainCategories",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#clientNav").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getCategoriesByParentId(parentId) {
    var data = {
        parentId: parentId,
    };
    $.ajax({
        url: "cc?action=getSubCategoriesClient",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $(".subCategories").html(response);
        },
        error: function () {
            alert("Error");
        }
    });
}

function getCategoriesByParentIdForBRC(parentId) {
    var data = {
        parentId: parentId,
    };
    $.ajax({
        url: "cc?action=getSubCategoriesForBRC",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#subCategoriesInBRC").html(response);
        },
        error: function () {
            alert("Error");
        }
    });
}

function getAllCategories() {
    $.ajax({
        url: "cc?action=getCategoriesForClient",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#otherCategories").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}





function sideBar() {
    getAllCategories();
    getDateCategory();
}

function header() {
    getMainCategories();
}

function mainPage() {
    $("#breadcumb").fadeOut(500);
    $("#lastPosts").fadeIn(500);
    $("#slide").fadeIn(500);
    $("#mostViewed").fadeIn(500);
    $("#oneNews").fadeOut(500);

    getSlidePosts();
    getLastPosts();
    getMostViewedPosts();
}

function getCategrieById(catId) {
    var data = {
        catId: catId,
    };
    $.ajax({
        url: "cc?action=getCategorieByIdForBRC",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            getCategoriesByParentIdForBRC(catId);
            $("#breadcumb").html(response).fadeIn(500);
        }
    });
}

function getPostsByCatId(catId) {
    var data = {
        catId: catId,
    };
    $.ajax({
        url: "pc?action=getPostsByCatId",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#oneCategory").html(response).fadeIn(500);
            getCategrieById(catId);
            $("#slide").fadeOut(500);
            $("#lastPosts").fadeOut(500);
            $("#mostViewed").fadeOut(500);
            $("#oneNews").fadeOut(500);
            $("#postsFromCurrentDate").fadeOut(500);
            topFunction();
        },
        error: function () {
            alert("Error")
        }
    });
}

function getPostsByParentId(parentId) {
    var data = {
        parentId: parentId
    };
    $.ajax({
        url: "pc?action=getPostsByParentId",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#oneCategory").html(response).fadeIn(500);
            getCategrieById(parentId);
            $("#slide").fadeOut(500);
            $("#lastPosts").fadeOut(500);
            $("#mostViewed").fadeOut(500);
            $("#oneNews").fadeOut(500);
            $("#postsFromCurrentDate").fadeOut(500);
            topFunction();
        },
        error: function () {
            alert("Error")
        }
    });
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function getPostFilesByIdForClient(postId) {
    var data ={
        postId:postId
    };
    $.ajax({
        url: "pc?action=getPostFilesByIdForClient",
        type: "GET",
        data: data,
        dataType: "html",
        success: function (response) {
            $("#postFiles").html(response).fadeIn(100);
            $("#lastPosts").fadeOut(500);
            $("#slide").fadeOut(500);
            $("#mostViewed").fadeOut(500);
            $("#postsFromCurrentDate").fadeOut(500);
            topFunction();
        },
        error: function () {
            alert("Error")
        }
    });
}

function getPostByIdForClient(id) {
    var data ={
        id:id
    };
    $.ajax({
        url: "pc?action=getPostByIdForClient",
        type: "GET",
        data: data,
        dataType: "html",
        success: function (response) {
            $("#oneNews").html(response).fadeIn(500);
            getPostFilesByIdForClient(id)
            $("#lastPosts").fadeOut(500);
            $("#slide").fadeOut(500);
            $("#mostViewed").fadeOut(500);
            $("#oneCategory").fadeOut(500);
            $("#postsFromCurrentDate").fadeOut(500);
            topFunction();
        },
        error: function () {
            alert("Error")
        }
    });
}

function getPostsByDate(dt){
    var data ={
        dt:dt
    };
    $.ajax({
        url: "pc?action=getPostsByDateForClient",
        type: "GET",
        data: data,
        dataType: "html",
        success: function (response) {
            $("#postsFromCurrentDate").html(response).fadeIn(500);
            $("#lastPosts").fadeOut(500);
            $("#slide").fadeOut(500);
            $("#mostViewed").fadeOut(500);
            $("#oneCategory").fadeOut(500);
            $("#oneNews").fadeOut(500);
            topFunction();
        },
        error: function () {
            alert("Error")
        }
    });
}

$(function () {
    mainPage();
    header();
    sideBar();


});