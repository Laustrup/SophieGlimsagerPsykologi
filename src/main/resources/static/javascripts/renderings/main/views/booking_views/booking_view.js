let booking = getBooking();
const stepTitles = ["Detaljer om booking", "Udfyld skema", "Godkend, book og betal"];
let stepIndex = 0,
    bookingStep = localStorage.getItem("booking_step") === null ? stepTitles[0] : localStorage.getItem("booking_step"),
    siteState = "booking";

async function renderBooking() {
    document.getElementById("main").innerHTML = `
        <section class="chapter_caption">
            <h1 id="booking_title" class="title">${siteState === "booking" ? "Book tid" : "Afmeld tid"}</h1> 
            <p class="title_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi culpa dignissimos, dolores libero molestiae natus omnis perspiciatis quod reiciendis. Autem blanditiis corporis earum impedit magni perferendis quo veniam voluptatum. Dicta!</p>
        </section>
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

    await bookingSection();
}

async function bookingSection() {
    document.getElementById("inner_booking_section").innerHTML = siteState === "booking" ? `
        <section id="booking_steps_section">
            <h3>Trin</h3>
            <div id="booking_steps" class="details">
                <div class="label_with_input">
                    <input type="radio" id="step_1" name="booking_step" onclick="jumpBookingStep(${stepTitles[0]}).then()" ${bookingStep === stepTitles[0] ? "checked" : ""}>
                    <label for="step_1" class="booking_step_label">${stepTitles[0]}</label>
                </div>
                <div class="label_with_input">
                    <input type="radio" id="step_2" name="booking_step" onclick="jumpBookingStep(${stepTitles[1]}).then()" ${bookingStep === stepTitles[1] ? "checked" : ""}>
                    <label for="step_2" class="booking_step_label">${stepTitles[1]}</label>
                </div>
                <div class="label_with_input">
                    <input type="radio" id="step_3" name="booking_step" onclick="jumpBookingStep(${stepTitles[2]}).then()" ${bookingStep === stepTitles[2] ? "checked" : ""}>
                    <label for="step_3" class="booking_step_label">${stepTitles[2]}</label>
                </div>
            </div>
        </section>
        <section id="booking_frame"></section>
    ` : `
        ${await cancelSection()}
    `;

    await renderBookingStep();
}

async function renderBookingStep() {
    switch (bookingStep) {
        case stepTitles[0]: {
            await renderCalendar();
            break;
        }
        case stepTitles[1]: {
            renderClientDescriptionForm();
            break;
        }
        case stepTitles[2]: {
            renderBookingReview();
            break;
        }
    }
}

async function changeBookingSiteState() {
    if (siteState === "booking")
        siteState = "cancel";
    else if (siteState === "cancel")
        siteState = "booking";

    await bookingSection();
}

function bookingNavigationButtons() {
    let buttons = ``;

    switch (bookingStep) {
        case "1": {
            buttons = `<button type="submit" class="neutral_button" onclick="nextBookingStep().then()">${stepTitles[1]}</button>`;
            break;
        }
        case "2": {
            buttons = `
                <button type="button" class="neutral_button" onclick="previousBookingStep().then();">${stepTitles[0]}</button>
                <button type="submit" class="neutral_button" onclick="nextBookingStep();">${stepTitles[2]}</button>
            `;
            break;
        }
        case "3": {
            buttons = `<button type="button" class="neutral_button" onclick="previousBookingStep()">${stepTitles[1]}</button>`;
        }
    }

    return `
        <div class="navigation_buttons">
            ${buttons}
        </div>
    `
}

async function previousBookingStep() {
    stepIndex--;
    bookingStep = stepTitles[stepIndex];
    localStorage.setItem("booking_step",bookingStep);
    updateBookingRadio();
    await renderBookingStep();
}

async function nextBookingStep() {
    stepIndex++;
    bookingStep = stepTitles[stepIndex];
    localStorage.setItem("booking_step",bookingStep);
    updateBookingRadio();
    await renderBookingStep();
}

async function jumpBookingStep(step) {
    bookingStep = step;
    bookingStep.forEach((element, index) => {
        if (element === step)
            stepIndex = index;
    });
    localStorage.setItem("booking_step",bookingStep);
    updateBookingRadio();
    await renderBookingStep();
}

function updateBookingRadio() {
    stepTitles.forEach((title,index) => {
        if (bookingStep === title)
            document.getElementById("step_" + (index+1)).checked = true;
    });
}
