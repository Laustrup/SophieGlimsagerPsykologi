const isLocal = true;

const domain = "http://" + (isLocal ? "localhost:8080/" : "www.sophie_glimsager_psykologi.dk/");

// View
const welcomeURL = domain + "velkommen",
      aboutURL = domain + "om_mig",
      paymentsURL = domain + "priser_og_betaling",
      contactURL = domain + "kontakt",
      treatmentURL = domain + "behandlingsområder",
      bookingViewURL = domain + "booking/afmeld_tider",
      faqURL = domain + "faq",
      adminURL = domain + "admin";

function bookingQuery(params) {
    return domain + "?start=" + params.start + "/?slut=" + params.end + (params.clientId != null ? "?klient=" + params.clientId : "");
}

function changeURL(href) {
    window.location.href = href;
    render().then();
}

// API
const apiURL = domain + "api/",
      bookingAPIURL = apiURL + "booking/",
      appointmentsURL = bookingAPIURL + "appointments",
      availableBookingsURL = bookingAPIURL + "available",
      apiAdminURL = apiURL + "admin/",
      FAQURL = apiAdminURL + "faq";

function loginQuery(login) {
    return apiAdminURL + "login/" + login;
}