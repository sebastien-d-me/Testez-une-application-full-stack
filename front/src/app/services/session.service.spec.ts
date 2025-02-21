import { TestBed } from "@angular/core/testing";
import { SessionService } from "./session.service";
import { SessionInformation } from "../interfaces/sessionInformation.interface";


describe("SessionService", () => {
    let service: SessionService;

    const mockSessionInformation: SessionInformation = {
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
            providers: [SessionService]
        });

        service = TestBed.inject(SessionService);
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le service existe bien
    it("should check that the service exist.", () => {
        expect(service).toBeTruthy();
    });

    /// Vérifie que la variable isLogged est fausse au départ
    it("should check that the isLogged variable is false at start.", () => {
        expect(service.isLogged).toBeFalsy();
    });


    // Tests intégrations
    /// Vérifie que l'observable renvoie la valeur (modifiée) du booléen isLogged à true si on se connecte
    it("should check that the observable return isLogged (to true) when we login", () => {
        service.logIn(mockSessionInformation);

        service.$isLogged().subscribe(sessionIsLogged => {
            expect(sessionIsLogged).toBeTruthy();
        });
    });

    /// Vérifie que la variable isLogged passe bien à true en cas de connexion
    it("should check that the value of isLogged change to true when we login", () => {
        service.logIn(mockSessionInformation);

        expect(service.isLogged).toBeTruthy();
    });

    /// Vérifie que la variable isLogged passe bien à false en cas de déconnexion
    it("should check that the value of isLogged change to false when we logout", () => {
        service.logIn(mockSessionInformation);

        expect(service.isLogged).toBeTruthy();
        
        service.logOut();

        expect(service.isLogged).toBeFalsy();
    });
});
