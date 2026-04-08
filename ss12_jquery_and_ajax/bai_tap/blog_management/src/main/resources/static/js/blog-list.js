$(document).ready(function () {
    let currentPage = 0;
    const pageSize = 20;
    let hasNext = true;
    let isLoading = false;

    const blogTableBody = $("#blogTableBody");
    const emptyState = $("#emptyState");
    const loadMoreBtn = $("#loadMoreBtn");

    loadBlogs(true);

    $("#searchForm").on("submit", function (event) {
        event.preventDefault();
        loadBlogs(true);
    });

    $("#resetBtn").on("click", function () {
        $("#searchTitle").val("");
        $("#searchCategoryId").val("");
        loadBlogs(true);
    });

    loadMoreBtn.on("click", function () {
        if (!hasNext || isLoading) {
            return;
        }
        currentPage++;
        loadBlogs(false);
    });

    function loadBlogs(resetData) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        toggleLoadMoreButton();

        if (resetData) {
            currentPage = 0;
            blogTableBody.empty();
            emptyState.addClass("d-none");
        }

        const searchTitle = $("#searchTitle").val();
        const searchCategoryId = $("#searchCategoryId").val();

        $.ajax({
            url: "/api/v1/blogs",
            method: "GET",
            dataType: "json",
            data: {
                page: currentPage,
                size: pageSize,
                searchTitle: searchTitle,
                searchCategoryId: searchCategoryId || null
            },
            success: function (pageData) {
                renderRows(pageData.content || []);
                hasNext = !!pageData.hasNext;
                toggleEmptyState();
                toggleLoadMoreButton();
            },
            error: function () {
                if (!blogTableBody.children().length) {
                    emptyState.removeClass("d-none").text("Cannot load blogs now.");
                }
                hasNext = false;
                toggleLoadMoreButton();
            },
            complete: function () {
                isLoading = false;
                toggleLoadMoreButton();
            }
        });
    }

    function renderRows(blogs) {
        let rows = "";
        for (let i = 0; i < blogs.length; i++) {
            const blog = blogs[i];
            const imageUrl = blog.imageUrl ? escapeHtml(blog.imageUrl) : "https://via.placeholder.com/80x60?text=No+Image";
            const summary = blog.summary ? escapeHtml(blog.summary) : "";
            const categoryName = blog.categoryName ? escapeHtml(blog.categoryName) : "";
            const createdAt = formatDate(blog.createdAt);

            rows += "<tr>" +
                "<td>" + blog.id + "</td>" +
                "<td><img src=\"" + imageUrl + "\" alt=\"blog image\" style=\"width:80px;height:60px;object-fit:cover\" class=\"rounded\"></td>" +
                "<td><strong>" + escapeHtml(blog.title || "") + "</strong><br><small class=\"text-muted\">" + summary + "</small></td>" +
                "<td>" + escapeHtml(blog.author || "") + "</td>" +
                "<td><span class=\"badge bg-primary\">" + categoryName + "</span></td>" +
                "<td>" + createdAt + "</td>" +
                "<td>" +
                "<a href=\"/blogs/" + blog.id + "\" class=\"btn btn-info btn-sm\">View</a> " +
                "<a href=\"/blogs/update/" + blog.id + "\" class=\"btn btn-warning btn-sm\">Edit</a> " +
                "<form action=\"/blogs/delete/" + blog.id + "\" method=\"post\" style=\"display:inline;\">" +
                "<button type=\"submit\" class=\"btn btn-danger btn-sm\" onclick=\"return confirm('Delete this blog?')\">Delete</button>" +
                "</form>" +
                "</td>" +
                "</tr>";
        }
        blogTableBody.append(rows);
    }

    function toggleLoadMoreButton() {
        if (isLoading) {
            loadMoreBtn.prop("disabled", true).text("Loading...");
            return;
        }
        if (hasNext) {
            loadMoreBtn.prop("disabled", false).removeClass("d-none").text("Load more");
        } else {
            loadMoreBtn.prop("disabled", true).text("No more blogs");
        }
    }

    function toggleEmptyState() {
        if (blogTableBody.children().length === 0) {
            emptyState.removeClass("d-none").text("No blogs found.");
        } else {
            emptyState.addClass("d-none");
        }
    }

    function formatDate(dateString) {
        if (!dateString) {
            return "";
        }
        const date = new Date(dateString);
        if (Number.isNaN(date.getTime())) {
            return "";
        }
        const day = String(date.getDate()).padStart(2, "0");
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const year = date.getFullYear();
        return day + "-" + month + "-" + year;
    }

    function escapeHtml(value) {
        return String(value)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#39;");
    }
});
