import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { AuthService } from "./auth.service";
import { RegisterRequest } from "../interfaces/registerRequest.interface";
import { LoginRequest } from "../interfaces/loginRequest.interface";


describe("SessionsService", () => {
    let authService: AuthService;
    let httpMock: HttpTestingController;

    const mockDataRegister: RegisterRequest = {
        "email": "test@test.com",
        "firstName": "John",
        "lastName": "DOE",
        "password": "test123"
    }

    const mockDataLogin: LoginRequest = {
        "email": "test@test.com",
        "password": "test123"
    }

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientModule,
                HttpClientTestingModule
            ],
            providers: [AuthService]
        });
        authService = TestBed.inject(AuthService);
        httpMock = TestBed.inject(HttpTestingController);
    });



    // Tests unitaires
    /// Le service doit bien être crée
    it("should create the service.", () => {
        expect(authService).toBeTruthy();
    });


    // Tests Intégrations
    /// Le service doit être une requête POST et inscrire l'utilsiateur
    it("should POST (request) the register of new user", () => {
        authService.register(mockDataRegister).subscribe(sessionData => {
            expect(sessionData).toEqual(mockDataRegister);
        });

        const req = httpMock.expectOne("api/auth/register");
        expect(req.request.method).toBe("POST");
        req.flush(mockDataRegister);
    });


    /// Le service doit être une requête GET et connecter l'utilisateur
    it("should POST (request) the login of the user", () => {
        authService.login(mockDataRegister).subscribe(sessionData => {
            expect(sessionData).toEqual(mockDataLogin);
        });

        const req = httpMock.expectOne("api/auth/login");
        expect(req.request.method).toBe("POST");
        req.flush(mockDataLogin);
    });



    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
