# Blog Management - AJAX Integration

This project integrates AJAX into the blog list page with two features:

- Search blogs by title/category without page reload.
- Load more blogs in batches of 20.

## Main Endpoints

- View page: `/blogs`
- Blog API: `/api/v1/blogs`
  - Query params: `page`, `size`, `searchTitle`, `searchCategoryId`

## What Was Added

- jQuery dependency via WebJar in `build.gradle`.
- MVC controller for blog pages.
- AJAX-ready REST list response for search + load more.
- Frontend script `src/main/resources/static/js/blog-list.js`.

## Run

```powershell
./gradlew.bat bootRun
```

Then open:

- `http://localhost:8080/blogs`

## Test

```powershell
./gradlew.bat test
```

