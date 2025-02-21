import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { TeacherService } from "./teacher.service";


describe("TeacherService", () => {
    let service: TeacherService;
    let httpMock: HttpTestingController;

    const mockTeachers = [
        {
            id: "1",
            last_name: "DOE",
            first_name: "John",
            created_at: "2024-01-12 17:00:00",
            updated_at: "2024-01-13 09:00:00"
        },
        {
            id: "2",
            last_name: "DUPONT",
            first_name: "Jean",
            created_at: "2025-02-13 18:00:00",
            updated_at: "2025-02-14 10:00:00"
        }
    ];
    
    const mockTeacher = {
        id: "1",
        last_name: "DOE",
        first_name: "John",
        created_at: "2024-01-12 17:00:00",
        updated_at: "2024-01-13 09:00:00"
    };

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports:[
                HttpClientModule,
                HttpClientTestingModule
            ]
        });

        service = TestBed.inject(TeacherService);
        httpMock = TestBed.inject(HttpTestingController);
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le service existe bien
    it("should check that the service exist.", () => {
        expect(service).toBeTruthy();
    });


    // Tests intégrations
    /// Le service doit être une requête GET et récupérer les informations des professeurs
    it("should GET (request) the details of all the teachers.", () => {
        service.all().subscribe(sessionData => {
            expect(sessionData).toEqual(mockTeachers);
        });

        const req = httpMock.expectOne("api/teacher");
        expect(req.request.method).toBe("GET");
        req.flush(mockTeachers);
    });

    /// Le service doit être une requête GET et récupérer les informations d'un professeur
    it("should GET (request) the details of a specific teacher.", () => {
        service.detail("1").subscribe(sessionData => {
            expect(sessionData).toEqual(mockTeacher);
        });

        const req = httpMock.expectOne("api/teacher/1");
        expect(req.request.method).toBe("GET");
        req.flush(mockTeacher);
    });


    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
