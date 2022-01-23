let paginationHTML = infos => {
    let htmlString = `<ul class="pagination" style="justify-content: center;">`;

    if (infos.startPage !== 1) {
        htmlString += `
            <li class="page-item" data-page="1">
                <a class="page-link" href="">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <li class="page-item" data-page="${infos.startRange ? 1 : (infos.startPage - 1)}">
                <a class="page-link" href="" aria-label="previous">
                    <span aria-hidden="true">&lt;</span>
                </a>
            </li>
        `
    }

    for (let i = infos.startPage; i <= infos.endPage; i++) {
        htmlString += `
            <li class="${infos.currentPage === i ? 'page-item active' : 'page-item'}" data-page="${i}"><a class="page-link" href="">${i}</a></li>
        `
    }

    if (infos.endPage !== infos.totalPageCount) {
        htmlString += `
            <li class="page-item" data-page="${infos.endRange ? infos.endPage : (infos.endPage + 1)}">
                <a class="page-link" href="" aria-label="next">
                    <span aria-hidden="true">&gt;</span>
                </a>
            </li>
            <li class="page-item" data-page="${infos.totalPageCount}">
                <a class="page-link" href="">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        `
    }

    htmlString += `</ul>`;
    return htmlString;
}

window.onload = function () {
    const urlParameter = window.location.search;
    let currentPage = 1;

    if (urlParameter.length > 0) {
        let urlParams = new URLSearchParams(urlParameter);
        currentPage = urlParams.get("page");
    }

    let paginationDiv = document.getElementById("pagination")

    axios.get(`/board/articles/pagination?page=${currentPage}`).then(({data}) => {
        paginationDiv.innerHTML = paginationHTML(data);

        let pageItems = document.getElementsByClassName("page-item");

        for (let i = 0, size = pageItems.length; i < size; i++) {
            pageItems[i].firstElementChild.setAttribute("href", `/board/articles?page=${pageItems[i].dataset.page}`);
        }
    });
}