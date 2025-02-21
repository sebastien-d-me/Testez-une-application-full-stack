import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { By } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterTestingModule } from "@angular/router/testing";
import { expect } from "@jest/globals";
import { LoginComponent } from "./login.component";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { of } from "rxjs";
import { LoginRequest } from "../../interfaces/loginRequest.interface";


describe("LoginComponent", () => {
    let fixture: ComponentFixture<LoginComponent>;
    let component: LoginComponent;
    let router: Router;
    let authService: AuthService;

    const mockLogin: LoginRequest = {
        "email": "test@test.com",
        "password": "test123"
    }

    beforeEach(async () => {
        const mockDataAuth = {
            login: jest.fn().mockReturnValue(of(mockLogin))
        }

        await TestBed.configureTestingModule({
        declarations: [LoginComponent],
        providers: [
            LoginComponent, {
                provide: AuthService, useValue: mockDataAuth
            }
        ],
        imports: [
            RouterTestingModule,
            BrowserAnimationsModule,
            HttpClientModule,
            MatCardModule,
            MatIconModule,
            MatFormFieldModule,
            MatInputModule,
            ReactiveFormsModule]
        }).compileComponents();

        fixture = TestBed.createComponent(LoginComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
        router = TestBed.inject(Router);
        authService = TestBed.inject(AuthService);
    });

    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });

    /// Vérifie que par défaut, la valeur d'onError est fausse
    it("should check if onError variable value is false at start.", () => {
        expect(component.onError).toBeFalsy();
    });

    /// Vérifie que Le bouton d'envoi est désactivé au départ
    it("should check if the submit button is disabled at start.", () => {
        expect(component.form.invalid).toBeTruthy();
    });

    /// Vérifie que le mot de passe est caché au départ
    it("should check if the passowrd value is hidden at start.", () => {
        expect(component.hide).toBeTruthy();
    });

    /// Vérifie l'état d'erreur (doit être vrai) si tous les champs ne sont pas remplis
    it("should check if the error variable value is true if all the fields are not filled.", () => {
        component.form.setValue({
            email: "",
            password: ""
        });

        component.submit();
      
        expect(component.form.invalid).toBeTruthy();
    });

    /// Vérifie l'état d'erreur (doit être faux) si tous les champs ne sont pas remplis
    it("shouldn't trigger the error to false if all the fields are filled.", () => {
        component.form.setValue({
            email: "test@test.com",
            password: "test123"
        });

        component.submit();
      
        expect(component.form.invalid).toBeFalsy();
    });


    // Test d'intégrations
    /// Vérifie le bon fonctionnement de l'aperçu du mot de passe
    it("should toggle the password display.", () => {
        const buttonHide = fixture.debugElement.query(By.css("button[mat-icon-button]"));
        const iconHide = fixture.debugElement.query(By.css("mat-icon"));
        buttonHide.triggerEventHandler("click");
        
        expect(iconHide).toBeTruthy();
    });

    /// Vérifie qu'après la connexion, on est renvoyé sur la page des sessions
    it("should check that the user is sent to the sessions page after login.", () => {
        const routerNavigate = jest.spyOn(router, "navigate");

        component.form.setValue({
            email: "john.doe@studio.com",
            password: "test123"
        });

        component.submit();

        expect(routerNavigate).toHaveBeenCalledWith(["/sessions"]);
    });
});
