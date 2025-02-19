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
import { expect } from "@jest/globals";
import { SessionService } from "src/app/services/session.service";
import { SessionApiService } from "../../services/session-api.service";
import { FormComponent } from "./form.component";
import { Router } from "@angular/router";
import { Session } from "../../interfaces/session.interface";
import { of } from "rxjs";


describe("FormComponent", () => {
    let component: FormComponent;
    let fixture: ComponentFixture<FormComponent>;
    let router: Router;
    let serviceApi: SessionApiService;


    const mockSessionService = {
        sessionInformation: {
            admin: true
        }
    } 

    const mockDataInterface: Session = {
            id: 1,
            name: "Nom de la session",
            description: "Description de la session",
            date: new Date("2024-01-14 11:00:00"),
            teacher_id: 1,
            users: [2],
            createdAt: new Date("2024-01-12 17:00:00"),
            updatedAt: new Date("2024-01-13 09:00:00")
        };

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
        serviceApi = TestBed.inject(SessionApiService);
        component = fixture.componentInstance;
        router = TestBed.inject(Router);
        fixture.detectChanges();
    });



    // Tests unitaires
    /// Le composant doit bien être crée
    it("should create the component.", () => {
        expect(component).toBeTruthy();
    });


    /// L'état d'erreur doit se mettre sur vrai si les champs ne sont pas remplis
    it("should trigger the error to true if all the fields aren't filled.", () => {
        component.sessionForm?.setValue({
            name: "",
            date: "",
            teacher_id: "",
            description: ""
        });

        component.submit();
      
        expect(component.sessionForm?.invalid).toBeTruthy();
    });


    /// L'état d'erreur doit se mettre sur faux si les champs sont remplis
    it("shouldn't trigger the error to true if all the fields are filled.", () => {
        component.sessionForm?.setValue({
            name: "test session",
            date: "2025-02-13 23:45:00",
            teacher_id: "1",
            description: "Lorem ipsum"
        });

        component.submit();
      
        expect(component.sessionForm?.invalid).toBeFalsy();
    });



    // Tests Intégrations
    /// Doit rediriger vers session si l'utilisateur n'est pas un admin
    it("should redirect to the session if the user is not admin", () => {
        const routerNavigate = jest.spyOn(router, "navigate");
        mockSessionService.sessionInformation.admin = false;

        component.ngOnInit();

        expect(routerNavigate).toHaveBeenCalledWith(["/sessions"]);
    });


    /// Les variables doivent prendre des valeurs si l'url inclut update
    it("should add the correct value to the variable if the url contains 'update'", () => {
        jest.spyOn(router, "url", "get").mockReturnValue("/sessions/update/1");

        component.ngOnInit();

        expect(component.onUpdate).toBeTruthy();
    });
});
