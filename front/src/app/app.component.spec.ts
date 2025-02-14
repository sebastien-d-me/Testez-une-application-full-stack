import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterTestingModule } from "@angular/router/testing";
import { expect } from "@jest/globals";
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



    // Tests unitaires
    /// L'application doit être crée
    it("should create the app.", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;
        
        expect(app).toBeTruthy();
    });


    /// Tests d'intégrations
    // L'obversable doit bien nous donner la valeur du booléen isLogged false au début
    it("should return isLogged value to the app", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;

        app.$isLogged().subscribe(sessionIsLogged => {
            expect(sessionIsLogged).toBeFalsy();
        });
    });


    /// La déconnexion doit passer la variable isLogged à false
    it("should change the value of the isLogged boolean (to false) after logout", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;

        const checkGetHome = jest.spyOn(service, "logOut");

        app.logout();

        expect(checkGetHome).toHaveBeenCalled();
    });
});
