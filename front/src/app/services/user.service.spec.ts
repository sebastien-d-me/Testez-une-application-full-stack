import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { UserService } from "./user.service";


describe("UserService", () => {
    let service: UserService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports:[
                HttpClientModule
            ]
        });
        service = TestBed.inject(UserService);
    });


  
    // Tests unitaires
    /// Vérifie que le service est bien crée
    it("should create", () => {
        expect(service).toBeTruthy();
    });
});
