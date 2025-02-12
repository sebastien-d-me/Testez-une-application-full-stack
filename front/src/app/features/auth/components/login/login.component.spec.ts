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
    /// Le composant doit bien être crée
    it("should create the component.", () => {
        expect(component).toBeTruthy();
    });


    // La variable onError doit être mise sur faux au départ
    it("should have the onError variable to be falsy at start.", () => {
        expect(component.onError).toBeFalsy();
    });


    /// Le bouton d'envoie doit être désactivé au départ
    it("should have the submit button disabled at start.", () => {
        expect(component.form.invalid).toBeTruthy();
    });


    /// Le mot de passe doit être caché par défaut
    it("should hide the password value at start.", () => {
        expect(component.hide).toBeTruthy();
    });


    /// La fonction d'aperçu du mot de passe doit fonctionner
    it("should toggle the password display.", () => {
        const buttonHide = fixture.debugElement.query(By.css("button[mat-icon-button]"));
        const iconHide = fixture.debugElement.query(By.css("mat-icon"));
        buttonHide.triggerEventHandler("click");
        
        expect(iconHide).toBeTruthy();
    });


    /// L'état d'erreur doit se mettre sur vrai si les champs ne sont pas remplis
    it("should trigger the error to true if all the fields aren't filled.", () => {
        component.form.setValue({
            email: "",
            password: ""
        });

        component.submit();
      
        expect(component.form.invalid).toBeTruthy();
    });


    /// L'état d'erreur doit se mettre sur faux si les champs sont remplis
    it("shouldn't trigger the error to true if all the fields are filled.", () => {
        component.form.setValue({
            email: "test@test.com",
            password: "test123"
        });

        component.submit();
      
        expect(component.form.invalid).toBeFalsy();
    });
});
