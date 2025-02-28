// SRC/APP/COMPONENTS - ME
describe("Me component", () => {
    // Test du retour vers la page précédente
    it("Should get back to the previous page", () => {
        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 2,
                email: "john.doe@test.com",
                lastName: "DOE",
                firstName: "John",
                admin: false,
                createdAt: new Date("2025-02-27 12:00:00"),
            }
        });

        cy.visit("/login");
        
        cy.get("input[formControlName=email]").type("john.doe@test.com");
        cy.get("input[formControlName=password]").type(`${"test123!"}{enter}{enter}`);

        cy.get("span").contains("Account").click();    
        cy.get("button.mat-focus-indicator.mat-icon-button.mat-button-base").click();
    
        cy.url().should("include", "/sessions"); 
    });


    
    // Test de récupération des infos d'un utilisateur spécfiique
    it("Show the user details", () => {
        cy.intercept("GET", "/api/user/2", {
            body: {
                id: 2,
                email: "john.doe@test.com",
                lastName: "DOE",
                firstName: "John",
                admin: false,
                createdAt: new Date("2025-02-27 12:00:00"),
                updatedAt: new Date("2025-02-28 13:00:00")
            }
        }).as("meDetails");

        cy.get("span").contains("Account").click();   
        
        cy.wait("@meDetails");

        cy.get("p").contains("Name:").should("be.visible").and("contain", "John DOE");
        cy.get("p").contains("Email:").should("be.visible").and("contain", "john.doe@test.com");
    });
});