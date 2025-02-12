import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { SessionService } from "src/app/services/session.service";
import { MeComponent } from "./me.component";


describe("MeComponent", () => {
    let component: MeComponent;
    let fixture: ComponentFixture<MeComponent>;

    const mockSessionService = {
        sessionInformation: {
            admin: true,
            id: 1
        }
    }
    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [MeComponent],
            imports: [
                MatSnackBarModule,
                HttpClientModule,
                MatCardModule,
                MatFormFieldModule,
                MatIconModule,
                MatInputModule
            ],
            providers: [{ 
                provide: SessionService, 
                useValue: mockSessionService 
            }],
        }).compileComponents();

        fixture = TestBed.createComponent(MeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });



    // Tests unitaires
    /// Vérifie que le composant est bien crée
    it("should create", () => {
        expect(component).toBeTruthy();
    });

    
    /// Vérifie que ça retourne bien en arrière
    it("should get back", () => {
        const checkGetBack = jest.spyOn(window.history, "back");
        component.back();

        expect(checkGetBack).toHaveBeenCalled();
    });
});
