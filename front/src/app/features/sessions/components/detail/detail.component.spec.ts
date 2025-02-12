import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ReactiveFormsModule } from "@angular/forms";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { RouterTestingModule, } from "@angular/router/testing";
import { expect } from "@jest/globals"; 
import { SessionService } from "../../../../services/session.service";
import { DetailComponent } from "./detail.component";


describe("DetailComponent", () => {
    let component: DetailComponent;
    let fixture: ComponentFixture<DetailComponent>; 
    let service: SessionService;

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
            providers: [{ provide: SessionService, useValue: mockSessionService }],
        }).compileComponents();
        
        service = TestBed.inject(SessionService);
        fixture = TestBed.createComponent(DetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });



    // Tests unitaires
    /// Le composant doit bien être crée
    it("should create the component.", () => {
        expect(component).toBeTruthy();
    });


    /// La fonction doit correctement renvoyer en arrière
    it("should send back the user.", () => {
        const checkGetBack = jest.spyOn(window.history, "back");
        component.back();
        expect(checkGetBack).toHaveBeenCalled();
    });
});

