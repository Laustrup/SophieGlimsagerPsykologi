let booking = getBooking();
const stepTitles = ["Detaljer om booking", "Udfyld skema", "Godkend, book og betal"];
let bookingStep = localStorage.getItem("booking_step") === null ? stepTitles[0] : localStorage.getItem("booking_step");
let siteState = "booking";

async function renderBooking() {
    document.getElementById("main").innerHTML = `
        <h1 id="booking_title" class="title">${siteState === "booking" ? "Book tid" : "Afmeld tid"}</h1>
        <hr />
        <section id="booking_section" class="chapter">
            <div id="inner_booking_section" class="inner_container"></div>
        </section>
        <section id="booking_switch_section" class="chapter">
            <div id="inner_booking_switch_container" class="inner_container">
                <h3>Skift mellem booking og aflysning</h3>
                <label class="switch">
                    <input type="checkbox" id="togBtn" onclick="changeBookingSiteState().then()">
                    <div class="slider round"></div>
                </label>
            </div>
        </section>
    `;

    await bookingSection(await fetchAvailableBookings());
}

async function bookingSection(bookings) {
    const includeCalendar = siteState === "booking" && bookingStep === stepTitles[0];

    document.getElementById("inner_booking_section").innerHTML = siteState === "booking" ? `
        <div id="booking_info">
            ${steps}
        </div>
        ${includeCalendar ? `<div id="calendar_frame"></div>` : ``}
    ` : `
        ${await cancelSection()}
    `;

    if (includeCalendar)
        renderCalendar(bookings)
}

function displayLength(inMinutes) {
    return inMinutes < 60 ? inMinutes.toString() + " minutter"
        : (Math.floor(inMinutes/60).toString() + " timer " +
            (inMinutes % 60 > 0 ? " og " + (inMinutes % 60).toString() + " minutter" : "").toString());
}

const steps = `
    <section id="booking_steps_section">
        <h3>Trin</h3>
        <div id="booking_steps" class="details">
            <div class="label_with_input">
                <input type="radio" id="step_1" name="booking_step" ${localStorage.getItem("booking_step") === null ? "checked" : ""} onclick="${() => {jumpBookingStep(1)}}" >
                <label for="step_1" class="booking_step_label">${stepTitles[0]}</label>
            </div>
            <div class="label_with_input">
                <input type="radio" id="step_2" name="booking_step" value="${() => { return bookingStep === "2"; }}" onclick="${() => {jumpBookingStep(2)}}">
                <label for="step_2" class="booking_step_label">${stepTitles[1]}</label>
            </div>
            <div class="label_with_input">
                <input type="radio" id="step_3" name="booking_step" value="${() => { return bookingStep === "3"; }}" onclick="${() => {jumpBookingStep(3)}}">
                <label for="step_3" class="booking_step_label">${stepTitles[2]}</label>
            </div>
        </div>
    </section>
`;

async function changeBookingSiteState() {
    if (siteState === "booking")
        siteState = "cancel";
    else if (siteState === "cancel")
        siteState = "booking";

    await bookingSection(await fetchAvailableBookings());
}

function bookingNavigationButtons() {
    let buttons = ``;

    switch (bookingStep) {
        case "1": {
            buttons = `<button type="submit" class="neutral_button" onclick="${() => { nextBookingStep()}}">${stepTitles[1]}</button>`;
            break;
        }
        case "2": {
            buttons = `
                <button type="button" class="neutral_button" onclick="${() => { storeReservation(); previousBookingStep();}}">${stepTitles[0]}</button>
                <button type="submit" class="neutral_button" onclick="${() => { storeReservation(); nextBookingStep();}}">${stepTitles[2]}</button>
            `;
            break;
        }
        case "3": {
            buttons = `<button type="button" class="neutral_button" onclick="${() => {previousBookingStep()}}">${stepTitles[1]}</button>`;
        }
    }

    return `
        <div class="navigation_buttons">
            ${buttons}
        </div>
    `
}

function storeReservation() {
    storeBooking({
        start: booking.start,
        end: booking.end,
        subject: document.getElementById("booking_subject").value,
        title: document.getElementById("booking_title").value,
        description: document.getElementById("booking_description"),
        length: booking.length,
        timestamp: booking.timestamp,
        client: {
            name: document.getElementById("client_name").value,
            email: document.getElementById("client_email").value,
            phone: document.getElementById("client_phone").value,
            birthdate: document.getElementById("client_birthdate").value,
            consultation: document.getElementById("booking_consultation").value
        }
    });
}

const clientDescription = `
    ${steps}
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

const reviewBookingAndPay = `
    ${steps}
    <div>
        <div>
            
        </div>
        <button class="positive_button">Book</button>
    </div>
    ${bookingNavigationButtons()}
`;

function previousBookingStep() {
    bookingStep--;
    localStorage.setItem("booking_step",bookingStep);
    document.getElementById("inner_booking_section").innerHTML = bookingInfo();
}

function nextBookingStep() {
    bookingStep++;
    localStorage.setItem("booking_step",bookingStep);
    document.getElementById("inner_booking_section").innerHTML = bookingInfo();
}

function jumpBookingStep(step) {
    bookingStep = step;
    localStorage.setItem("booking_step",bookingStep);
    document.getElementById("inner_booking_section").innerHTML = bookingInfo();
}

function bookingInfo() {
    switch (bookingStep) {
        case stepTitles[0]: return chosenBookingDetails();
        case stepTitles[1]: return clientDescription;
        case stepTitles[2]: return reviewBookingAndPay;
    }
}

