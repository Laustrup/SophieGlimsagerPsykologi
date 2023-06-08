render();

function render() {
    renderHead();
    renderHeader();
    renderFooter();

    const endpoint = window.location.href;
    console.log("Current endpoint",endpoint);

    switch (endpoint) {
        case welcomeURL: {
            renderFrontpage();
            console.log("Frontpage rendered!");
            break;
        } case aboutURL: {
            renderAbout();
            console.log("About rendered!");
            break;
        } case paymentsURL: {
            renderPayment();
            console.log("Payment rendered!");
            break;
        } case contactURL: {
            renderContact();
            console.log("Contact rendered!");
            break;
        } case treatmentURL: {
            renderTreatment();
            console.log("Treatment rendered!");
            break;
        } case bookingURL: {
            renderBooking();
            console.log("Booking rendered!");
            break;
        } case faqURL: {
            renderFAQ();
            console.log("FAQ rendered!");
            break;
        } case adminURL: {
            renderAdmin();
            console.log("Admin rendered!");
            break;
        } default: {
            renderFrontpage();
            console.log("Frontpage rendered!");
        }
    }
}