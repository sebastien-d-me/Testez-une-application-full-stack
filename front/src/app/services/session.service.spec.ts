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
    /// Le service doit bien être crée
    it("should create the service.", () => {
        expect(service).toBeTruthy();
    });
});
