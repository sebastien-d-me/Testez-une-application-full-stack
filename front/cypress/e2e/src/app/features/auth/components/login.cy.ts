// SRC/APP/FEATURES/AUTH/COMPONENTS - LOGIN
describe("Login component", () => {
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


    
    // Test de la connexion
    it("Should login successfull", () => {
        cy.visit("/login");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.url().should("include", "/sessions");
    });
});