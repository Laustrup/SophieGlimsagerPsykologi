const startKey = "start", endKey = "end", subjectKey = "subject", titleKey = "title", descriptionKey = "description",
    lengthKey = "length", timestampKey = "timestamp", isBookedKey = "isBooked", clientIdKey = "client_id",
    clientNameKey = "client_name", clientEmailKey = "client_email", clientPhoneKey = "client_phone",
    clientBirthdateKey = "client_birthdate", consultationKey = "consultation", clientAgeKey = "age";

function getBooking() {
    return sessionStorage.getItem("has_stored_booking") === "true" ? {
        start: new Date(sessionStorage.getItem(startKey)),
        end: new Date(sessionStorage.getItem(endKey)),
        subject: sessionStorage.getItem(subjectKey),
        title: sessionStorage.getItem(titleKey),
        description: sessionStorage.getItem(descriptionKey),
        length: sessionStorage.getItem(lengthKey),
        timestamp: sessionStorage.getItem(timestampKey),
        isBooked: sessionStorage.getItem(isBookedKey),
        consultation: sessionStorage.getItem(consultationKey),
        client: getClient()
    } : null;
}

function getClient() {
    return sessionStorage.getItem("has_stored_client") === "true" ? {
        id: sessionStorage.getItem(clientIdKey),
        name: sessionStorage.getItem(clientNameKey),
        email: sessionStorage.getItem(clientEmailKey),
        phone: sessionStorage.getItem(clientPhoneKey),
        birthdate: new Date(sessionStorage.getItem(clientBirthdateKey)),
        age: sessionStorage.getItem(clientAgeKey)
    } : null;
}

function storeBooking(booking) {
    sessionStorage.setItem(startKey, booking.start);
    sessionStorage.setItem(endKey, booking.end);
    if (booking.subject !== undefined)
        sessionStorage.setItem(subjectKey, booking.subject);
    if (booking.title !== undefined)
        sessionStorage.setItem(titleKey, booking.title);
    if (booking.description !== undefined)
        sessionStorage.setItem(descriptionKey, booking.description);
    sessionStorage.setItem(timestampKey, booking.timestamp);
    sessionStorage.setItem(lengthKey, booking.length);
    if (booking.consultation !== undefined)
        sessionStorage.setItem(consultationKey, booking.consultation);
    if (booking.isBooked !== undefined)
        sessionStorage.setItem(isBookedKey, booking.isBooked);
    if (booking.client !== undefined)
        storeToClient(booking.client);

    sessionStorage.setItem("has_stored_booking", "true");
}

function storeToClient(client) {
    sessionStorage.setItem(clientIdKey, client.id);
    sessionStorage.setItem(clientNameKey, client.name);
    sessionStorage.setItem(clientEmailKey, client.email);
    sessionStorage.setItem(clientPhoneKey, client.phone);
    sessionStorage.setItem(clientBirthdateKey, client.birthdate);
    sessionStorage.setItem(clientAgeKey, client.age);
    sessionStorage.setItem("has_stored_client", "true");
}

function translateToBookings(bookings) {
    let translation = [];

    bookings.forEach(booking => translation.push(translateToBooking(booking)));

    return translation;
}

function translateToClients(clients) {
    let translation = [];

    clients.forEach(client => translation.push(translateToClient(client)));

    return translation;
}

function translateToBooking(booking) {
    return {
        start: new Date(booking.start),
        end: new Date(booking.end),
        subject: booking.subject,
        title: booking.title,
        description: booking.description,
        timestamp: new Date(booking.timestamp),
        length: booking.length,
        isBooked: booking.isBooked,
        client: translateToClient(booking.client)
    };
}

function translateToClient(client) {
    return {
        id: client.id,
        name: client.name,
        email: client.email,
        phone: client.phone,
        birthdate: new Date(client.birthdate),
        consultation: client.consultation,
        age: client.age
    };
}