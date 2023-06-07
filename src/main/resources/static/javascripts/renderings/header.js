async function renderHeader() {
    let html = ``;
    const navigationClass = "navigation_tab";

    if (window.location.href === adminURL && await isLoggedIn())
        html = `
            <section id="tabs">
                <a href="${logout()}" class="${navigationClass}">Log ud</a>
            </section>
        `;
    else
        html = `
            <section id="logo_and_tabs">
                <section id="logo_section">
                    <div>
                        <a href="${welcomeURL}" class="image_button">
                            <img src="../static/images/logo.png" id="header_logo" alt="Sophie Glimsager">
                        </a>
                    </div>
                    <div>|</div>
                </section>
                <section id="tabs">
                    <a href="${bookingURL}" class="${navigationClass}">Book/afmeld tid</a>
                    <a href="${treatmentURL}" class="${navigationClass}">Behandlingsområder</a>
                    <a href="${aboutURL}" class="${navigationClass}">Om mig</a>
                    <a href="${paymentsURL}" class="${navigationClass}">Priser og betaling</a>
                    <a href="${contactURL}" class="${navigationClass}">Kontakt</a>
                </section>
            </section>
        `;

    document.getElementById("header_content").innerHTML = html;
}