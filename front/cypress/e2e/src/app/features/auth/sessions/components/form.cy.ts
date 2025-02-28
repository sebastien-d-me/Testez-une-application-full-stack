// SRC/APP/FEATURES/AUTH/SESSIONS/COMPONENT - FORM
describe("Form component", () => {
    // Requêtes
    beforeEach(() => {
        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                firstName: "Yoga",
                lastName: "Studio",
                admin: true
            }
        });

        cy.intercept({
            method: "GET",
            url: "/api/teacher"
        }, {
            statusCode: 200,
            body: [
                {
                    id: 1,
                    lastName: "DELAHAYE",
                    firstName: "Margot",
                    createdAt: "2025-02-06T16:55:49",
                    updatedAt: "2025-02-06T16:55:49"
                }, {
                    id: 2,
                    lastName: "THIERCELIN",
                    firstName: "Hélène",
                    createdAt: "2025-02-06T16:55:49",
                    updatedAt: "2025-02-06T16:55:49"
                }
            ]
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
                id: 1,
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum"
            }]
        });

        cy.intercept({
            method: "GET",
            url: "/api/session/1"
        }, {
            statusCode: 200,
            body: {
                id: 1,
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum"
            }
        });
    });

    
    
    // Test de création d'une session
    it("Create a session", () => {
        cy.visit("/login");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.url().should("include", "/sessions");

        cy.get("button[routerLink='create']").click();
        cy.url().should("include", "/sessions/create")

        cy.get("input[formControlName=name]").type("Session 1");
        cy.get("input[formControlName=date]").type("2025-02-26");
        cy.get(".mat-select[formControlName=teacher_id]").click().get("mat-option").contains("Margot DELAHAYE").click();
        cy.get("textarea[formControlName=description]").type("Lorem ipsum");

        cy.get("button[type='submit']").click();
        cy.url().should("include", "/sessions");
    });



    // Test de mise à jour d'une session
    it("Update a session", () => {
        cy.intercept({
            method: "PUT",
            url: "/api/session/1",
        }, {
            statusCode: 200,
            body: {
                id: 1,
                name: "Session 2",
                date: "2025-02-27",
                teacher_id: 2,
                users: null,
                description: "Lorem ipsum 2"
            }
        });

        cy.url().should("include", "/sessions");

        cy.get("button").contains("Edit").click();
        cy.url().should("include", "/sessions/update")

        cy.get("input[formControlName=name]").clear().type("Session 2");
        cy.get("input[formControlName=date]").clear().type("2025-02-27");
        cy.get(".mat-select[formControlName=teacher_id]").click().get("mat-option").contains("Hélène THIERCELIN").click();
        cy.get("textarea[formControlName=description]").clear().type("Lorem ipsum 2");

        cy.get("button").contains("Save").click();
        cy.url().should("include", "/sessions");
    });
});