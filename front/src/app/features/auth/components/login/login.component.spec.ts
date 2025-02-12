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
import { SessionService } from "src/app/services/session.service";
import { LoginComponent } from "./login.component";


describe("LoginComponent", () => {
    let component: LoginComponent;
    let fixture: ComponentFixture<LoginComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
        declarations: [LoginComponent],
        providers: [SessionService],
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
        component = fixture.componentInstance;
        fixture.detectChanges();
    });


    
    // Tests unitaires
    /// Vérifie que le composant est bien crée
    it("should create", () => {
        expect(component).toBeTruthy();
    });


    /// Vérifie que par défaut le mot de passe est bien caché
    it("should hide the password at start", () => {
        expect(component.hide).toBeTruthy();
    });


    /// Vérifie que l"icone du mot d"aperçu de mot de passe se change bien
    it("should toggle the password display", () => {
        const buttonHide = fixture.debugElement.query(By.css("button[mat-icon-button]"));
        const iconHide = fixture.debugElement.query(By.css("mat-icon"));
        buttonHide.triggerEventHandler("click");
        
        expect(iconHide).toBeTruthy();
    });


    /// Vérifie que l"erreur se mette bien si es champs sont vides
    it("should get back", () => {
        component.form.setValue({
            email: "",
            password: ""
        });
      
        expect(component.form.invalid).toBeTruthy();
    });
});
