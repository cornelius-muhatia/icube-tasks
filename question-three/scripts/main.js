/**
 * Http Response class
 */
class Response {
    /*
    * Response status
    */
    status;
    /*
    * Server response content
    */
    content;

    /**
     * Constructor
     * 
     * @param {*} status 
     * @param {*} content 
     */
    constructor(status, content) {
        this.status = status;
        this.content = content;
    }
}

/**
 * Http service
 */
class HttpService {

    /**
     * Maintains count of active requests
     */
    loadingSlots = 0;
    /**
     * Progress indicator element
     */
    loadingElement;

    /**
     * Constructor. Initializes the url
     * 
     */
    constructor(loadingElement) {
        this.loadingElement = loadingElement;
    }

    /**
     * Used to fetch data from the API
     * 
     * @param {string} endpoint api endpoint
     * @param {function} callback callback function used to pass back the results
     */
    get(url, callback) {
        this.loadingSlots++;
        if (this.loadingElement) {
            this.loadingElement.style.visibility = 'visible';
        }
        let client = new XMLHttpRequest();
        let inst = this;
        client.open("GET", url, true);
        client.send();
        client.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE) {
                let response = new Response();
                response.status = this.status;
                if (this.status == 200) {
                    try {
                        if (this.responseText) {
                            response.content = JSON.parse(this.responseText);
                        } else {
                            response.content = {};
                        }
                    } catch (err) {
                        console.error("Failed parse json text to object", err);
                    }
                }
                callback(response);
                inst.loadingSlots--;
                if (inst.loadingElement && inst.loadingSlots < 1) {
                    inst.loadingElement.style.visibility = 'hidden';
                }
            }
        };
        client.onerror = function () {
            alert("Failed to make connection to the server. Please check your internet connection before reloading the page")
        }
    }
}

/**
 * Used to manage people storage
 */
class PeopleRepository {

    /**
     * Local storage key
     */
    key = "starwars-people";

    /**
     * Saves people to the local storage
     * 
     * @param {object} people people details
     */
    save(people) {
        console.debug("Saving starwars characters to local storage");
        localStorage.setItem(this.key, JSON.stringify(people));
    }

    /**
     * Retrieves people from local storage
     * 
     * @returns {string} people details
     */
    findAll() {
        console.debug("Retrieving starwars characters from local storage")
        let peopleStr = localStorage.getItem(this.key);
        return peopleStr == null ? [] : JSON.parse(peopleStr);
    }

    /**
     * Used tho check if a value exists in the saved people list
     * @param {string} val 
     */
    contains(val) {
        console.debug("Checking if " + val + " exists");
        let peopleStr = localStorage.getItem(this.key);
        return peopleStr == null ? false : peopleStr.includes(val);
    }

    /**
     * Delete person with specified url
     * @param {Array} urls person details url;
     */
    delete(urls) {
        let people = this.findAll();
        urls.forEach(function (url, index) {
            for (let i = 0; i < people.length; i++) {
                if (url.includes(people[i]['url'])) {
                    people.splice(i, 1);
                }
            }
        });
        this.save(people);
    }
}
