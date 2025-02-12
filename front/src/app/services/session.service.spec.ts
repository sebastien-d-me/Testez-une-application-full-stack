import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { SessionService } from "./session.service";


describe("SessionService", () => {
    let service: SessionService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(SessionService);
    });



    // Tests unitaires
    /// Vérifie que le service est bien crée
    it("should create", () => {
        expect(service).toBeTruthy();
    });
});
