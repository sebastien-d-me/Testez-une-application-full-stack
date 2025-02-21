import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { RouterTestingModule } from "@angular/router/testing";
import { SessionService } from "../../../../services/session.service";
import { DetailComponent } from "./detail.component";
import { SessionApiService } from "../../services/session-api.service";
import { of } from "rxjs";
import { Router } from "@angular/router";


describe("DetailComponent", () => {
    let fixture: ComponentFixture<DetailComponent>;
    let component: DetailComponent;
    let router: Router;
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
                { 
                    provide: SessionService, 
                    useValue: mockSessionService 
                },
                SessionApiService
            ],
        }).compileComponents();
        
        fixture = TestBed.createComponent(DetailComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
        router = TestBed.inject(Router);
        service = TestBed.inject(SessionService);
        serviceApi = TestBed.inject(SessionApiService);       
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });

    /// Vérifie que le retour en arrière est bien fonctionnel
    it("should check that the user is sent to the previous page.", () => {
        const checkGetBack = jest.spyOn(window.history, "back");
        component.back();

        expect(checkGetBack).toHaveBeenCalled();
    });


    // Tests intégrations
    /// Vérifie que la fonction de suppression de session est bien appelé
    it("should check if the delete session function is called.", () => {
        const checkDelete = jest.spyOn(serviceApi, "delete").mockReturnValue(of({}));
        component.delete();

        expect(checkDelete).toHaveBeenCalledWith(null);
    });

    /// Vérifie que la fonction d'ajout de participation est appelé
    it("should check if the add participation function is called.", () => {
        const checkParticipation = jest.spyOn(serviceApi, "participate").mockReturnValue(of(undefined));
        component.participate();

        expect(checkParticipation).toHaveBeenCalled();
    });

    /// Vérifie que la fonction de suppression de participation est appelé
    it("should check if the remove of participation function is called.", () => {
        const checkRemoveParticipation = jest.spyOn(serviceApi, "unParticipate").mockReturnValue(of(undefined));
        component.unParticipate();

        expect(checkRemoveParticipation).toHaveBeenCalled();
    });
});

