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


describe("FormComponent", () => {
    let component: FormComponent;
    let fixture: ComponentFixture<FormComponent>;

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
        component = fixture.componentInstance;
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
});
