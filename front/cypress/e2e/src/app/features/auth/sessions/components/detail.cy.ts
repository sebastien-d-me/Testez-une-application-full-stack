describe("Detail spec", () => {
    it("Show the details of a session", () => {
        cy.visit("/login");

        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                firstName: "Yoga",
                lastName: "Studio",
                admin: true
            }
        });

        cy.intercept("POST", "/api/session", {
            body: {
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum",
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
                description: "Lorem ipsum",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
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
                description: "Lorem ipsum",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
            }
        });

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.url().should("include", "/sessions");

        cy.get("span").contains("Detail").click();
        cy.url().should("include", "/sessions/detail/1");
    });

    it("Get back to the sessions", () => {
        cy.get("mat-icon").contains("arrow_back").click();
        cy.url().should("include", "/sessions");
    });

    it("Will delete the session", () => {  
        cy.reload();

        cy.intercept("POST", "/api/auth/login", {
            body: {
                id: 1,
                email: "yoga@studio.com",
                firstName: "Yoga",
                lastName: "Studio",
                admin: true
            }
        });

        cy.intercept("POST", "/api/session", {
            body: {
                name: "Session 1",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
                description: "Lorem ipsum",
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
                description: "Lorem ipsum",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
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
                description: "Lorem ipsum",
                date: "2025-02-26",
                teacher_id: 1,
                users: null,
            }
        });

        cy.intercept("DELETE", "/api/session/1", {
            statusCode: 200
        }).as("deleteSession");

        cy.get("input[formControlName=email]").type("yoga@studio.com");
        cy.get("input[formControlName=password]").type(`${"test!1234"}{enter}{enter}`);

        cy.get("span").contains("Detail").click();
        cy.url().should("include", "/sessions/detail/1");

        cy.get("mat-icon").contains("delete").click();
        
        cy.wait("@deleteSession");
        cy.url().should("include", "/sessions");
    });
});


