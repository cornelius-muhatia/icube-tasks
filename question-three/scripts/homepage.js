/*
 * Http client service
 */
let httpService = new HttpService(document.getElementById("progress-indicator"));
/**
 * People repository for storing favorite list
 */
let peopleRepo = new PeopleRepository();
/*
 * Next page url. Default to the first page for initial loading
 */
let nextPageUrl = "https://swapi.dev/api/people";
/**
 * Previous page url
 */
let prevPageUrl = null;

//Load table on page load
window.onload = function(){
  loadPage(nextPageUrl);
}


/**
 * Load people table content
 * 
 * @param page_url page url
*/
function loadPage(page_url, callback) {
  httpService.get(page_url, function (response) {
    nextPageUrl = response.content.next;
    prevPageUrl = response.content.previous;
    if (response.status == 200) {
      document.getElementById("table-content").innerHTML = "";
      let tBody = document.getElementById("table-content");
      response.content.results.forEach(function (value, index) {
        tBody.innerHTML += '<tr><td>'
          + value['name'] 
          + (peopleRepo.contains(value['url']) ? ' <img src="images/favorite-black.svg"/>' : '')
          + '</td><td>'
          + value['gender']
          + '</td><td>'
          + value['height']
          + '</td><td>'
          + value['skin_color']
          + '</td><td>'
          + '<a href="details.html?url=' + value['url'] + '">View</a>'
          + '</td></tr>';
      });
    }
    if (typeof callback === "function") {
      callback(response.content.count);
    }
  });
}

/**
 * Load next page
 * 
 * @param {*} next button reference
*/
function nextPage(button) {
  console.debug("Loading next page...");
  loadPage(nextPageUrl, function (count) {
    if (prevPageUrl != null) {
      document.getElementById('previous-button').disabled = false;
    }
    if (nextPageUrl == null) {
      button.disabled = true;
    }
  });
}

/**
 * Load previous page
 * 
 * @param {*} previous button reference
*/
function prevPage(button) {
  console.debug("Loading previous page...");
  loadPage(prevPageUrl, function (count) {
    if (nextPageUrl != null) {
      document.getElementById('next-button').disabled = false;
    }
    if (prevPageUrl == null) {
      button.disabled = true;
    }
  });
}