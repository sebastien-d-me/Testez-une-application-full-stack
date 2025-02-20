import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { SessionService } from "src/app/services/session.service";
import { MeComponent } from "./me.component";
import { of } from "rxjs";
import { UserService } from "src/app/services/user.service";


describe("MeComponent", () => {
    let component: MeComponent;
    let fixture: ComponentFixture<MeComponent>;
    let userService: UserService;

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
            },
            UserService
        ],
        }).compileComponents();

        userService = TestBed.inject(UserService);
        fixture = TestBed.createComponent(MeComponent);
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




    // Tests Intégrations
    /// L'user doit être supprimé
    it("should call the delete user function", () => {
        const checkDelete = jest.spyOn(userService, "delete").mockReturnValue(of({}));
    
        component.delete();
    
        expect(checkDelete).toHaveBeenCalledWith("1");
    });
});
