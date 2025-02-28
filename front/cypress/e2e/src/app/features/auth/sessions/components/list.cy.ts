// SRC/APP/FEATURES/AUTH/SESSIONS/COMPONENT - LIST
describe("List component", () => {
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

        cy.intercept("POST", "/api/session", {
            body: {
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum"
            }
        });

        cy.intercept({
            method: "GET",
            url: "/api/session"
        }, {
            statusCode: 200,
            body: [{
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum"
            }]
        });
    });

    

    // Test de la liste des sessions
    it("List all the session", () => {
        cy.visit("/login");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.url().should("include", "/sessions");

        cy.get(".mat-card-title").should("be.visible").and("contain", "Session 1");
        cy.get(".mat-card-subtitle").should("be.visible").and("contain", "Session on February 26, 2025");
        cy.get(".mat-card-content > p").should("be.visible").and("contain", "Lorem ipsum");
    });
});