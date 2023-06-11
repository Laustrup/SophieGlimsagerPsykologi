async function renderBooking() {
    document.getElementById("main").innerHTML = `
        <section id="booking_section" class="chapter">
            <div class="inner_container">
                ${bookingSection(await fetchAvailableBookings())}
            </div>
        </section>
        <section id="cancel_booking_section" class="chapter">
            <div id="inner_cancel_booking_container" class="inner_container">
                ${await cancelSection()}
            </div>
        </section>
    `;
}

function bookingSection(bookings) {
    let html = `
        
    `;

    return html;
}

function renderCalendar(bookings) {

}

const deleteFormContent = `
    <div class="input_fields">
        ${inputField({
            id: "name",
            label: "Navn",
            text: TEXT_INPUT,
            placeholder: "Indtast dit registreret navn her",
            isRequired: true
        })}
        <div class="time_fields">
            <p>Samtalens start</p>
            <div class="date_and_time_fields">
                ${inputField({
                    id: "date",
                    label: "Dato",
                    type: DATE_INPUT,
                    placeholder: "åååå-mm-dd",
                    isRequired: true
                })}
                ${timepicker({
                    id: "time",
                    label: "Tid",
                    isRequired: true
                })}
            </div>
        </div>
    </div>
    <div class="form_buttons">
        <button type="submit" onclick="cancelBooking().then()" class="cautious_button">Aflys booking</button>
    </div>
`

async function cancelSection() {
    return `
        <div id="canceling_information">
            <h3>Aflys booking</h3>
            <p>Her kan du aflyse tider, så længe det er dagen før samtalen.</p>
            <p>
                Husk det er vigtigt, at det er den samme infomation, som der er registreret på samtalen også afhængigt af store og små bogstaver,
                hvis du er i tvivl om hvad der er registreret, så kan du tjekke den email du har modtaget efter bookingen af samtalen
            </p>
        </div>
        <form id="delete_booking_form">
            ${deleteFormContent}
        </form>
    `;
}


async function cancelBooking() {
    const name = document.getElementById("name").value,
        start = document.getElementsByTagName("start").value,
        end = document.getElementById("slut").value,
        from = start.toLocaleString("da-DK", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "numeric",
            minute: "numeric"
        }),
        to = end.toLocaleString("da-DK", {
            hour: "numeric",
            minute: "numeric"
        });


    if (confirm("Hej " + name + ". Du har en tid fra " + from + " til " + to + ". Er du sikker på du ville slette denne?"))
        document.getElementById("delete_booking_form").innerHTML = await requestToDeleteBooking({
            start: start,
            end: undefined,
            subject: undefined,
            client: {
                id: 0,
                name: name,
                email: undefined,
                birthdate: undefined,
                phone: 0,
                consultation: undefined
            },
            title: undefined,
            description: undefined
        }) ? `
            <h3>Aflysning fuldført</h3>
            <p>
                Samtalen ${from} til ${end} under navnet ${name} er blevet aflyst successfuldt.
                Du burde gerne have fået en mail som bekræftelse på dette og returneret dine penge via. mobilepay på det nummer der er regisreret under bookingen.
                Ellers er du velkommen til at sende en besked under kontakt fanen øverst til højre på siden.
            </p>
        ` : `
            <h3>Aflysningen kunne ikke lykkes</h3>
            <p>Noget teknisk gik galt, hver velkommen til at sende en besked under kontakt fanen øverst til højre på siden.</p>
        `;
    else
        document.getElementById("delete_booking_form").innerHTML = ``;

    document.getElementById("delete_booking_form").innerHTML += `
        <button onclick="${() => document.getElementById("delete_booking_form").innerHTML = deleteFormContent}">Aflys en samtale</button>
    `;
}