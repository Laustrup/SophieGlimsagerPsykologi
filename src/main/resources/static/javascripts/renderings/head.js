function renderHead() {
    let html = "", endpoint = window.location.href;
    const mainTitle = "Sophie Glimsager";

    switch (endpoint) {
        case bookingViewURL: {
            html = mainTitle + " - Tidsbooking";
            break;
        }
        case treatmentURL: {
            html = mainTitle + " - Behandlingsområder";
            break;
        }
        case aboutURL: {
            html = mainTitle + " - Om mig";
            break;
        }
        case paymentsURL: {
            html = mainTitle + " - Priser og betaling";
            break;
        }
        case contactURL: {
            html = mainTitle + " - Kontakt";
            break;
        }
        default: {
            html = mainTitle;
            break;
        }
    }

    document.getElementById("head_title").innerText = `${html}`;
}