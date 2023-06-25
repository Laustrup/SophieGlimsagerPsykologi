function renderClientDescriptionForm() {
    document.getElementById("booking_frame").innerHTML =  `
        <form id="client_detail_form" class="booking_section_form" onsubmit="saveClientDescription();">
            <div class="input_fields">
                <div id="booking_description_fields">
                    <p>Angående samtalen</p>
                    ${inputField({
                        id: "booking_title",
                        label: "Title på booking",
                        type: TEXT_INPUT,
                        placeholder: "F.eks. Stress på arbejdet",
                        isRequired: true,
                        max: 20
                    })}
                    <label for="booking_subject">Emne</label>
                    <select id="booking_subject">
                        <option value="STRESS">Stress</option>
                        <option value="ANXIETY">Angst</option>
                    </select>
                    <div class="input_field">
                        <label for="booking_description">Beskrivelse</label>
                        <textarea id="booking_description" rows="10" cols="33" placeholder="Beskriv dit emne" maxlength="250" required></textarea>
                    </div>
                    <label for="booking_consultation">Mødeform</label>
                    <select id="booking_consultation">
                        <option value="ONLINE">Online</option>
                        <option value="PHONE">Telefonisk</option>
                    </select>
                </div>
                <div id="client_description_fields">
                    <p>Lidt om dig</p>
                    ${inputField({
                        id: "client_name",
                        label: "Dit navn",
                        type: TEXT_INPUT,
                        placeholder: "F.eks. Bente Frandsen",
                        isRequired: true,
                        max: 35
                    })}
                    ${inputField({
                        id: "client_email",
                        label: "Din email",
                        type: EMAIL_INPUT,
                        placeholder: "F.eks. bente.frandsen@gmail.com",
                        isRequired: true,
                        max: 35
                    })}
                    ${inputField({
                        id: "client_phone",
                        label: "Dit telefonnummer",
                        type: PHONE_INPUT,
                        placeholder: "F.eks. 42757589",
                        isRequired: true,
                        max: 20
                    })}
                    ${inputField({
                        id: "client_birthdate",
                        label: "Din fødselsdato",
                        type: DATE_INPUT,
                        isRequired: true
                    })}
                </div>
            </div>
        </form>
    `;
}

async function saveClientDescription() {
    storeBooking({
        start: booking.start,
        end: booking.end,
        subject: document.getElementById("booking_subject").value,
        description: document.getElementById("booking_description").value,
        consultation: document.getElementById("booking_consultation").value,
        client: {
            name: document.getElementById("client_name").value,
            email: document.getElementById("client_email").value,
            phone: document.getElementById("client_phone").value,
            birthdate: document.getElementById("client_birthdate").value
        }
    });
    booking = getBooking();
    await nextBookingStep();
}