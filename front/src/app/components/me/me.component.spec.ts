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
    let fixture: ComponentFixture<MeComponent>;
    let component: MeComponent;
    let userService: UserService;

    const mockSessionService = {
        sessionInformation: {
            id: 1
        }
    };

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [MeComponent],
            imports: [
                HttpClientModule,
                MatCardModule,
                MatFormFieldModule,
                MatIconModule,
                MatInputModule,
                MatSnackBarModule
            ],
            providers: [
                { 
                    provide: SessionService, 
                    useValue: mockSessionService 
                }, 
                UserService
            ],
        }).compileComponents();

        fixture = TestBed.createComponent(MeComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
        userService = TestBed.inject(UserService);
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });

    /// Vérifie que le retour en arrière est bien fonctionnel
    it("should send the user to the previous page", () => {
        const checkPreviousPage = jest.spyOn(window.history, "back");
        component.back();

        expect(checkPreviousPage).toHaveBeenCalled();
    })


    // Tests Intégrations
    /// Vérifie que la fonction de suppression d'un utilisateur est appelé avec l'ID
    it("should call the delete user function with his ID", () => {
        const checkDelete = jest.spyOn(userService, "delete").mockReturnValue(of({}));
        component.delete();

        expect(checkDelete).toHaveBeenCalledWith("1");
    });
});
