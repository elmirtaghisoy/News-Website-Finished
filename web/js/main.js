//**************************************************
//--------------------GLOBAL VARIABLES--------------
//**************************************************
var selectedId;
var globAuthorId;
var parentIdtoAdd;
var selectedFileId;
var postIdForDeleting;


//**************************************************
//--------------------UTILS-------------------------
//**************************************************
function idSelector(id) {
    selectedId = id;
}

function closeModal() {
    $(".show").removeClass("show");
    $(".fade").removeClass("fade");
    $(".modal-backdrop").removeClass("modal-backdrop");
    $("body").removeClass("modal-open");
    $("body").css("padding", "0");
}

function fileIdSelector(fileId) {
    selectedFileId = fileId;
}

//**************************************************
//--------------------AUTHORS-----------------------
//**************************************************
function addAuthor() {
    var name = $("#authorName").val();
    var surname = $("#authorSurname").val();
    var profession = $("#authorProfession").val();
    var email = $("#authorEmail").val();
    var phone = $("#authorPhone").val();

    var data = {
        name: name,
        surname: surname,
        profession: profession,
        email: email,
        phone: phone
    };
    $.ajax({
        url: "ac?action=addAuthor",
        type: "POST",
        data: data,
        dataType: "text",
        success: function (response) {
            if (response == "success") {
                $("#addModal").hide();
                closeModal();
                alert("Müəllif əlavə edildi.");
                getGeneralAuthorsStatistics();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }

    })
}

function getAuthorById(authorId, opp) {
    globAuthorId = authorId;
    var data = {
        authorId: authorId,
        opp: opp
    }
    $.ajax({
        url: "ac?action=getAuthor",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            if (opp === 'del')
                $("#delModalSec").html(response);
            else if (opp === 'upd')
                $("#updModalSec").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function updateAuthor() {
    var name = $("#editAuthorName").val();
    var surname = $("#editAuthorSurname").val();
    var profession = $("#editAuthorProfession").val();
    var phone = $("#editAuthorPhone").val();
    var email = $("#editAuthorEmail").val();

    var data = {
        name: name,
        surname: surname,
        profession: profession,
        phone: phone,
        email: email,
        id: globAuthorId
    };
    $.ajax({
        url: "ac?action=updateAuthor",
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#editModal").hide();
                alert("Dəyişiklik edildi.");
                getGeneralAuthorsStatistics();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });

}

function deleteAuthor() {
    var data = {
        id: globAuthorId
    };
    $.ajax({
        url: "ac?action=deleteAuthor",
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#deleteModal").hide();
                alert("Müəllif silindi.");
                getGeneralAuthorsStatistics();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}


//**************************************************
//--------------------STATISTICS--------------------
//**************************************************
function getGeneralAuthorsStatistics() {
    $.ajax({
        url: "sc?action=users",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#content").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getStatisticsByMonth(month) {
    var currentMonth = ("#" + month + "Stat");
    var data = {
        month: month,
        authorId: selectedId
    };
    $.ajax({
        url: "sc?action=getStatisticsByMonth",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $(currentMonth).html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}


//**************************************************
//--------------------CATEGORIES--------------------
//**************************************************
function getCategorires() {
    $.ajax({
        url: "cc?action=getCategories",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#content").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getUnUsedCategories(parentName) {
    var data = {
        parentName: parentName,
    };

    $.ajax({
        url: "cc?action=getUnUsedCategories",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#unusedcats").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getCategoriesByParentId(parentId) {
    parentIdtoAdd = parentId;
    var data = {
        parentId: parentId,
    };
    $.ajax({
        url: "cc?action=getSubCategories",
        type: "GET",
        dataType: "html",
        data: data,
        success: function (response) {
            $("#updateCatModal").html(response);
        },
        error: function () {
            alert("Error");
        }
    });
}

function addCategorie() {
    var catName = $("#addCategoryName").val();
    var data = {
        catName: catName,
    };
    $.ajax({
        url: "cc?action=addCategorie",
        type: "POST",
        data: data,
        dataType: "text",
        success: function (response) {
            if (response == "success") {
                $("#addCategory").hide();
                closeModal();
                alert("Kateqoriya əlavə edildi.");
                getCategorires();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    })
}

function addSubCategorie() {
    var catName = $("#subCategoryName").val();
    var data = {
        catName: catName,
        parentId: parentIdtoAdd,
    };
    $.ajax({
        url: "cc?action=addSubCategorie",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#addSubCategory").hide();
                alert("Elave edildi.");
                getCategoriesByParentId(parentIdtoAdd)
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function updateSubsOfParent(id) {
    var data = {
        id: id,
    };
    $.ajax({
        url: "cc?action=updateSubsOfParent",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                alert("Alt kategoriya silindi");
                getCategoriesByParentId(parentIdtoAdd)
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function updateCategory() {
    var catName = $("#updateCategoryName").val();
    var data = {
        catName: catName,
        parentId: parentIdtoAdd,
    };
    $.ajax({
        url: "cc?action=updateCategoryName",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#updateCategory").hide();
                alert("Elave edildi.");
                getCategorires()
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function deleteCategory() {
    var data = {
        id: selectedId
    };
    $.ajax({
        url: "cc?action=deleteCategory",
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#deleteCategory").hide();
                alert("Kateqoriya silindi. Silinmish kateqoriaya aid postlar 'Diger' kateqoriyasina aid oldu.");
                getCategorires();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function addCatToMainMenu() {
    var data = {
        id: selectedId,
    };
    $.ajax({
        url: "cc?action=addToMainMenu",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#addToMenu").hide();
                alert("Kateqoriya Esas menuya elave edildi.");
                getCategorires()
            } else if (response == "fail") {
                alert("Kateqoriya alt kateqoriya oldugu ucun. Esas menuya elave edile bilmez.");
            }
        }
    });
}

function deleteCatFromMainMenu() {
    var data = {
        id: selectedId,
    };
    $.ajax({
        url: "cc?action=deleteFromMainMenu",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#deleteFromMainMenu").hide();
                alert("Kateqoriya esas menudan cixarildi.");
                getCategorires()
            } else if (response == "fail") {
                alert("Xeta");
            }
        }
    });
}


//**************************************************
//-----------------------POSTS----------------------
//**************************************************
function getAllPosts() {
    $.ajax({
        url: "pc?action=getAllPosts",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#content").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getDataForNewPost() {
    $.ajax({
        url: "pc?action=getDataForNewPost",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#addPostModal").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function deletePost() {
    var data = {
        id: selectedId
    };
    $.ajax({
        url: "pc?action=deletePosts",
        type: "POST",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#deletePostModal").hide();
                alert("Paylashim silindi.");
                getAllPosts();
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function getPostFilesById(postId) {
    var data ={
        postId:postId
    };
    $.ajax({
        url: "pc?action=getPostFilesById",
        type: "GET",
        data: data,
        dataType: "html",
        success: function (response) {
            $("#postFiles").html(response).fadeIn(500);
        },
        error: function () {
            alert("Error")
        }
    });
}

//**************************************************
//-----------------------FILES----------------------
//**************************************************

function deletePostFile() {
    var data = {
        fileId: selectedFileId
    };
    $.ajax({
        url: "pc?action=deletePostFile",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#deleteFileModal").hide();
                getPostFilesById(postIdForDeleting);
                alert("Fayl silindi.");
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}

function selectPostCover() {
    var data = {
        fileId: selectedFileId
    };
    $.ajax({
        url: "pc?action=selectPostCover",
        type: "GET",
        data: data,
        dataType: 'text',
        success: function (response) {
            if (response == "success") {
                closeModal();
                $("#selectPostCover").hide();
                getPostFilesById(postIdForDeleting);
                alert("Fayl silindi.");
            } else if (response == "fail") {
                alert("Xəta");
            }
        }
    });
}


//**************************************************
//---------------------SEARHC-----------------------
//**************************************************
function getDataForSearch() {
    $.ajax({
        url: "pc?action=getDataForSearch",
        type: "GET",
        dataType: "html",
        success: function (response) {
            $("#searchBox").html(response);
        },
        error: function () {
            alert("Error")
        }
    });
}

function getSearchedData() {
    //For Search
    var keyword = $("#searchNews").val();

    //For Advanced Search
    var dateFrom = $("#datepicker1").val();
    var dateTo = $("#datepicker2").val();
    var userFrmSrc = $("#userFrmSrc").val();
    var likeFrmSrc = $("#likeFrmSrc").val();
    var viewFrmSrc = $("#viewFrmSrc").val();
    var catFrmSrc = $("#catFrmSrc").val();

    var data = {
        keyword:keyword,
        dateFrom:dateFrom,
        dateTo:dateTo,
        userFrmSrc:userFrmSrc,
        likeFrmSrc:likeFrmSrc,
        viewFrmSrc:viewFrmSrc,
        catFrmSrc:catFrmSrc
    };

    $.ajax({
        url: "pc?action=getSearchedData",
        type: "GET",
        data: data,
        dataType: "html",
        success: function (response) {
            $("#content").html(response);

        },
        error: function () {
            alert("Error")
        }
    });
}

function advancedSearch(){

}


//**************************************************
//--------------------GENERAL-----------------------
//**************************************************
$(function () {
    $('.nav-link').click(function () {
        var btnId = $(this).attr('id');
        if (btnId == "postsBtnId") {
            $("#additionalNav").show();
            getAllPosts();
        } else if (btnId == "usersBtnId") {
            $("#additionalNav").hide();
            getGeneralAuthorsStatistics();
        } else if (btnId == "categoriesBtnId") {
            $("#additionalNav").hide();
            getCategorires();
        }
    });
});
