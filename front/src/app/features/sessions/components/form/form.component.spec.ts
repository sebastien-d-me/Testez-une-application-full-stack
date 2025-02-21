import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterTestingModule } from "@angular/router/testing";
import { SessionService } from "src/app/services/session.service";
import { SessionApiService } from "../../services/session-api.service";
import { FormComponent } from "./form.component";
import { Router } from "@angular/router";


describe("FormComponent", () => {
    let fixture: ComponentFixture<FormComponent>;
    let component: FormComponent;
    let router: Router;
    let serviceApi: SessionApiService;


    const mockSessionService = {
        sessionInformation: {
            admin: true
        }
    } 

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientModule,
                MatCardModule,
                MatIconModule,
                MatFormFieldModule,
                MatInputModule,
                ReactiveFormsModule, 
                MatSnackBarModule,
                MatSelectModule,
                BrowserAnimationsModule
            ],
            providers: [
                { 
                    provide: SessionService, 
                    useValue: mockSessionService 
                },
                SessionApiService
            ],
            declarations: [FormComponent]
        }).compileComponents();

        fixture = TestBed.createComponent(FormComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
        router = TestBed.inject(Router);
        serviceApi = TestBed.inject(SessionApiService);        
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });

    /// L'état d'erreur doit se mettre sur vrai si au moins un champ est vide
    it("should check that the error is triggered if at least one field is empty.", () => {
        component.sessionForm?.setValue({
            name: "",
            date: "",
            teacher_id: "",
            description: ""
        });

        component.submit();
      
        expect(component.sessionForm?.invalid).toBeTruthy();
    });


    /// L'état d'erreur doit se mettre sur faux si aucun n'est vide
    it("should check that the erorr is not triggered if all the fields are filled.", () => {
        component.sessionForm?.setValue({
            name: "test session",
            date: "2025-02-13 23:45:00",
            teacher_id: "1",
            description: "Lorem ipsum"
        });

        component.submit();
      
        expect(component.sessionForm?.invalid).toBeFalsy();
    });


    // Tests intégrations
    /// Vérifie que l'utilisateur est bien redirigé vers session s'il n'est pas un admin
    it("should check that the user if redirected to the session page if he's not a admin.", () => {
        const routerNavigate = jest.spyOn(router, "navigate");
        mockSessionService.sessionInformation.admin = false;

        component.ngOnInit();

        expect(routerNavigate).toHaveBeenCalledWith(["/sessions"]);
    });

    /// Vérifie que onUpdate passe à true si l'url contient "update"
    it("should check that onUpdate value is true if the URL contain 'update'.", () => {
        jest.spyOn(router, "url", "get").mockReturnValue("/sessions/update/1");

        component.ngOnInit();

        expect(component.onUpdate).toBeTruthy();
    });
});
