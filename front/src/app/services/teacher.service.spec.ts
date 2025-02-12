import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { TeacherService } from "./teacher.service";


describe("TeacherService", () => {
    let service: TeacherService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports:[
                HttpClientModule
            ]
        });
        service = TestBed.inject(TeacherService);
    });



    // Tests unitaires
    /// Vérifie que le service est bien crée
    it("should create", () => {
        expect(service).toBeTruthy();
    });
});
