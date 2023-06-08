function renderHeader() {
    let html = ``;
    const navigationClass = "navigation_tab";

    if (window.location.href === adminURL)
        html = `
            <section id="tabs">
                <a href="javascript:logout();window.print(logout);" class="${navigationClass}">Log ud</a>
            </section>
        `;
    else
        html = `
            <section id="logo_and_tabs">
                <section id="logo_section">
                    <div>
                        <a href="${welcomeURL}" onclick="changeURL(welcomeURL);" class="image_button">
                            <img src="../static/images/logo.png" id="header_logo" alt="Sophie Glimsager">
                        </a>
                    </div>
                    <div>|</div>
                </section>
                <section id="tabs">
                    <a href="${bookingURL}" onclick="changeURL(bookingURL);" class="${navigationClass}">Book/afmeld tid</a>
                    <a href="${treatmentURL}" onclick="changeURL(treatmentURL);" class="${navigationClass}">Behandlingsområder</a>
                    <a href="${aboutURL}" onclick="changeURL(aboutURL);" class="${navigationClass}">Om mig</a>
                    <a href="${paymentsURL}" onclick="changeURL(paymentsURL);" class="${navigationClass}">Priser og betaling</a>
                    <a href="${faqURL}" onclick="changeURL(faqURL);" class="${navigationClass}">FAQ</a>
                    <a href="${contactURL}" onclick="changeURL(contactURL);" class="${navigationClass}">Kontakt</a>
                </section>
            </section>
        `;

    document.getElementById("header_content").innerHTML = html;
}