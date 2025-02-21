import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterTestingModule } from "@angular/router/testing";
import { AppComponent } from "./app.component";
import { SessionService } from "./services/session.service";


describe("AppComponent", () => {
    let service: SessionService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientModule,
                MatToolbarModule
            ],
            declarations: [
                AppComponent
            ],
            providers: [SessionService]
        }).compileComponents();

        service = TestBed.inject(SessionService);
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que l'application existe bien
    it("should check that it create the app.", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;
        
        expect(app).toBeTruthy();
    });


    // Tests intégrations
    /// L'observable doit nous retourner la valeur de isLogged (false)
    it("should check that isLogged observable is returned (to false)", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;

        app.$isLogged().subscribe(sessionIsLogged => {
            expect(sessionIsLogged).toBeFalsy();
        });
    });

    /// La déconnexion doit passer la variable isLogged à false et renvoyé à l'accueil
    it("should check that the value of isLogged is changed (to false) after logout and send the user to home", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;

        const checkGetHome = jest.spyOn(service, "logOut");

        app.logout();

        expect(checkGetHome).toHaveBeenCalled();
    });
});
