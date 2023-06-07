const isLocal = true;

const domain = "http://" + isLocal ? "localhost:8080/" : "www.sophie_glimsager_psykologi.dk/";

// View
const welcomeURL = domain + "velkommen",
      aboutURL = domain + "om_mig",
      paymentsURL = domain + "priser_og_betaling",
      contactURL = domain + "kontakt",
      treatmentURL = domain + "behandlingsområder",
      bookingURL = domain + "booking/afmeld_tider",
      adminURL = domain + "admin";

function bookingQuery(params) {
    return domain + "?start=" + params.start + "/?slut=" + params.end + (params.clientId != null ? "?klient=" + params.clientId : "");
}

// API
const apiURL = domain + "api/"
      getURL = apiURL + "get/",
      bookingUpsertURL = apiURL + "upsert",
      appointmentsURL = getURL + "appointments",
      availableBookingsURL = getURL + "available",
      deleteBookingURL = apiURL + "delete",
      apiAdminURL = apiURL + "admin/";

function loginQuery(login) {
    return apiAdminURL + "login/" + login;
}