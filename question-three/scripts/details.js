/**
 * Retrieve person details url from get parameters
 */
function getPersonUrl() {
    let urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("url");
}

/**
 * Load home world details
 * @param {string} url home world url
 * @param {HttpService} httpService Http client service
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function loadHomeWorld(httpService, url, dateFormatter) {
    httpService.get(url, function (response) {
        if (response.status === 200) {
            document.getElementById("home-world").innerHTML = '';
            let detailsTable = document.getElementById("home-world");
            detailsTable.innerHTML += '<tr><th>Name:</th><td>' + response.content['name'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Rotation Period:</th><td>' + response.content['rotation_period'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Orbital Period:</th><td>' + response.content['orbital_period'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Diameter:</th><td>' + response.content['diameter'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Climate:</th><td>' + response.content['climate'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Gravity:</th><td>' + response.content['gravity'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Terrain:</th><td>' + response.content['terrain'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Surface Water:</th><td>' + response.content['surface_water'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Population:</th><td>' + response.content['population'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Creation Date:</th><td>' + dateFormatter.format(new Date(response.content['created'])) + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Updated On:</th><td>' + dateFormatter.format(new Date(response.content['edited'])) + '</td></tr>';
        }
    });
}

/**
 * Fetch Starships
 * 
 * @param {HttpService} httpService Http client service
 * @param {array} urls starship urls
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function loadStarships(httpService, urls, dateFormatter) {
    document.getElementById('starship-details').innerHTML = '';
    let shipsDetails = document.getElementById('starship-details');
    urls.forEach(function (value, index) {
        appendStarShips(shipsDetails, httpService, value, dateFormatter);
    });
}

/**
 * Append starship rows
 * 
 * @param {*} tableBody Starship table body reference
 * @param {HttpService} httpService Http client service
 * @param {string} url starship url
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function appendStarShips(tableBody, httpService, url, dateFormatter) {
    httpService.get(url, function (response) {
        if (response.status === 200) {
            tableBody.innerHTML += '<tr>'
                + '<td>' + response.content['name'] + '</td>'
                + '<td>' + response.content['model'] + '</td>'
                + '<td>' + response.content['cost_in_credits'] + '</td>'
                + '<td>' + response.content['cargo_capacity'] + '</td>'
                + '<td>' + response.content['starship_class'] + '</td>'
                + '<td>' + dateFormatter.format(new Date(response.content['created'])) + '</td>'
                + '</tr>';

        }
    });
}

/**
 * Fetch vehicles
 * 
 * @param {HttpService} httpService Http client service
 * @param {array} urls starship urls
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function loadVehicles(httpService, urls, dateFormatter) {
    document.getElementById('vehicle-details').innerHTML = '';
    let shipsDetails = document.getElementById('vehicle-details');
    urls.forEach(function (value, index) {
        appendVehicles(shipsDetails, httpService, value, dateFormatter);
    });
}

/**
 * Append vehicle rows
 * 
 * @param {*} tableBody Starship table body reference
 * @param {HttpService} httpService Http client service
 * @param {string} url starship url
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function appendVehicles(tableBody, httpService, url, dateFormatter) {
    httpService.get(url, function (response) {
        if (response.status === 200) {
            tableBody.innerHTML += '<tr>'
                + '<td>' + response.content['name'] + '</td>'
                + '<td>' + response.content['model'] + '</td>'
                + '<td>' + response.content['cost_in_credits'] + '</td>'
                + '<td>' + response.content['cargo_capacity'] + '</td>'
                + '<td>' + response.content['vehicle_class'] + '</td>'
                + '<td>' + dateFormatter.format(new Date(response.content['created'])) + '</td>'
                + '</tr>';

        }
    });
}

/**
 * Fetch films
 * 
 * @param {HttpService} httpService Http client service
 * @param {array} urls films urls
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function loadFilms(httpService, urls, dateFormatter) {
    document.getElementById('film-details').innerHTML = '';
    let filmsDetails = document.getElementById('film-details');
    urls.forEach(function (value, index) {
        appendFilms(filmsDetails, httpService, value, dateFormatter);
    });

}

/**
 * Append films rows
 * 
 * @param {*} tableBody table body reference
 * @param {HttpService} httpService Http client service
 * @param {string} url films url
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function appendFilms(tableBody, httpService, url, dateFormatter) {
    httpService.get(url, function (response) {
        if (response.status === 200) {
            tableBody.innerHTML += '<tr>'
                + '<td>' + response.content['title'] + '</td>'
                + '<td>' + response.content['director'] + '</td>'
                + '<td>' + response.content['producer'] + '</td>'
                + '<td>' + response.content['opening_crawl'] + '</td>'
                + '<td>' + response.content['episode_id'] + '</td>'
                + '<td>' + dateFormatter.format(new Date(response.content['created'])) + '</td>'
                + '</tr>';

        }
    });
}

/**
 * Fetch species
 * 
 * @param {HttpService} httpService Http client service
 * @param {array} urls species urls
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function loadSpecies(httpService, urls, dateFormatter) {
    document.getElementById('species-details').innerHTML = '';
    let speciesDetails = document.getElementById('species-details');
    urls.forEach(function (value, index) {
        appendSpecies(speciesDetails, httpService, value, dateFormatter);
    });

}

/**
 * Append species rows
 * 
 * @param {*} tableBody table body reference
 * @param {HttpService} httpService Http client service
 * @param {string} url species url
 * @param {Intl.DateTimeFormat} dateFormatter Date formatter
 */
function appendSpecies(tableBody, httpService, url, dateFormatter) {
    httpService.get(url, function (response) {
        if (response.status === 200) {
            tableBody.innerHTML += '<tr>'
                + '<td>' + response.content['name'] + '</td>'
                + '<td>' + response.content['classification'] + '</td>'
                + '<td>' + response.content['designation'] + '</td>'
                + '<td>' + response.content['average_lifespan'] + '</td>'
                + '<td>' + response.content['average_height'] + '</td>'
                + '<td>' + response.content['skin_colors'] + '</td>'
                + '<td>' + response.content['language'] + '</td>'
                + '<td>' + dateFormatter.format(new Date(response.content['created'])) + '</td>'
                + '</tr>';
        }
    });
}


/*
 * Swapi base url
 */
const BASE_URL = "https://swapi.dev/api";
/*
* Http service bean
*/
let httpService = new HttpService();
/**
 * People/character repository
 */
let peopleRepo = new PeopleRepository();

window.onload = function () {
    /**
     * Date formatter
     */
    const dateFormatter = new Intl.DateTimeFormat('en-us', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    });

    //Load user details
    httpService.get(this.getPersonUrl(), function (response) {
        if (response.status === 200) {
            document.getElementById("personal-details").innerHTML = '';
            let detailsTable = document.getElementById("personal-details");
            detailsTable.innerHTML += '<tr><th>Name:</th><td>' + response.content['name'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Gender:</th><td>' + response.content['gender'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Height:</th><td>' + response.content['height'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Mass:</th><td>' + response.content['mass'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Hair Colour:</th><td>' + response.content['hair_color'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Eye Colour:</th><td>' + response.content['eye_color'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Skin Colour:</th><td>' + response.content['skin_color'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Birth Year:</th><td>' + response.content['birth_year'] + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Creation Date:</th><td>' + dateFormatter.format(new Date(response.content['created'])) + '</td></tr>';
            detailsTable.innerHTML += '<tr><th>Updated On:</th><td>' + dateFormatter.format(new Date(response.content['edited'])) + '</td></tr>';
            loadHomeWorld(httpService, response.content['homeworld'], dateFormatter);
            loadStarships(httpService, response.content['starships'], dateFormatter);
            loadFilms(httpService, response.content['films'], dateFormatter);
            loadVehicles(httpService, response.content['vehicles'], dateFormatter);
            loadSpecies(httpService, response.content['species'], dateFormatter);
        }
    });
    loadFavoriteList();
}

/**
 * Add to favorite
 */
function addFavorite(button) {
    let favoritePanel = document.getElementById("favorite-panel");
    if (favoritePanel.style.display == 'none') {
        document.getElementById("favorite-panel").innerHTML = '';
        loadFavoriteCheckboxes(favoritePanel);
        button.innerHTML = 'Update Favorite'
    } else {
        let checkBoxes = document.getElementsByName('favorite-checkbox');
        favorites = peopleRepo.findAll();
        checkBoxes.forEach(function (val, index) {
            if (val.checked) {
                favorites.push(JSON.parse(unescape(val.value)));
            }
        });
        peopleRepo.save(favorites);
        loadFavoriteList();
        favoritePanel.style.display = 'none';
        button.innerHTML = 'Add';
    }
}

/**
 * Load favorite checkboxes for selection
 * 
 * @param {*} favoritePanel favorite form content view
 */
function loadFavoriteCheckboxes(favoritePanel) {
    httpService.get(BASE_URL + "/people", function (response) {
        if (response.status == 200) {
            favoritePanel.style.display = 'flex';
            response.content.results.forEach(function (val, index) {
                if (!peopleRepo.contains(val['url'])) {
                    favoritePanel.innerHTML += '<div class="checkbox">'
                        + '<input type="checkbox" name="favorite-checkbox" value="'
                        + escape(JSON.stringify({
                            name: val['name'],
                            url: val['url'],
                            gender: val['gender'],
                            height: val['height'],
                            skin_color: val['skin_color']
                        }))
                        + '"/> '
                        + '<label>' + val['name'] + '</label>'
                        + '</div>';
                }
            });
        }
    });
}

/**
 * Load favorites from storage
 * 
 * @param {Intl.DateTimeFormat} dateFormatter date formatter
 */
function loadFavoriteList() {
    document.getElementById('favorite-content').innerHTML = '';
    let people = peopleRepo.findAll();
    let favoriteElem = document.getElementById('favorite-content');
    people.forEach(function (value, index) {
        favoriteElem.innerHTML += '<tr>'
            + '<td><input type="checkbox" name="select-favorite" onchange="selectFavoriteChanged(this)" value="' + value['url'] + '" />'
            + '<td>' + value['name'] + '</td>'
            + '<td>' + value['gender'] + '</td>'
            + '<td>' + value['height'] + '</td>'
            + '<td>' + value['skin_color'] + '</td>'
            + '</tr>'
    });
}

/**
 * Remove favorites from repository
 * 
 * @param {*} button 
 */
function removeFavorite(button){
    checkboxes = document.getElementsByName("select-favorite");
    let urls = [];
    checkboxes.forEach(function(checkbox, index){
        if(checkbox.checked){
            urls.push(checkbox.value);
        }
    });
    peopleRepo.delete(urls);
    this.loadFavoriteList();
}

/**
 * Keeps count of checked favorite checkboxes
 */
checkedFavoritesCount = 0;

/**
 * Enables/Disables delete favorite button based on selected records
 * 
 * @param {*} checkbox Changed checkbox reference
 */
function selectFavoriteChanged(checkbox){
    if(checkbox.checked){
        checkedFavoritesCount++;
    } else{
        checkedFavoritesCount--;
    }
    document.getElementById("delete-favorite").disabled = checkedFavoritesCount < 1;
}

