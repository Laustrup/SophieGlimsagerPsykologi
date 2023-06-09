async function fetchAvailableBookings() {
    return await fetchElement({
        url: availableBookingsURL,
        method: GET
    });
}

async function fetchAppointments() {
    return await fetchElement({
        url: appointmentsURL,
        method: GET
    });
}

async function fetchAllBookings() {
    return await fetchElement({
        url: bookingAPIURL,
        method: GET
    });
}

async function requestToDeleteBooking(body) {
    return Boolean(await fetchElement({
        url: bookingAPIURL,
        method: DELETE,
        body: body
    }));
}

async function upsertBooking(body) {
    return await fetchElement({
        url: bookingAPIURL,
        method: POST,
        body: body
    });
}