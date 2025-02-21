import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { AuthService } from "./auth.service";
import { RegisterRequest } from "../interfaces/registerRequest.interface";
import { LoginRequest } from "../interfaces/loginRequest.interface";


describe("SessionsService", () => {
    let authService: AuthService;
    let httpMock: HttpTestingController;

    const mockRegister: RegisterRequest = {
        "email": "test@test.com",
        "firstName": "John",
        "lastName": "DOE",
        "password": "test123"
    }

    const mockLogin: LoginRequest = {
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


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le service existe bien
    it("should check that the service exist.", () => {
        expect(authService).toBeTruthy();
    });


    // Tests intégrations
    /// Le service doit être une requête POST pour inscrire l'utilsiateur
    it("should check that it is a POST (request) to register a new user.", () => {
        authService.register(mockRegister).subscribe(sessionData => {
            expect(sessionData).toEqual(mockRegister);
        });

        const req = httpMock.expectOne("api/auth/register");
        expect(req.request.method).toBe("POST");
        req.flush(mockRegister);
    });


    /// Le service doit être une requête POST pour connecter l'utilisateur
    it("should check that it is a POST (request) to login the user.", () => {
        authService.login(mockLogin).subscribe(sessionData => {
            expect(sessionData).toEqual(mockLogin);
        });

        const req = httpMock.expectOne("api/auth/login");
        expect(req.request.method).toBe("POST");
        req.flush(mockLogin);
    });


    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
