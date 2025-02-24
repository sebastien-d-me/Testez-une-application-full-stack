describe("Register spec", () => {
    it("Register successfull", () => {
        cy.visit("/register");
    
        cy.intercept("POST", "/api/auth/register", {
            body: {
                lastName: "lastName",
                firstName: "firstName",
                email: "email",
                password: "password"
            }
        });
    
        cy.intercept({
            method: "GET",
            url: "/api/login",
        }, []).as("login");
    
        cy.get("input[formControlName=firstName]").type("DOE");
        cy.get("input[formControlName=lastName]").type("John");
        cy.get("input[formControlName=email]").type("test@test.com");
        cy.get("input[formControlName=password]").type(`${"test123!"}{enter}{enter}`);
    
        cy.url().should("include", "/login");
    })
});