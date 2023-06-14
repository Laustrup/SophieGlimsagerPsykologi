async function fetchAvailableBookings() {
    return translateToBookings(await fetchElement({
            url: availableBookingsURL,
            method: GET
        })
    );
}

async function fetchAppointments() {
    return translateToBookings(await fetchElement({
            url: appointmentsURL,
            method: GET
        })
    );
}

async function fetchAllBookings() {
    return translateToBookings(await fetchElement({
            url: bookingAPIURL,
            method: GET
        })
    );
}

async function requestToDeleteBooking(body) {
    return Boolean(await fetchElement({
            url: bookingAPIURL,
            method: DELETE,
            body: body
        })
    );
}

async function upsertBooking(body) {
    return translateToBooking(await fetchElement({
            url: bookingAPIURL,
            method: POST,
            body: body
        })
    );
}