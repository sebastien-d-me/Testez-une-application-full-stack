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
import { Router } from "@angular/router";


describe("DetailComponent", () => {
    let component: DetailComponent;
    let fixture: ComponentFixture<DetailComponent>; 
    let service: SessionService;
    let serviceApi: SessionApiService;
    let router: Router;

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
        router = TestBed.inject(Router);
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
    /// La session doit être supprimé
    it("should delete the session", () => {
        const checkDelete = jest.spyOn(serviceApi, "delete").mockReturnValue(of({}));

        component.delete();

        expect(checkDelete).toHaveBeenCalledWith(null);
    });


    /// Le service doit appeler la fonction de participation
    it("should add the participation of a session", () => {
        const checkParticipation = jest.spyOn(serviceApi, "participate").mockReturnValue(of(undefined));

        component.participate();

        expect(checkParticipation).toHaveBeenCalled();
    });


    /// Le service doit appeler la fonction de désinscription de participation
    it("should remove the unregistration of participation of a session", () => {
        const checkRemoveParticipation = jest.spyOn(serviceApi, "unParticipate").mockReturnValue(of(undefined));
        
        component.unParticipate();

        expect(checkRemoveParticipation).toHaveBeenCalled();
    });
});

