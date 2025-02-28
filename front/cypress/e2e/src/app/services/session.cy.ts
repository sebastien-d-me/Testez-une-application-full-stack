// SRC/APP/SERVICES - SESSION
describe("Session service", () => {
    // Requêtes
    before(() => {
        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                lastName: "Studio",
                firstName: "Yoga",
                admin: true
            }
        });
    });


    
    // Test de déconnexion
    it("Should logout successfull", () => {
        cy.visit("/login");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.url().should("include", "/sessions");

        cy.get(".link").contains("Logout").click();
    });
});