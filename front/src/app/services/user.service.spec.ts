import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing"
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { UserService } from "./user.service";


describe("UserService", () => {
    let service: UserService;
    let httpMock: HttpTestingController;

    const mockData = {
        "id": 1,
        "email": "yoga@studio.com",
        "lastName": "Admin",
        "firstName": "Admin",
        "admin": true,
        "createdAt": "2025-02-06T16:55:49",
        "updatedAt": "2025-02-06T16:55:49"
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports:[
                HttpClientModule,
                HttpClientTestingModule
            ],
            providers: [UserService]
        });
        service = TestBed.inject(UserService);
        httpMock = TestBed.inject(HttpTestingController);
    });


  
    // Tests unitaires
    /// Le service doit bien être crée
    it("should create the service.", () => {
        expect(service).toBeTruthy();
    });



    // Tests Intégrations
    /// Le service doit être une requête GET et récupérer les informations d'un utilisateur
    it("should GET (request) the details of a user", () => {
        service.getById("1").subscribe(sessionData => {
            expect(sessionData).toEqual(mockData);
        });

        const req = httpMock.expectOne("api/user/1");
        expect(req.request.method).toBe("GET");
        req.flush(mockData);
    });


    /// Le service doit être une requête DELETE et supprimer la session
    it("should DELETE (request) a user", () => {
        service.delete("1").subscribe(sessionData => {
            expect(sessionData).toBeUndefined();
        });

        const req = httpMock.expectOne("api/user/1");
        expect(req.request.method).toBe("DELETE");
    });



    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
