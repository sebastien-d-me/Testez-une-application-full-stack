import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { expect } from "@jest/globals";
import { RegisterComponent } from "./register.component";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { of } from "rxjs";
import { RegisterRequest } from "../../interfaces/registerRequest.interface";


describe("RegisterComponent", () => {
    let component: RegisterComponent;
    let authService: AuthService;
    let fixture: ComponentFixture<RegisterComponent>;
    let router: Router;

    const mockData: RegisterRequest = {
        "email": "test@test.com",
        "firstName": "John",
        "lastName": "DOE",
        "password": "test123"
    }

    beforeEach(async () => {
        const mockDataAuth = {
            register: jest.fn().mockReturnValue(of(mockData))
        }

        await TestBed.configureTestingModule({
            declarations: [RegisterComponent],
            providers: [
                RegisterComponent, {
                    provide: AuthService, useValue: mockDataAuth
                }
            ],
            imports: [
                BrowserAnimationsModule,
                HttpClientModule,
                ReactiveFormsModule,  
                MatCardModule,
                MatFormFieldModule,
                MatIconModule,
                MatInputModule
            ]
        }).compileComponents();

        fixture = TestBed.createComponent(RegisterComponent);
        component = fixture.componentInstance;
        router = TestBed.inject(Router);
        authService = TestBed.inject(AuthService);
        fixture.detectChanges();
    });



    // Tests unitaires
    /// Le composant doit bien être crée
    it("should create the component.", () => {
        expect(component).toBeTruthy();
    });


    // Vérifie si par défaut la vérification d'erreur est fausse
    it("should check if onError is false at start", () => {
        expect(component.onError).toBeFalsy();
    });


    /// Vérifie que l'erreur se mette bien si les champs sont vides
    it("should check if the error trigger if the fields are empty", () => {
        component.form.setValue({
            email: "",
            firstName: "",
            lastName: "",
            password: ""
        });

        component.submit();
      
        expect(component.form.invalid).toBeTruthy();
    });


    /// Vérifie que l'erreur ne se mette pas si les champs sont remplis
    it("should check if the error don't trigger if the fields aren't empty", () => {
        component.form.setValue({
            email: "test@test.com",
            firstName: "John",
            lastName: "DOE",
            password: "test123"
        });

        component.submit();
      
        expect(component.form.invalid).toBeFalsy();
    });
    


    /// Test d'intégrations
    /// La connexion doit renvoyé à la page des sessions
    it("should send to the sessions page after login", () => {
        const routerNavigate = jest.spyOn(router, "navigate");

        component.form.setValue({
            email: "test@test.com",
            firstName: "John",
            lastName: "DOE",
            password: "test123"
        });

            
        component.submit();

        expect(routerNavigate).toHaveBeenCalledWith(["/login"]);
    });
});
