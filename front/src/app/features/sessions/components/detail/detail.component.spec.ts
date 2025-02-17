import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { RouterTestingModule, } from "@angular/router/testing";
import { expect } from "@jest/globals"; 
import { SessionService } from "../../../../services/session.service";
import { DetailComponent } from "./detail.component";
import { SessionApiService } from "../../services/session-api.service";
import { of } from "rxjs";


describe("DetailComponent", () => {
    let component: DetailComponent;
    let fixture: ComponentFixture<DetailComponent>; 
    let service: SessionService;
    let serviceApi: SessionApiService;

    const mockSessionService = {
        sessionInformation: {
            admin: true,
            id: 1
        }
    }

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientModule,
                MatSnackBarModule,
                ReactiveFormsModule
            ],
            declarations: [DetailComponent], 
            providers: [
                { provide: SessionService, useValue: mockSessionService },
                SessionApiService
            ],
        }).compileComponents();
        
        service = TestBed.inject(SessionService);
        serviceApi = TestBed.inject(SessionApiService);
        fixture = TestBed.createComponent(DetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });



    // Tests unitaires
    /// Le composant doit bien être crée
    it("should create the component.", () => {
        expect(component).toBeTruthy();
    });


    /// La fonction doit correctement renvoyer en arrière
    it("should send back the user.", () => {
        const checkGetBack = jest.spyOn(window.history, "back");
        component.back();
        expect(checkGetBack).toHaveBeenCalled();
    });



    // Tests Intégrations
    /// Le service doit appeler la fonction de participation
    it("should POST (request) the participation of a session", () => {
        const checkParticipation = jest.spyOn(serviceApi, "participate").mockReturnValue(of(undefined));
        
        component.participate();

        expect(checkParticipation).toHaveBeenCalled();
    });


    /// Le service doit appeler la fonction de désinscription de participation
    it("should DELETE (request) the unregistration of participation of a session", () => {
        const checkRemoveParticipation = jest.spyOn(serviceApi, "unParticipate").mockReturnValue(of(undefined));
        
        component.unParticipate();

        expect(checkRemoveParticipation).toHaveBeenCalled();
    });
});

