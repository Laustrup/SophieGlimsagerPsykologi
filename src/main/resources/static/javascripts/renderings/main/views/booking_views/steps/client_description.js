function renderClientDescriptionForm() {
    document.getElementById("booking_frame").innerHTML =  `
        <form>
            <div class="input_fields">
                <div id="booking_description_fields">
                    <p>Angående samtalen</p>
                    ${inputField({
                        id: "booking_title",
                        label: "Title på booking",
                        text: TEXT_INPUT,
                        placeholder: "F.eks. Stress på arbejdet",
                        isRequired: true,
                        max: 20
                    })}
                    <label for="booking_subject">Emne</label>
                    <select id="booking_subject">
                        <option value="STRESS">Stress</option>
                        <option value="ANXIETY">Angst</option>
                    </select>
                    ${inputField({
                        id: "booking_description",
                        label: "Beskrivelse",
                        text: TEXT_INPUT,
                        placeholder: "Beskriv dit emne",
                        isRequired: true,
                        max: 250
                    })}
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
                        text: TEXT_INPUT,
                        placeholder: "F.eks. Bente Frandsen",
                        isRequired: true,
                        max: 35
                    })}
                    ${inputField({
                        id: "client_email",
                        label: "Din email",
                        text: EMAIL_INPUT,
                        placeholder: "F.eks. bente.frandsen@gmail.com",
                        isRequired: true,
                        max: 35
                    })}
                    ${inputField({
                        id: "client_phone",
                        label: "Dit telefonnummer",
                        text: PHONE_INPUT,
                        placeholder: "F.eks. 42757589",
                        isRequired: true,
                        max: 20
                    })}
                    ${inputField({
                        id: "client_birthdate",
                        label: "Din fødselsdato",
                        text: DATE_INPUT,
                        isRequired: true
                    })}
                </div>
            </div>
            ${bookingNavigationButtons()}
        </form>
    `;
}
