
const MainJs = require("../main.js");

describe("Main.js test suite", function(){
    it("Test Response Class Initialization", function(){
        expect(new MainJs.Response(400, null)).toBeTruthy();
    });

    it("PeopleRepository Tests", function(){
        expect(new MainJs.PeopleRepository()).toBeTruthy();
    });

    it("HttpService Tests", function(){
        let element = jasmine.createSpyObj("element", ["style"]);
        element.style.and.returnValue(
            {visibility: 'none'}
        );

        let httpService = new MainJs.HttpService(element);
        expect(httpService).toBeTruthy();
    })

});
