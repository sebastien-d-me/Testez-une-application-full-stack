// SRC/APP/COMPONENTS - NOT FOUND
describe("Not found component", () => {
    // Test du renvoie vers la page 404
    it("Should send to the 404 page", () => {       
        cy.visit("/test");

        cy.url().should("include", "/404"); 
        
        cy.get("h1").should("be.visible").and("contain", "Page not found !");
    });
});