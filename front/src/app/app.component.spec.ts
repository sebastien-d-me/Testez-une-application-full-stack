import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterTestingModule } from "@angular/router/testing";
import { expect } from "@jest/globals";
import { AppComponent } from "./app.component";


describe("AppComponent", () => {
    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                HttpClientModule,
                MatToolbarModule
            ],
            declarations: [
                AppComponent
            ]
        }).compileComponents();
    });



    // Tests unitaires
    /// Vérifie que l'application est bien crée
    it("should create the app", () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;
        
        expect(app).toBeTruthy();
    });
});
