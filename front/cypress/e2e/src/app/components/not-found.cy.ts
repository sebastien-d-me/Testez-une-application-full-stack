describe("Not found spec", () => {
    it("Get back to the 404 page", () => {
        cy.visit("/login");

        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 2,
                email: "john.doe@test.com",
                firstName: "John",
                lastName: "DOE",
                admin: false
            }
        });

        cy.get("input[formControlName=email]").type("john.doe@test.com");
        cy.get("input[formControlName=password]").type(`${"test123!"}{enter}{enter}`);
        
        cy.visit("/test");

        cy.wait(100); 
    
        cy.url().should("include", "/404"); 
        cy.get("h1").should("be.visible").and("contain", "Page not found !");
    });
});