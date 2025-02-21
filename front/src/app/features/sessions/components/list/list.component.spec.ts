import { HttpClientModule } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { SessionService } from "src/app/services/session.service";
import { ListComponent } from "./list.component";


describe("ListComponent", () => {
    let component: ListComponent;
    let fixture: ComponentFixture<ListComponent>;

    const mockSessionService = {
        sessionInformation: {
            admin: true
        }
    }

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ListComponent],
            imports: [HttpClientModule, MatCardModule, MatIconModule],
            providers: [{ 
                provide: SessionService, 
                useValue: mockSessionService 
            }]
        }).compileComponents();

        fixture = TestBed.createComponent(ListComponent);
        fixture.detectChanges();
        component = fixture.componentInstance;
    });


    /** Tests **/
    // Tests unitaires
    /// Vérifie que le composant existe bien
    it("should check that the component exist.", () => {
        expect(component).toBeTruthy();
    });
});
