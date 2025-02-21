import { ComponentFixture, TestBed } from "@angular/core/testing";
import { NotFoundComponent } from "./not-found.component";


describe("NotFoundComponent", () => {
    let fixture: ComponentFixture<NotFoundComponent>;
    let component: NotFoundComponent;
    
    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [NotFoundComponent]
        }).compileComponents();

        fixture = TestBed.createComponent(NotFoundComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });
});
