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
    /// Le service doit bien être crée
    it("should create the service.", () => {
        expect(service).toBeTruthy();
    });
});
