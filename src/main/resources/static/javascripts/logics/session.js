const startKey = "start", endKey = "end", subjectKey = "subject", titleKey = "title", descriptionKey = "description",
    lengthKey = "length", timestampKey = "timestamp", clientIdKey = "client_id", clientNameKey = "client_name",
    clientEmailKey = "client_email", clientPhoneKey = "client_phone", clientBirthdateKey = "client_birthdate",
    consultationKey = "consultation", clientAgeKey = "age";

function getBooking() {
    return localStorage.getItem("has_stored_booking") === "true" ? {
        start: localStorage.getItem(startKey),
        end: localStorage.getItem(endKey),
        subject: localStorage.getItem(subjectKey),
        title: localStorage.getItem(titleKey),
        description: localStorage.getItem(descriptionKey),
        length: localStorage.getItem(lengthKey),
        timestamp: localStorage.getItem(timestampKey),
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
    localStorage.setItem(subjectKey, booking.subject);
    localStorage.setItem(titleKey, booking.title);
    localStorage.setItem(descriptionKey, booking.description);
    localStorage.setItem(timestampKey, booking.timestamp);
    localStorage.setItem(lengthKey, booking.length);
    localStorage.setItem("has_stored_booking", "true");
    if (booking.client !== undefined)
        storeClient();
}

function storeClient(client) {
    localStorage.setItem(clientIdKey, client.id);
    localStorage.setItem(clientNameKey, client.name);
    localStorage.setItem(clientEmailKey, client.email);
    localStorage.setItem(clientPhoneKey, client.phone);
    localStorage.setItem(clientBirthdateKey, client.birthdate);
    localStorage.setItem(consultationKey, client.consultation);
    localStorage.setItem(clientAgeKey, client.age);
    localStorage.setItem("has_stored_client", "true");
}