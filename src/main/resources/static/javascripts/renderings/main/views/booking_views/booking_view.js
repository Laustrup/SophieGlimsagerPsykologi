let booking = getBooking();
const stepTitles = ["Detaljer om booking", "Udfyld skema", "Godkend, book og betal"];
let stepIndex = 0,
    highestStep = sessionStorage.getItem("highest_booking_step") === null ? stepIndex : sessionStorage.getItem("highest_booking_step"),
    bookingStep = sessionStorage.getItem("booking_step") === null ? stepTitles[0] : sessionStorage.getItem("booking_step"),
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
    const isBooking = siteState === "booking";

    document.getElementById("inner_booking_section").innerHTML = isBooking ? `
        <section id="booking_steps_section"></section>
        <section id="booking_frame"></section>
        <section id="booking_navigation_buttons" class="navigation_buttons"></section>
    ` : `
        ${await cancelSection()}
    `;

    if (isBooking)
        await renderBookingParts();
}

async function renderBookingParts() {
    renderBookingSteps();

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

    document.getElementById("booking_navigation_buttons").innerHTML = bookingNavigationButtons();
}

async function changeBookingSiteState() {
    if (siteState === "booking")
        siteState = "cancel";
    else if (siteState === "cancel")
        siteState = "booking";

    await bookingSection();
}

function renderBookingSteps() {
    document.getElementById("booking_steps_section").innerHTML = `
        <h3>Trin</h3>
        <div id="booking_steps" class="details">
            <div class="label_with_input">
                <input type="radio" id="step_1" name="booking_step"
                    onclick="jumpBookingStep('${stepTitles[0]}').then()"
                    ${bookingStep === stepTitles[0] ? "checked" : ""}
                >
                <label for="step_1" class="booking_step_label">${stepTitles[0]}</label>
            </div>
            <div class="label_with_input">
                <input type="radio" id="step_2" name="booking_step"
                    onclick="jumpBookingStep('${stepTitles[1]}').then()"
                    ${bookingStep === stepTitles[1] ? "checked" : ""}
                    ${highestStep >= 1 ? "" : "disabled"}
                >
                <label for="step_2" class="booking_step_label">${stepTitles[1]}</label>
            </div>
            <div class="label_with_input">
                <input type="radio" id="step_3" name="booking_step"
                    onclick="jumpBookingStep('${stepTitles[2]}').then()"
                    ${bookingStep === stepTitles[2] ? "checked" : ""}
                    ${highestStep >= 2 ? "" : "disabled"}
                >
                <label for="step_3" class="booking_step_label">${stepTitles[2]}</label>
            </div>
        </div>
    `;
}

function bookingNavigationButtons() {
    switch (bookingStep) {
        case stepTitles[0]: {
            return booking !== null && booking !== undefined
                ? `<button type="submit" class="neutral_button" onclick="nextBookingStep().then()">Frem</button>`
                : ``;
        }
        case stepTitles[1]: {
            return `
                <button type="button" class="neutral_button" onclick="previousBookingStep().then();">Tilbage</button>
                <button type="submit" class="neutral_button" form="client_detail_form"">Frem</button>
            `;
        }
        case stepTitles[2]: {
            return `<button type="button" class="neutral_button" onclick="previousBookingStep()">Tilbage</button>`;
        }
        default: {
            return ``;
        }
    }
}

async function previousBookingStep() {
    stepIndex--;
    bookingStep = stepTitles[stepIndex];
    localStorage.setItem("booking_step",bookingStep);
    updateBookingRadio();
    await renderBookingParts();
}

async function nextBookingStep() {
    incrementBookingStep();
    bookingStep = stepTitles[stepIndex];
    localStorage.setItem("booking_step",bookingStep);
    updateBookingRadio();
    await renderBookingParts();
}

async function jumpBookingStep(step) {
    let stepIsAllowed = false,
        stepIsNewHighestStep = false;

    stepTitles.forEach((element, index) => {
        if (element === step && index <= highestStep + 1) {
            if (index > highestStep)
                stepIsNewHighestStep = true;

            incrementBookingStep(index);
            stepIsAllowed = true;
        }
    });

    if (stepIsAllowed) {
        bookingStep = step;
        localStorage.setItem("booking_step",bookingStep);
        updateBookingRadio();
        await renderBookingParts();
    }

    if (stepIsNewHighestStep)
        renderBookingSteps();
}

function incrementBookingStep(index) {
    stepIndex = index !== undefined && index !== null ? index : stepIndex + 1;
    highestStep = stepIndex > highestStep ? stepIndex : highestStep;
    sessionStorage.setItem("highest_booking_step",highestStep);
}

function updateBookingRadio() {
    stepTitles.forEach((title,index) => {
        if (bookingStep === title)
            document.getElementById("step_" + (index+1)).checked = true;
    });
}
