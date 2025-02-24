describe("Me spec", () => {
    it("Get back to the previous page", () => {
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

        cy.get("span").contains("Account").click();   
        cy.wait(100); 
        cy.get("button.mat-focus-indicator.mat-icon-button.mat-button-base").click();
    
        cy.url().should("include", "/sessions"); 
    });

    it("Show the user details", () => {
        cy.intercept("GET", "/api/user/2", {
            body: {
                id: 2,
                email: "john.doe@test.com",
                firstName: "John",
                lastName: "DOE",
                admin: false
            }
        }).as("meDetails");

        cy.get("span").contains("Account").click();   
        cy.wait("@meDetails");

        cy.get("p").contains("Name:").should("be.visible").and("contain", "John DOE");
        cy.get("p").contains("Email:").should("be.visible").and("contain", "john.doe@test.com");
    });
});