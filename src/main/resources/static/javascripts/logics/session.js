const startKey = "start", endKey = "end", subjectKey = "subject", titleKey = "title", descriptionKey = "description",
    lengthKey = "length", timestampKey = "timestamp", isBookedKey = "isBooked", clientIdKey = "client_id",
    clientNameKey = "client_name", clientEmailKey = "client_email", clientPhoneKey = "client_phone",
    clientBirthdateKey = "client_birthdate", consultationKey = "consultation", clientAgeKey = "age";

function getBooking() {
    return localStorage.getItem("has_stored_booking") === "true" ? {
        start: localStorage.getItem(startKey),
        end: localStorage.getItem(endKey),
        subject: localStorage.getItem(subjectKey),
        title: localStorage.getItem(titleKey),
        description: localStorage.getItem(descriptionKey),
        length: localStorage.getItem(lengthKey),
        timestamp: localStorage.getItem(timestampKey),
        isBooked: localStorage.getItem(isBookedKey),
        client: getClient()
    } : null;
}

function getClient() {
    return localStorage.getItem("has_stored_client") === "true" ? {
        id: localStorage.getItem(clientIdKey),
        name: localStorage.getItem(clientNameKey),
        email: localStorage.getItem(clientEmailKey),
        phone: localStorage.getItem(clientPhoneKey),
        birthdate: localStorage.getItem(clientBirthdateKey),
        consultation: localStorage.getItem(consultationKey),
        age: localStorage.getItem(clientAgeKey)
    } : null;
}

function storeBooking(booking) {
    localStorage.setItem(startKey, booking.start);
    localStorage.setItem(endKey, booking.end);
    if (booking.subject !== undefined)
        localStorage.setItem(subjectKey, booking.subject);
    if (booking.title !== undefined)
        localStorage.setItem(titleKey, booking.title);
    if (booking.description !== undefined)
        localStorage.setItem(descriptionKey, booking.description);
    localStorage.setItem(timestampKey, booking.timestamp);
    localStorage.setItem(lengthKey, booking.length);
    if (booking.isBooked !== undefined)
        localStorage.setItem(isBookedKey, booking.isBooked);
    if (booking.client !== undefined)
        storeToClient();

    localStorage.setItem("has_stored_booking", "true");
}

function storeToClient(client) {
    localStorage.setItem(clientIdKey, client.id);
    localStorage.setItem(clientNameKey, client.name);
    localStorage.setItem(clientEmailKey, client.email);
    localStorage.setItem(clientPhoneKey, client.phone);
    localStorage.setItem(clientBirthdateKey, client.birthdate);
    localStorage.setItem(consultationKey, client.consultation);
    localStorage.setItem(clientAgeKey, client.age);
    localStorage.setItem("has_stored_client", "true");
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