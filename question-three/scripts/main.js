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
     * Constructor. Initializes the url
     * 
     */
    constructor() {
    }

    /**
     * Used to fetch data from the API
     * 
     * @param {string} endpoint api endpoint
     * @param {function} callback callback function used to pass back the results
     */
    get(url, callback) {
        let client = new XMLHttpRequest();
        client.open("GET", url, true);
        client.send();
        client.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE) {
                let response = new Response();
                response.status = this.status;
                try {
                    if (this.responseText) {
                        response.content = JSON.parse(this.responseText);
                    } else {
                        response.content = {};
                    }
                } catch (err) {
                    console.error("Failed parse json text to object", err);
                }
                callback(response);
            }
        };
    }
}

/**
 * Used to manage people storage
 */
class PeopleRepository{

    /**
     * Local storage key
     */
    key = "starwars-people";
    
    /**
     * Saves people to the local storage
     * 
     * @param {object} people people details
     */
    save(people){
        console.debug("Saving starwars characters to local storage");
        localStorage.setItem(this.key, JSON.stringify(people));
    }

    /**
     * Retrieves people from local storage
     * 
     * @returns {string} people details
     */
    findAll(){
        console.debug("Retrieving starwars characters from local storage")
        return JSON.parse(localStorage.getItem(this.key));
    }

    /**
     * Used tho check if a value exists in the saved people list
     * @param {string} val 
     */
    contains(val){
        console.debug("Checking if " + val + " exists");
        let peopleStr = localStorage.getItem(this.key);
        return peopleStr == null ? false : peopleStr.includes(val);
    }

    /**
     * Delete person with specified url
     * @param {Array} urls person details url;
     */
    delete(urls){
        let people = this.findAll();
        urls.forEach(function(url, index){
            for(let i = 0; i < people.length; i++){
                if(url.includes(people[i]['url'])){
                    people.splice(i, 1);
                }
            }
        });
        this.save(people);  
    }
}
