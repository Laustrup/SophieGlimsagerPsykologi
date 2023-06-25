function renderBookingReview() {
    document.getElementById("booking_frame").innerHTML = `
        <div id="review">
            <h2>Samtalen</h2>
            <div id="review_of_booking">
                <div>
                    <label for="booking_title">Titel</label>
                    <p id="booking_title">${booking.title}</p>
                </div>
                <div>
                    <label for="booking_description">Beskrivelse</label>
                    <p id="booking_description">${booking.description}</p>
                </div>
                <div>
                    <label for="booking_start">Start</label>
                    <p id="booking_start">${(booking.start).toLocaleString("da-DK", {
                        weekday: "long",
                        year: "numeric",
                        month: "long",
                        day: "numeric",
                        hour: "numeric",
                        minute: "numeric"
                    })}
                    </p>
                </div>
                <div>
                    <label for="booking_end">Slut</label>
                    <p id="booking_end">${(booking.end).toLocaleString("da-DK", {
                        weekday: "long",
                        year: "numeric",
                        month: "long",
                        day: "numeric",
                        hour: "numeric",
                        minute: "numeric"
                    })}</p>
                </div>
                <div>
                    <label for="booking_subject">Emne</label>
                    <p id="booking_subject">${booking.subject}</p>
                </div>
                <div>
                    <label for="booking_consultation">Mødeform</label>
                    <p id="booking_consultation">${booking.client.consultation}</p>
                </div>
            </div>
            <h2>Dine oplysninger</h2>
            <div id="review_of_client">
                <div>
                    <label for="client_name">Navn</label>
                    <p id="booking_name">${booking.client.name}</p>
                </div>
                <div>
                    <label for="client_email">Email</label>
                    <p id="booking_email">${booking.client.email}</p>
                </div>
                <div>
                    <label for="client_phone">Telefon</label>
                    <p id="booking_phone">${booking.client.phone}</p>
                </div>
                <div>
                    <label for="client_birthdate">Fødselsdato</label>
                    <p id="booking_birthdate">${booking.client.birthdate.toLocaleString("da-DK", {
                        year: "numeric",
                        month: "long",
                        day: "numeric"
                    })}</p>
                </div>
            </div>
        </div>
        <div id="accept_review">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consectetur dolor, eaque labore quam ullam unde. Architecto delectus dicta dignissimos ex exercitationem facere laboriosam maiores, minus numquam repellat tempora ullam voluptate.</p>
            <div class="mobilepay-button mobilepay-button-std" onclick="" id="mobilepay_booking_button" data-theme="mpblue">
                <img src="https://cdn.mobilepay.dk/res-website/img/buttons/standard/mpblue/medium.svg" alt="MobilePay">
            </div>
        </div>
    `;
}