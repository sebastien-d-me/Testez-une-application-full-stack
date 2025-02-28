// SRC/APP/COMPONENTS - ME
describe("Me component", () => {
    // Test du retour vers la page précédente
    it("Should get back to the previous page", () => {
        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                lastName: "Studio",
                firstName: "Yoga",
                admin: true
            }
        });

        cy.visit("/login");
        
        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.get("span").contains("Account").click();    
        cy.get("button.mat-focus-indicator.mat-icon-button.mat-button-base").click();
    
        cy.url().should("include", "/sessions"); 
    });



    // Test de récupération des infos d'un utilisateur spécfiique
    it("Should show the details of a specific user", () => {
        cy.intercept("GET", "/api/user/1", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                lastName: "Studio",
                firstName: "Yoga",
                admin: true,
                createdAt: new Date("2025-02-27 12:00:00"),
                updatedAt: new Date("2025-02-28 13:00:00")
            }
        }).as("meDetails");

        cy.get("span").contains("Account").click();   
        
        cy.wait("@meDetails");

        cy.get("p").contains("Name:").should("be.visible").and("contain", "Yoga STUDIO");
        cy.get("p").contains("Email:").should("be.visible").and("contain", "yoga@studio.com");
    });
});