import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RegisterComponent } from "./register.component";
import { Router } from "@angular/router";
import { AuthService } from "../../services/auth.service";
import { of } from "rxjs";
import { RegisterRequest } from "../../interfaces/registerRequest.interface";


describe("RegisterComponent", () => {
    let fixture: ComponentFixture<RegisterComponent>;
    let component: RegisterComponent;
    let router: Router;
    let authService: AuthService;
    
    const mockRegister: RegisterRequest = {
        "email": "test@test.com",
        "firstName": "John",
        "lastName": "DOE",
        "password": "test123"
    }

    beforeEach(async () => {
        const mockDataAuth = {
            register: jest.fn().mockReturnValue(of(mockRegister))
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

    // Vérifie si par défaut la vérification d'erreur est fausse
    it("should check if onError value is false at start.", () => {
        expect(component.onError).toBeFalsy();
    });

    /// Vérifie que l'erreur se mette bien si au moins un champ n'est pas remplit
    it("should check if the error trigger if at least one field is empty.", () => {
        component.form.setValue({
            email: "",
            firstName: "",
            lastName: "",
            password: ""
        });

        component.submit();
      
        expect(component.form.invalid).toBeTruthy();
    });

    /// Vérifie que l'erreur ne se mette pas si tous les champs sont remplis
    it("should check if the error don't trigger if all the fields are filled.", () => {
        component.form.setValue({
            email: "test@test.com",
            firstName: "John",
            lastName: "DOE",
            password: "test123"
        });

        component.submit();
      
        expect(component.form.invalid).toBeFalsy();
    });
    

    // Test d'intégrations
    /// Vérifie qu'on est bien renvoyé à la page de login après l'isncription
    it("should check that the user is sent to the login page after registration.", () => {
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
