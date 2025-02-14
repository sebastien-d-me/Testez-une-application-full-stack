import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { SessionService } from "./session.service";
import { SessionInformation } from "../interfaces/sessionInformation.interface";


describe("SessionService", () => {
    let service: SessionService;
    let httpMock: HttpTestingController;

    const mockData: SessionInformation = {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNzM5NTQ2MTE3fQ.fNmj7-TytXWP1LrFBOCbNt0tIUUcK6KFcGXTWmtggCs",
        "type": "Bearer",
        "id": 1,
        "username": "john.doe@studio.com",
        "firstName": "John",
        "lastName": "DOE",
        "admin": true
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientModule,
                HttpClientTestingModule
            ],
            providers: [SessionService]
        });
        service = TestBed.inject(SessionService);
        httpMock = TestBed.inject(HttpTestingController);
    });



    // Tests unitaires
    /// Le service doit bien être crée
    it("should create the service.", () => {
        expect(service).toBeTruthy();
    });


    /// L'utilisateur ne doit pas être connecté au début
    it("should not have the isLogged true at start", () => {
        expect(service.isLogged).toBeFalsy();
    });



    /// Tests d'intégrations
    // L'obversable doit bien nous donner la valeur du booléen isLogged true si on se connecte par exemple
    it("should return isLogged value to the app", () => {
        service.logIn(mockData);

        service.$isLogged().subscribe(sessionIsLogged => {
            expect(sessionIsLogged).toBeTruthy();
        });
    });


    /// La connexion doit passer la variable isLogged à true
    it("should change the value of the isLogged boolean (to true) after login", () => {
        service.logIn(mockData);

        expect(service.isLogged).toBeTruthy();
    });


    /// La déconnexion doit passer la variable isLogged à false
    it("should change the value of the isLogged boolean (to false) after logout", () => {
        service.logOut();

        expect(service.isLogged).toBeFalsy();
    });
    



    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
