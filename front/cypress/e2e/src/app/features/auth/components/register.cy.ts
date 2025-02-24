describe("Register spec", () => {
    it("Register successfull", () => {
        cy.visit("/register");
    
        cy.intercept("POST", "/api/auth/register", {
            body: {
                lastName: "DOE",
                firstName: "John",
                email: "john.doe@test.com",
                password: "test123!"
            }
        });
    
        cy.intercept({
            method: "GET",
            url: "/api/login",
        }, []).as("login");
    
        cy.get("input[formControlName=firstName]").type("DOE");
        cy.get("input[formControlName=lastName]").type("John");
        cy.get("input[formControlName=email]").type("john.doe@test.com");
        cy.get("input[formControlName=password]").type(`${"test123!"}{enter}{enter}`);
    
        cy.url().should("include", "/login");
    })
});