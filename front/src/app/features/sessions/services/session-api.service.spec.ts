import { HttpClientModule } from "@angular/common/http";
import { HttpTestingController, HttpClientTestingModule } from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
import { expect } from "@jest/globals";
import { SessionApiService } from "./session-api.service";
import { Session } from "../interfaces/session.interface";


describe("SessionsService", () => {
    let service: SessionApiService;
    let httpMock: HttpTestingController;

    const mockSessions = [
        {
            id: "1",
            name: "Nom de la session",
            description: "Description de la session",
            date: "2024-01-14 11:00:00",
            teacher_id: "1",
            created_at: "2024-01-12 17:00:00",
            updated_at: "2024-01-13 09:00:00"
        },
        {
            id: "2",
            name: "Nom de la session 2",
            description: "Description de la session 2",
            date: "2025-02-15 12:00:00",
            teacher_id: "2",
            created_at: "2025-02-13 18:00:00",
            updated_at: "2025-02-14 10:00:00"
        }
    ];

    const mockSessionInterface: Session = {
        id: 1,
        name: "Nom de la session",
        description: "Description de la session",
        date: new Date("2024-01-14 11:00:00"),
        teacher_id: 1,
        users: [2],
        createdAt: new Date("2024-01-12 17:00:00"),
        updatedAt: new Date("2024-01-13 09:00:00")
    };

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [
                HttpClientModule,
                HttpClientTestingModule
            ],
            providers: [SessionApiService]
        });

        service = TestBed.inject(SessionApiService);
        httpMock = TestBed.inject(HttpTestingController);
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le service existe bien
    it("should check that the service exist.", () => {
        expect(service).toBeTruthy();
    });


    // Tests intégrations
    /// Le service doit être une requête GET et récupérer les informations de toutes les sessions
    it("should GET (request) the details of all the sessions.", () => {
        service.all().subscribe(sessionData => {
            expect(sessionData).toEqual(mockSessions);
        });

        const req = httpMock.expectOne("api/session");
        expect(req.request.method).toBe("GET");
        req.flush(mockSessions);
    });

    /// Le service doit être une requête GET et récupérer les informations d'une session
    it("should GET (request) the details of a specific session.", () => {
        service.detail("1").subscribe(sessionData => {
            expect(sessionData).toEqual(mockSessions);
        });

        const req = httpMock.expectOne("api/session/1");
        expect(req.request.method).toBe("GET");
        req.flush(mockSessions);
    });

    /// Le service doit être une requête DELETE et supprimer la session
    it("should DELETE (request) a session.", () => {
        service.delete("1").subscribe(sessionData => {
            expect(sessionData).toBeUndefined();
        });

        const req = httpMock.expectOne("api/session/1");
        expect(req.request.method).toBe("DELETE");
    });

    /// Le service doit être une requête CREATE et crée la session
    it("should CREATE (request) a session", () => {
        service.create(mockSessionInterface).subscribe(sessionData => {
            expect(sessionData).toEqual(mockSessions);
        });

        const req = httpMock.expectOne("api/session");
        expect(req.request.method).toBe("POST");
        req.flush(mockSessions);
    });

    /// Le service doit être une requête PUT et mettre à jour la session
    it("should PUT (request) the update of a session", () => {
        service.update("1", mockSessionInterface).subscribe(sessionData => {
            expect(sessionData).toEqual(mockSessions);
        });

        const req = httpMock.expectOne("api/session/1");
        expect(req.request.method).toBe("PUT");
        req.flush(mockSessions);
    });

    /// Le service doit être une requête POST et mettre à jour la participation
    it("should POST (request) the participation of a session", () => {
        service.participate("1", "2").subscribe(sessionData => {
            expect(sessionData).toBeUndefined();
        });

        const req = httpMock.expectOne("api/session/1/participate/2");
        expect(req.request.method).toBe("POST");
        req.flush(mockSessions);
    });

    /// Le service doit être une requête DELETE et mettre à jour la participation
    it("should DELETE (request) the participation of a session", () => {
        service.unParticipate("1", "2").subscribe(sessionData => {
            expect(sessionData).toBeUndefined();
        });

        const req = httpMock.expectOne("api/session/1/participate/2");
        expect(req.request.method).toBe("DELETE");
    });


    // Fin des tests 
    afterEach(() => {
        httpMock.verify();
    });
});
