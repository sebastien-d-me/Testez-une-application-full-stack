describe("Me spec", () => {
    it("Get back to the previous page", () => {
        cy.visit("/login");

        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                username: "userName",
                firstName: "firstName",
                lastName: "lastName",
                admin: true
            }
        });

        cy.intercept({
            method: "GET",
            url: "/api/session"
        }, []).as("session");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);
        
        cy.get("span").contains("Account").click();   
        cy.wait(1000); 
        cy.get("button.mat-focus-indicator.mat-icon-button.mat-button-base").click();
    
        cy.url().should("include", "/sessions");
    });
});